
package com.nunesbarweb.repository;

import com.nunesbarweb.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private List<Cliente> clientes = new ArrayList<>();

    public void salvar(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> listar() {
        return clientes;
    }
}
