package org.brinka.brinkaapi.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_boneco")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imagem;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoryEntity categoria;
    @OneToMany(mappedBy = "produto")
    private List<ReviewEntity> avaliacoes;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;
}
