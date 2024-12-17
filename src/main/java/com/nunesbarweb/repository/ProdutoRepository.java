// Pacote: repository
package com.nunesbarweb.repository;

import com.nunesbarweb.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private List<Produto> produtos = new ArrayList<>();

    public void salvar(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> listar() {
        return produtos;
    }
}