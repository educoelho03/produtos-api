package com.springapi.springwear.repository;

import com.springapi.springwear.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByPrecoBetween(Double valorIni, Double valorFin);
    List<Produto> findByCategoriaNome(String nomeCategoria);
    List<Produto> findByNomeContainingIgnoreCase(String nome);

}
