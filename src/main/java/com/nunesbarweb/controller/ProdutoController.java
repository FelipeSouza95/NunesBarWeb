// Pacote: controller
package com.nunesbarweb.controller;

import com.nunesbarweb.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProdutoController {
    private ProdutoService service = new ProdutoService();

    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", service.listarProdutos());
        return "produtos";
    }

    @PostMapping("/cadastrarProduto")
    public String cadastrarProduto(@RequestParam String nome, @RequestParam String categoria, @RequestParam double valor, @RequestParam int quantidade) {
        service.cadastrarProduto(nome, categoria, valor, quantidade);
        return "redirect:/produtos";
    }
}