package org.brinka.brinkaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class Payment {
    private Integer id;
    private PaymentType tipo;
    private PaymentStatus status;
    private BigDecimal valor;
    private LocalDateTime dataPagamento;
}
