package com.nunesbarweb.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<ItemCarrinho> itens = new ArrayList<>();
    private static Carrinho instance = new Carrinho();

    private Carrinho() {
        itens = new ArrayList<>();
    }

    // Método para obter a instância do carrinho
    public static Carrinho getInstance() {
        return instance;
    }

    // Método para adicionar produto ao carrinho
    public void adicionarProduto(Produto produto, int quantidade) {
        // Verifica se o produto já existe no carrinho
        for (ItemCarrinho item : itens) {
            if (item.getProduto().equals(produto)) {
                item.setQuantidade(item.getQuantidade() + quantidade); // Aumenta a quantidade
                return;
            }
        }

        // Se o produto não está no carrinho, adiciona um novo item
        itens.add(new ItemCarrinho(produto, quantidade));
    }

    // Método para obter os itens do carrinho
    public List<ItemCarrinho> getItens() {
        return itens;
    }

    // Método para calcular o total do carrinho
    public double getTotal() {
        return itens.stream()
            .mapToDouble(item -> item.getProduto().getValor() * item.getQuantidade())
            .sum(); // Calcula o total
    }

    // Método para finalizar a compra (opcional, caso precise)
    public void finalizarCompra() {
        // Lógica para finalizar a compra (por exemplo, limpar o carrinho, processar pagamento, etc.)
        itens.clear();
    }

    // Método para limpar o carrinho
    public void limparCarrinho() {
        itens.clear();
    }
}
