package com.nunesbarweb.controller;

import com.nunesbarweb.model.Cliente;
import com.nunesbarweb.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Endpoint para salvar ou atualizar um cliente
    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute Cliente cliente, Model model) {
        if (cliente == null || cliente.getNome() == null || cliente.getCpf() == null) {
            model.addAttribute("erro", "Os campos nome e CPF são obrigatórios.");
            return "erro";  // Redireciona para uma página de erro (precisa criar uma página de erro)
        }
        Cliente clienteSalvo = clienteService.salvarCliente(cliente);
        model.addAttribute("cliente", clienteSalvo);
        return "cliente-sucesso";  // Redireciona para a página de sucesso (precisa criar uma página de sucesso)
    }

    // Endpoint para obter todos os clientes
    // Método para listar clientes
    @GetMapping("/listarClientes")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.obterTodosClientes();
        model.addAttribute("clientes", clientes);
        return "listarClientes";
    }

    // Endpoint para obter um cliente pelo ID (para alteração)
    @GetMapping("/alterarCliente/{id}")
    public String obterClientePorId(@PathVariable int id, Model model) {
        Optional<Cliente> cliente = clienteService.obterClientePorId(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "alterarCliente";  // Redireciona para a página de alteração de cliente (alterarCliente.html)
        } else {
            model.addAttribute("erro", "Cliente não encontrado.");
            return "erro";  // Redireciona para uma página de erro (precisa de uma página de erro)
        }
    }

    // Endpoint para atualizar um cliente
    @PostMapping("/alterarCliente/{id}")
    public String atualizarCliente(@PathVariable int id, @ModelAttribute Cliente cliente, Model model) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        if (clienteAtualizado != null) {
            model.addAttribute("cliente", clienteAtualizado);
            // Redireciona para listar todos os clientes após a atualização
            return "redirect:/listarClientes";  // Redireciona para a página de listagem de clientes
        } else {
            model.addAttribute("erro", "Cliente não encontrado para atualização.");
            return "erro";  // Redireciona para a página de erro (precisa de uma página de erro)
        }
    }

    // Endpoint para deletar um cliente
    @GetMapping("/deletarCliente/{id}")
    public String deletarCliente(@PathVariable int id, Model model) {
        clienteService.deletarCliente(id);
        model.addAttribute("mensagem", "Cliente deletado com sucesso.");
        // Redireciona para a lista de clientes após a exclusão
        return "redirect:/listarClientes";  // Redireciona para a página de listagem de clientes
    }
}
