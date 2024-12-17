package com.nunesbarweb.repository;

import com.nunesbarweb.model.Usuario;
import com.nunesbarweb.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> usuarios = new ArrayList<>();

    public void salvar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> listar() {
        return usuarios;
    }

    public Usuario autenticar(String login, String senha) {
        return usuarios.stream()
                .filter(u -> u.getLogin().equals(login) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }
}