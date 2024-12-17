package com.nunesbarweb.controller;

import com.nunesbarweb.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteController {
    private ClienteService clienteService = new ClienteService();

    @PostMapping("/cadastrarCliente")
    public String cadastrarCliente(@RequestParam String nome, @RequestParam String email) {
        clienteService.cadastrarCliente(nome, email);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "clientes";
    }
}
