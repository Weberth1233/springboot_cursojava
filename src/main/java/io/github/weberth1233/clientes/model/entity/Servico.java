package io.github.weberth1233.clientes.model.entity;
import lombok.Data;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
@Entity
@Data
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 150)
    private String descricao;
    //Varios serviço para um cliente
    //Um cliente varios serviços um serviço para um cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @Column
    private BigDecimal valor;
}
