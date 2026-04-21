package dev.kraus.ControleEstoque.Repository;

import dev.kraus.ControleEstoque.Model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
    List<Produtos> id(Long id);
    Optional<Produtos> findByProduto(String produto);
}
