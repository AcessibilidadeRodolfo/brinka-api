package org.brinka.brinkaapi.application.dto.output;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CartOutput (
    List<CartItemOutput> items,
    BigDecimal total
) {

}
