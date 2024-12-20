package com.nunesbarweb.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<ItemCarrinho> itens = new ArrayList<>();
    private static Carrinho instancia;

    private Carrinho() {}

    public static Carrinho getInstance() {
        if (instancia == null) {
            instancia = new Carrinho();
        }
        return instancia;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        itens.add(new ItemCarrinho(produto, quantidade));
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public double getTotal() {
        return itens.stream().mapToDouble(ItemCarrinho::getTotal).sum();
    }

    public void finalizarCompra() {
        // Aqui você pode adicionar lógica de finalização da compra
        limparCarrinho();
    }

    public void limparCarrinho() {
        itens.clear();
    }
}

