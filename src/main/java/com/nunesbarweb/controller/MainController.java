package com.nunesbarweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nunesbarweb.model.Produto;
import com.nunesbarweb.service.Tabelas;

@Controller
public class MainController {

    // Método para exibir página adicionar Produto
    @GetMapping("/")
    public String Inicial(Model model) {
        model.addAttribute("produto", new Produto());
        return "adicionar";
    }

    @GetMapping("/adicionar")
    public String adicionar(Model model) {
        model.addAttribute("produto", new Produto());
        return "adicionar";
    }

    // Método para listar os produtos cadastrados
    @GetMapping("/listarProdutos")
    public String listagem(Model model) {
        model.addAttribute("produtos", Tabelas.geProdutos());
        return "listarProdutos";
    }

    // Método para gravar o produto
    @PostMapping("/gravar")
    public String gravarProduto(@ModelAttribute Produto produto) {
        Tabelas.adicionarProduto(produto); // Adiciona o 
        return "redirect:/listarProdutos"; // Redireciona para a página de listagem de produtos
    }
}
