package com.nunesbarweb.repository;

import com.nunesbarweb.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Método para buscar cliente por CPF e senha
    Cliente findByCpfAndSenha(String cpf, String senha);
}
