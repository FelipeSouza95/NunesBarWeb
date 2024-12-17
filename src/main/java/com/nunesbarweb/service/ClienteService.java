
package com.nunesbarweb.service;

import com.nunesbarweb.model.Cliente;
import com.nunesbarweb.repository.ClienteRepository;
import java.util.List;

public class ClienteService {
    private ClienteRepository repository = new ClienteRepository();

    public void cadastrarCliente(String nome, String email) {
        Cliente cliente = new Cliente(nome, email);
        repository.salvar(cliente);
    }

    public List<Cliente> listarClientes() {
        return repository.listar();
    }
}
