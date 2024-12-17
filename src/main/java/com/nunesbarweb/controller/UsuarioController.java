package com.nunesbarweb.controller;

import com.nunesbarweb.service.UsuarioService;
import com.nunesbarweb.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {
    private UsuarioService usuarioService = new UsuarioService();

    @PostMapping("/login")
    public String autenticar(@RequestParam String login, @RequestParam String senha, Model model) {
        Usuario usuario = usuarioService.autenticarUsuario(login, senha);
        if (usuario != null) {
            if ("admin".equals(usuario.getTipo())) {
                return "redirect:/menuAdmin";
            }
            return "redirect:/menuUsuario";
        }
        model.addAttribute("erro", "Login ou senha inválidos");
        return "login";
    }

    @PostMapping("/cadastrarUsuario")
    public String cadastrarUsuario(@RequestParam String login, @RequestParam String senha, @RequestParam String tipo) {
        usuarioService.cadastrarUsuario(login, senha, tipo);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuarios";
    }
}