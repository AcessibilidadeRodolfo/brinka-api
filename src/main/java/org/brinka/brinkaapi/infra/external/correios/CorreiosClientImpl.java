package org.brinka.brinkaapi.infra.external.correios;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.dto.input.FreteInput;
import org.brinka.brinkaapi.application.dto.output.FreteOutput;
import org.brinka.brinkaapi.application.gateway.CorreiosClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CorreiosClientImpl implements CorreiosClient {
    private static final String URL = "https://www2.correios.com.br/sistemas/precosPrazos/prazos.cfm";

    private static final String REFERER = "https://www2.correios.com.br/sistemas/precosPrazos/";

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public FreteOutput calculateFrete(FreteInput input) {

        String body = buildFormData(input);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Referer", REFERER)
                .header("Origin", "https://www2.correios.com.br")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RuntimeException(
                        "Correios returned HTTP " + response.statusCode()
                );
            }

            return parseResponse(response.body());

        } catch (IOException ex) {
            throw new RuntimeException(
                    "Error communicating with Correios",
                    ex
            );
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Correios request was interrupted",
                    ex
            );
        }
    }

    private String buildFormData(FreteInput input) {

        Map<String, String> form = new LinkedHashMap<>();

        form.put("data", input.data().format(DATE_FORMAT));
        form.put("dataAtual", input.dataAtual().format(DATE_FORMAT));
        form.put("cepOrigem", input.cepOrigem());
        form.put("cepDestino", input.cepDestino());
        form.put("servico", input.servico());
        form.put("Selecao", "caixa selected");
        form.put("Formato", input.formato());
        form.put("embalagem1", input.embalagem());
        form.put("embalagem2", "");
        form.put("Altura", input.altura().toString());
        form.put("Largura", input.largura().toString());
        form.put("Comprimento", input.comprimento().toString());
        form.put("Selecao31", "");
        form.put("proCod_in_31", "");
        form.put("nomeEmbalagemCaixa", "");
        form.put("TipoEmbalagem31", "");
        form.put("Selecao32", "32");
        form.put("proCod_in_32", input.codigoEmbalagem());
        form.put("TipoEmbalagem32", "");
        form.put("Selecao33", "");
        form.put("proCod_in_33", "");
        form.put("TipoEmbalagem33", "");
        form.put("Selecao34", "");
        form.put("proCod_in_34", "");
        form.put("TipoEmbalagem34", "");
        addEmptyProductFields(form);
        form.put("peso", input.peso().toString());
        form.put("nomeEmbalagemEnvelope", "");
        form.put("TipoEmbalagem8", "");
        form.put("TipoEmbalagem12", "");
        form.put("TipoEmbalagem22", "");
        form.put("valorDeclarado", "");
        form.put("Calcular", "Calcular");

        return form.entrySet()
                .stream()
                .map(entry ->
                        encode(entry.getKey()) +
                                "=" +
                                encode(entry.getValue())
                )
                .collect(Collectors.joining("&"));
    }

    private void addEmptyProductFields(Map<String, String> form) {

        int[] fields = {
                1, 2, 3, 4, 5, 6, 7,
                8, 9, 10, 11, 12, 13,
                14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27,
                28, 29, 30
        };

        for (int field : fields) {
            form.put("Selecao" + field, "");
            form.put("proCod_in_" + field, "");
        }
    }

    private FreteOutput parseResponse(String html) {

        Document document = Jsoup.parse(html);

        Element valueElement = document.select("tr.destaque:has(th:contains(Valor Total)) td").getLast();

        String value = valueElement.text();

        return new FreteOutput(parseMoney(value));
    }

    private BigDecimal parseMoney(String value) {
        String normalized = value
                .replace("R$", "")
                .replace(".", "")
                .replace(",", ".")
                .trim();

        return new BigDecimal(normalized);
    }

    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
