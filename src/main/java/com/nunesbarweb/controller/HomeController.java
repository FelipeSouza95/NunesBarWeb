package com.nunesbarweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Mapeia a página inicial do sistema Nunes Bar Web.
     * 
     * @return O template "index.html" da página inicial.
     */
    @GetMapping("/")
    public String index() {
        return "index"; // Nome do arquivo HTML localizado em src/main/resources/templates/index.html
    }
}