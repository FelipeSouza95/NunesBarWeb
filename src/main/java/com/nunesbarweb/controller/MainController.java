package com.nunesbarweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nunesbarweb.model.Carrinho;
import com.nunesbarweb.model.Cliente; // Importar o modelo Cliente
import com.nunesbarweb.model.Produto;
import com.nunesbarweb.service.Tabelas;

@Controller
public class MainController {

    // Método para exibir página Inicial
    @GetMapping("/")
    public String Inicial() {
        return "index";
    }

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
        model.addAttribute("produtos", Tabelas.getProdutos());
        return "listarProdutos";
    }

    // Método para gravar o produto
    @PostMapping("/gravar")
    public String gravarProduto(@ModelAttribute Produto produto) {
        Tabelas.adicionarProduto(produto); // Adiciona o produto
        return "redirect:/listarProdutos"; // Redireciona para a página de listagem de produtos
    }

    // Método para exibir a página de login
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

        // Verifica se é um administrador
        if ("admin".equals(login) && "admin".equals(senha)) {
            return "redirect:/menuAdmin"; // Redireciona para o menu do administrador
        }

        // Busca cliente pelo CPF e senha
        for (Cliente cliente : Tabelas.getClientes()) {
            if (cliente.getCpf().equals(login) && cliente.getSenha().equals(senha)) {
                return "redirect:/index"; // Redireciona para a página inicial do cliente
            }
        }

        // Caso login ou senha sejam inválidos
        model.addAttribute("erro", "Login ou senha inválidos!");
        return "login"; // Retorna à página de login com mensagem de erro
    }

    // Método para exibir o menu do administrador
    @GetMapping("/menuAdmin")
    public String menuAdmin() {
        return "menuAdmin"; // Retorna menuAdmin.html do diretório templates
    }

    // Método para exibir o formulário de cadastro de cliente
    @GetMapping("/cadastrar")
    public String adicionarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastrar"; // Retorna a página para adicionar um cliente
    }

    // Método para gravar o cliente
    @PostMapping("/gravarCliente")
    public String gravarCliente(@ModelAttribute Cliente cliente) {
        Tabelas.adicionarCliente(cliente); // Adiciona o cliente
        return "redirect:/listarClientes"; // Redireciona para a página de listagem de clientes
    }

    // Método para listar os clientes cadastrados
    @GetMapping("/listarClientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", Tabelas.getClientes()); // Lista de clientes
        return "listarClientes"; // Retorna a página de listagem de clientes
    }

    @GetMapping("/caixa")
    public String exibirCaixa(Model model) {
        // Busca os produtos disponíveis e adiciona ao modelo
        model.addAttribute("produtos", Tabelas.getProdutos());
        return "caixa"; // Renderiza a página caixa.html
    }

    // Adiciona produto ao carrinho
    @PostMapping("/adicionarAoCarrinho")
    public String adicionarAoCarrinho(@RequestParam("id") Long id, @RequestParam("quantidade") int quantidade,
            Model model) {
        Produto produto = Tabelas.getProdutoById(id.intValue()); // Converte Long para Integer, caso necessário
        if (produto != null) {
            Tabelas.adicionarAoCarrinho(produto, quantidade); // Método para adicionar ao carrinho
        }
        return "redirect:/caixa"; // Retorna à página do caixa
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
