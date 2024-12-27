package com.nunesbarweb.service;

import com.nunesbarweb.model.Cliente;
import com.nunesbarweb.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Salva ou atualiza um cliente
    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Método para obter cliente por CPF e senha
    public Cliente obterClientePorCpfESenha(String cpf, String senha) {
        // Supondo que o método findByCpfAndSenha já esteja implementado no repositório
        return clienteRepository.findByCpfAndSenha(cpf, senha);
    }

    // Obtém todos os clientes
    public List<Cliente> obterTodosClientes() {
        return clienteRepository.findAll();
    }

    // Obtém um cliente por ID
    public Optional<Cliente> obterClientePorId(int id) {
        return clienteRepository.findById(id);
    }

    // Deleta um cliente por ID
    public void deletarCliente(int id) {
        clienteRepository.deleteById(id);
    }

    // Atualiza um cliente
    public Cliente atualizarCliente(int id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        }
        return null;
    }
}
