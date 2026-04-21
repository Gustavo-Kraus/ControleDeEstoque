package dev.kraus.ControleEstoque.Controller;


import dev.kraus.ControleEstoque.Model.Produtos;
import dev.kraus.ControleEstoque.Model.ProdutosDTO;
import dev.kraus.ControleEstoque.Service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;

    @PostMapping("/salvar")
    public ResponseEntity<Produtos> salvar(@RequestBody Produtos produtos){

        Produtos produtoSalvo = produtosService.salvar(produtos);
        return ResponseEntity.ok(produtoSalvo);
    }

    @GetMapping("/listar")
    public List<Produtos> produtosList() {
        return produtosService.listarProdutos();
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<Long> produtosDelete(@PathVariable Long id){
        Produtos foiRemovido = produtosService.deletar(id);

        if (foiRemovido == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(id);
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity<Produtos> buscarPorId(@PathVariable Long id){
        Produtos produtos = produtosService.buscarPorId(id);

        return ResponseEntity.ok(produtos);
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<Produtos> atualizarProduto(
            @PathVariable Long id,
            @RequestBody ProdutosDTO produtosDTO){
        Produtos atualizado = produtosService.atualizar(id, produtosDTO);
        return ResponseEntity.ok(atualizado);
    }



}
