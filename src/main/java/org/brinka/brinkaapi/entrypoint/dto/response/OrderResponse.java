package org.brinka.brinkaapi.entrypoint.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Integer id,
        String status,
        List<OrderItemResponse> itens,
        BigDecimal subtotal,
        BigDecimal desconto,
        BigDecimal frete,
        BigDecimal total,
        OrderPaymentResponse pagamento,
        LocalDateTime dataPedido
) {
}
