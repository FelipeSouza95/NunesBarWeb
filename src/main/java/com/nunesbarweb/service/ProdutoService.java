// Pacote: service
package com.nunesbarweb.service;

import com.nunesbarweb.model.Produto;
import com.nunesbarweb.repository.ProdutoRepository;
import java.util.List;

public class ProdutoService {
    private ProdutoRepository repository = new ProdutoRepository();

    public void cadastrarProduto(String nome, String categoria, double valor, int quantidade) {
        Produto produto = new Produto(nome, categoria, valor, quantidade);
        repository.salvar(produto);
    }

    public List<Produto> listarProdutos() {
        return repository.listar();
    }
}