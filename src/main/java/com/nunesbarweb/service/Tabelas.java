package com.nunesbarweb.service;

import java.util.ArrayList;
import java.util.List;
import com.nunesbarweb.model.Produto;

public class Tabelas {

    private static List<Produto> produtos = new ArrayList<>();
    private static Integer produtoId = 1;

    // Métodos para armazenar e recuperar produtos
    public static void adicionarProduto(Produto produto) {
        produto.setId(produtoId++);
        produtos.add(produto);
    }

    public static List<Produto> getProdutos() {
        return produtos;
    }

    public static Produto getProdutoById(Integer id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private static List<Produto> carrinho = new ArrayList<>();

    public static void adicionarAoCarrinho(Produto produto, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            carrinho.add(produto);
        }
    }

    public static List<Produto> getCarrinho() {
        return carrinho;
    }

}
