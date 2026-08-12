package org.brinka.brinkaapi.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_pagamento")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido", nullable = false, unique = true)
    private OrderEntity pedido;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private PaymentTypeEntity tipo;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private PaymentStatusEntity status;

    private BigDecimal valor;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;
}
