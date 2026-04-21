package dev.kraus.ControleEstoque.Service;


import dev.kraus.ControleEstoque.Model.Produtos;
import dev.kraus.ControleEstoque.Model.ProdutosDTO;
import dev.kraus.ControleEstoque.Repository.ProdutosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutosService {
    private ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public Produtos salvar(Produtos produtos) {
        if (produtos.getProduto() == null || produtos.getProduto().isBlank()) {
            throw new RuntimeException("Nome do produto é obrigatorio");
        }

        boolean existeprodutonome = produtosRepository.findByProduto(produtos.getProduto()).isPresent();

        if (existeprodutonome){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um produto com esse nome"
            );
        }

        if (produtos.getQtd_atual() < 0) {
             throw new RuntimeException("a Qtd_Atual não pode ser negativa");
        }

        if (produtos.getQtd_min() < 0) {
            throw new RuntimeException("a QTD_Min Não pode ser negativa");
        }
        return produtosRepository.save(produtos);
    }

    public Produtos deletar(Long id) {
        Produtos produtos = produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produtos.getQtd_atual() > 0) {
            throw new RuntimeException("Não é possivel deletar um produto com estoque");
        }
        produtosRepository.delete(produtos);
        return produtos;
    }

    public List<Produtos> listarProdutos(){
        return produtosRepository.findAll();
    }

    public Produtos buscarPorId(Long id){
        return produtosRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado"));
    }

    public Produtos atualizar(Long id, ProdutosDTO produtosDTO){

        Produtos produtos = produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não entrado"));

        if (produtosDTO.getProduto() != null) {
            produtos.setProduto(produtosDTO.getProduto());
        }

        if (produtosDTO.getQtd_atual() != null) {
            produtos.setQtd_atual(produtosDTO.getQtd_atual());
        }
        if (produtosDTO.getQtd_min() != null) {
            produtos.setQtd_min(produtosDTO.getQtd_min());
        }

        return produtosRepository.save(produtos);
    }
}
