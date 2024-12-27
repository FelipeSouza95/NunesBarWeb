package com.nunesbarweb.repository;

import com.nunesbarweb.model.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    // Consulta para buscar produtos por categoria
    List<Produto> findByCategoria(String categoria);
    // Consulta para buscar produtos por nome (não sensível a maiúsculas/minúsculas)
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}

