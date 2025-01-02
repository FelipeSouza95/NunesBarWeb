package com.nunesbarweb.controller;

import com.nunesbarweb.model.Produto;
import com.nunesbarweb.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Método único para listar produtos com filtro por nome ou categoria
    @GetMapping({"/produtos", "/caixa"})
    public String listarProdutos(@RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "categoria", required = false) String categoria,
                                 Model model) {
        // Filtra os produtos por nome, categoria ou mostra todos
        if (search != null && !search.isEmpty()) {
            // Filtra os produtos por nome
            model.addAttribute("produtos", produtoService.buscarPorNome(search));
            model.addAttribute("search", search); // Passa o valor de pesquisa para a view
        } else if (categoria != null && !categoria.isEmpty()) {
            // Filtra os produtos por categoria
            model.addAttribute("produtos", produtoService.buscarPorCategoria(categoria));
            model.addAttribute("categoria", categoria); // Passa a categoria para a view
        } else {
            // Caso contrário, exibe todos os produtos
            model.addAttribute("produtos", produtoService.obterTodosProdutos());
        }

        // Verifica a URL para definir a view retornada
        String viewName = "produtos".equals(model.getAttribute("viewName")) ? "listarProdutos" : "caixa";
        return viewName; // Retorna a página correta de acordo com a URL
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
