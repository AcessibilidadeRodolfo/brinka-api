package org.brinka.brinkaapi.application.dto.input;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record FreteInput(
        LocalDate data,
        LocalDate dataAtual,
        String cepOrigem,
        String cepDestino,
        String servico,
        String formato,
        String embalagem,
        Integer altura,
        Integer largura,
        Integer comprimento,
        String codigoEmbalagem,
        BigDecimal peso,
        BigDecimal valorDeclarado
) {
}
