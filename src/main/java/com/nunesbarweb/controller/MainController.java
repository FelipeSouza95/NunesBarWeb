package com.nunesbarweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nunesbarweb.model.Produto;
import com.nunesbarweb.service.Tabelas;

@Controller
public class MainController {

    // Método para exibir página Inicial
    @GetMapping("/")
    public String Inicial() {
        return "index";
    }// Método para exibir página Inicial
    
    @GetMapping("/index")
    public String PaginaInicial() {
        return "index";
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

    @GetMapping("/login")
    public String login() {
        return "login"; // Retorna login.html do diretório templates
    }

    // Processar o login
    @PostMapping("/login")
    public String processarLogin(
            @RequestParam("login") String login,
            @RequestParam("senha") String senha,
            Model model) {
        if ("admin".equals(login) && "admin".equals(senha)) {
            return "redirect:/menuAdmin"; // Redireciona para o menu do administrador
        }
        model.addAttribute("erro", "Login ou senha inválidos!");
        return "login"; // Retorna à página de login com erro
    }

    @GetMapping("/menuAdmin")
    public String menuAdmin() {
        return "menuAdmin"; // Retorna menuAdmin.html do diretório templates
    }
}
