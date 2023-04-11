package io.github.weberth1233.clientes.model.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
//Permite a criação dos get, set, tostring, contrutor e etc para os atributos de forma automatica dentro do projeto sem necessidade da criação dos get se sets na classe
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.vazio}")
    private String nome;
    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.vazio}")
    @CPF(message = "{campo.cpf.inválido}")
    private String cpf;
    //updatable - não permitir atualizar a data de cadastro na base de dados
    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
    //Antes de inserir no banco eu insiro a data atual
    @PrePersist
    public void prePresist(){
        setDataCadastro(LocalDate.now());
    }
}
