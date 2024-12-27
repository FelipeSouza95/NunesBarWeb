package com.nunesbarweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nunesbarweb.model.Carrinho;
import com.nunesbarweb.model.Cliente;
import com.nunesbarweb.model.Produto;
import com.nunesbarweb.service.ClienteService;
import com.nunesbarweb.service.ProdutoService;

@Controller
public class MainController {

    @Autowired
    private ProdutoService produtoService; // Injetar ProdutoService

    @Autowired
    private ClienteService clienteService; // Injetar ClienteService

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

        // Busca cliente pelo CPF e senha
        Cliente cliente = clienteService.obterClientePorCpfESenha(login, senha);
        if (cliente != null) {
            return "redirect:/index"; // Redireciona para a página inicial do cliente
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

    // Método para exibir o formulário de cadastro de cliente
    @GetMapping("/cadastrar")
    public String adicionarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastrar";
    }

    // Método para gravar o cliente
    @PostMapping("/gravarCliente")
    public String gravarCliente(@ModelAttribute Cliente cliente) {
        clienteService.salvarCliente(cliente);
        return "redirect:/listarClientes"; // Redireciona para a página de listagem de clientes
    }

    // Método para listar os clientes cadastrados
    @GetMapping("/listarClientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.obterTodosClientes());
        return "listarClientes";
    }

    // Método para exibir o caixa (com lista de produtos)
    @GetMapping("/caixa")
    public String exibirCaixa(Model model) {
        model.addAttribute("produtos", produtoService.obterTodosProdutos());
        return "caixa";
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

        model.addAttribute("message", "Compra realizada com sucesso!");
        return "compraFinalizada"; // Redireciona para uma página de finalização de compra
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
