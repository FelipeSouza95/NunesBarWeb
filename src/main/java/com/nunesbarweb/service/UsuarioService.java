// Pacote: service
package com.nunesbarweb.service;

import com.nunesbarweb.model.Usuario;
import com.nunesbarweb.repository.UsuarioRepository;
import java.util.List;

public class UsuarioService {
    private UsuarioRepository repository = new UsuarioRepository();

    public void cadastrarUsuario(String login, String senha, String tipo) {
        Usuario usuario = new Usuario(login, senha, tipo);
        repository.salvar(usuario);
    }

    public Usuario autenticarUsuario(String login, String senha) {
        return repository.autenticar(login, senha);
    }

    public List<Usuario> listarUsuarios() {
        return repository.listar();
    }
}