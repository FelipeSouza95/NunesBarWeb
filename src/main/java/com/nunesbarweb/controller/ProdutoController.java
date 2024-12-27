package com.nunesbarweb.controller;

import com.nunesbarweb.model.Produto;
import com.nunesbarweb.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Método para listar todos os produtos
    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.obterTodosProdutos());
        return "listarProdutos"; // Retorna para a página de listagem de produtos
    }

    // Método para excluir um produto
    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id); // Chama o serviço para deletar o produto
        return "redirect:/produtos"; // Redireciona para a lista de produtos
    }

    // Método para exibir o formulário de alteração de produto
    @GetMapping("/alterar/{id}")
    public String alterarProduto(@PathVariable Integer id, Model model) {
        // Recupera o produto pelo ID
        Produto produto = produtoService.obterProdutoPorId(id).orElse(null);
        if (produto == null) {
            return "redirect:/produtos"; // Caso não encontre o produto, redireciona para a lista
        }
        model.addAttribute("produto", produto); // Adiciona o produto no modelo para o formulário
        return "alterar"; // Retorna para o formulário de alteração
    }

    // Método para atualizar o produto no banco
    @PostMapping("/alterar/{id}")
    public String atualizarProduto(@PathVariable Integer id, Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        if (produtoAtualizado == null) {
            return "redirect:/produtos"; // Se não encontrar o produto, redireciona para a lista
        }
        return "redirect:/produtos"; // Redireciona para a lista de produtos após a atualização
    }
}
