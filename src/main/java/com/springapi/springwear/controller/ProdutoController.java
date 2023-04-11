package com.springapi.springwear.controller;

import com.springapi.springwear.model.Produto;
import com.springapi.springwear.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/listar") //lista todos os produtos.
    @ResponseBody
    public ResponseEntity<List<Produto>> listaProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }

    @GetMapping("/valores?valorIni=X&valorFin=Y") // lista todos os produtos entre um range de valores.
    @ResponseBody
    public ResponseEntity<List<Produto>> listaProdutoPorValor(@RequestParam("valorIni") Double valorIni, @RequestParam("valorFim") Double valorFim ){
        List<Produto> produtos = produtoRepository.findByPrecoBetween(valorIni, valorFim);
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }

    @GetMapping("/cat/{nomeCategoria}") // Lista todos os produtos pertencentes a categoria (nomeCategoria)
    public ResponseEntity<List<Produto>> listaProdutoPorCategoria(@PathVariable String nomeCategoria){
        List<Produto> produtos = produtoRepository.findByCategoriaNome(nomeCategoria);
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}") // Lista os produtos onde parte do nome contem (nome)
    public ResponseEntity<List<Produto>> listaProdutoContemLetra(@PathVariable String nome){
        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Produto> salvaProduto(@RequestBody Produto produto){
        Produto produtos = produtoRepository.save(produto);
        return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Produto> atualizaProdutos(@PathVariable Long id, @RequestBody Produto produto){
        if (produto == null) {
            return ResponseEntity.badRequest().build();
        }

        Produto p = produtoRepository.findById(id).orElseThrow();

        p.setId(p.getId());
        p.setNome(p.getNome());
        p.setDescricao(p.getDescricao());
        p.setValor(p.getValor());

        produtoRepository.saveAndFlush(p);

        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Produto> deletaProduto(@PathVariable Long id){
        Produto p = produtoRepository.findById(id).orElseThrow();
        produtoRepository.delete(p);
        return ResponseEntity.ok(p);
    }

}
