package dev.kraus.ControleEstoque.Model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutosDTO {

    private String produto;
    private Integer qtd_atual;
    private Integer qtd_min;
}
