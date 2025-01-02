package com.nunesbarweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nunesbarweb.model.Carrinho;
import com.nunesbarweb.model.Produto;
import com.nunesbarweb.service.ProdutoService;

@Controller
public class MainController {

    @Autowired
    private ProdutoService produtoService; // Injetar ProdutoService

    // Método para exibir página Inicial
    @GetMapping("/")
    public String inicial() {
        return "index";
    }

    @GetMapping("/index")
    public String paginaInicial() {
        return "index";
    }

    @GetMapping("/adicionar")
    public String adicionar(Model model) {
        model.addAttribute("produto", new Produto());
        return "adicionar";
    }

    // Método para listar os produtos cadastrados
    @GetMapping("/listarProdutos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.obterTodosProdutos());
        return "listarProdutos";
    }

    // Método para gravar o produto
    @PostMapping("/gravar")
    public String gravarProduto(@ModelAttribute Produto produto) {
        produtoService.salvarProduto(produto);
        return "redirect:/listarProdutos"; // Redireciona para a página de listagem de produtos
    }

    // Método para exibir a página de login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Processar o login
    @PostMapping("/login")
    public String processarLogin(
            @RequestParam("login") String login,
            @RequestParam("senha") String senha,
            Model model) {

        // Verifica se é um administrador
        if ("admin".equals(login) && "admin".equals(senha)) {
            return "redirect:/menuAdmin"; // Redireciona para o menu do administrador
        }

        // Caso login ou senha sejam inválidos
        model.addAttribute("erro", "Login ou senha inválidos!");
        return "login"; // Retorna à página de login com mensagem de erro
    }

    // Método para exibir o menu do administrador
    @GetMapping("/menuAdmin")
    public String menuAdmin() {
        return "menuAdmin";
    }

    // Adiciona produto ao carrinho
    @PostMapping("/adicionarAoCarrinho")
    public String adicionarAoCarrinho(@RequestParam("id") Long id, @RequestParam("quantidade") int quantidade,
            Model model) {
        // Obter o produto pelo ID
        Produto produto = produtoService.obterProdutoPorId(id.intValue()).orElse(null);

        if (produto != null) {
            // Adiciona o produto ao carrinho
            Carrinho.getInstance().adicionarProduto(produto, quantidade);
        }

        return "redirect:/caixa"; // Redireciona para a página do caixa
    }

    // Exibe carrinho de compras
    @GetMapping("/carrinho")
    public String carrinho(Model model) {
        Carrinho carrinho = Carrinho.getInstance();
        model.addAttribute("itensCarrinho", carrinho.getItens());
        model.addAttribute("totalCarrinho", carrinho.getTotal());
        return "carrinho";
    }

    // Realiza a compra
    @PostMapping("/realizarCompra")
    public String realizarCompra(Model model) {
        Carrinho carrinho = Carrinho.getInstance();
        carrinho.finalizarCompra();

        // Adiciona a mensagem de sucesso para ser exibida na página do caixa
        model.addAttribute("message", "Compra realizada com sucesso!");
        return "carrinho";
    }

    // Cancela o carrinho
    @PostMapping("/cancelarCarrinho")
    public String cancelarCarrinho(Model model) {
        Carrinho carrinho = Carrinho.getInstance();
        carrinho.limparCarrinho();

        model.addAttribute("message", "Carrinho cancelado!");
        return "carrinho";
    }
}
