package org.brinka.brinkaapi.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_item_pedido")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private OrderEntity pedido;

    @ManyToOne
    @JoinColumn(name = "id_boneco")
    private ProductEntity boneco;

    private Integer quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
}
