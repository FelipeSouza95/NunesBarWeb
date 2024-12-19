package com.nunesbarweb.service;

import java.util.ArrayList;
import java.util.List;

import com.nunesbarweb.model.Cliente;
import com.nunesbarweb.model.Produto;

public class Tabelas {

    private static List<Produto> produtos = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static Integer produtoId = 1;
    private static Integer clienteId = 1;

    // Métodos para armazenar e recuperar produtos
    public static void adicionarProduto(Produto produto) {
        produto.setId(produtoId++);
        produtos.add(produto);
    }

    public static List<Produto> geProdutos() {
        return produtos;
    }

    public static Produto getProdutoById(Integer id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    // Métodos para armazenar e recuperar clientes
    public static void adicionarCliente(Cliente cliente ) {
        cliente.setId(clienteId++);
        clientes.add(cliente);
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static Cliente getClienteById(Integer id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}
