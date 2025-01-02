package com.nunesbarweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class NunesBarWebApplication extends SpringBootServletInitializer {

    // Método principal para rodar a aplicação
    public static void main(String[] args) {
        SpringApplication.run(NunesBarWebApplication.class, args);
    }

    // Sobrescreve o método configure para suportar o empacotamento como WAR
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NunesBarWebApplication.class);
    }
}
