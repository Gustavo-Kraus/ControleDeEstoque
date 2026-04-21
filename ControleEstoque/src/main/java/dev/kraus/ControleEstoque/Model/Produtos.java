package dev.kraus.ControleEstoque.Model;


import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produtos" )
public class Produtos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String produto;

    @NonNull
    private Integer qtd_atual;

    @NonNull
    private Integer qtd_min;
}
