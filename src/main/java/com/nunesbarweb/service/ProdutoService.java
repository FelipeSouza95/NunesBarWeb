package com.nunesbarweb.service;

import com.nunesbarweb.model.Produto;
import com.nunesbarweb.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Cria ou atualiza um produto
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Obtém todos os produtos
    public List<Produto> obterTodosProdutos() {
        return produtoRepository.findAll();
    }

    // Obtém um produto pelo id
    public Optional<Produto> obterProdutoPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    // Deleta um produto pelo id
    public void deletarProduto(int id) {
        produtoRepository.deleteById(id);
    }

    // Atualiza o produto
    public Produto atualizarProduto(int id, Produto produto) {
        if (produtoRepository.existsById(id)) {
            produto.setId(id);
            return produtoRepository.save(produto);
        }
        return null;
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        // A consulta que você deve usar depende da sua implementação do ProdutoRepository
        return produtoRepository.findByCategoria(categoria);  // Exemplo: Busca por categoria
    }

    // Método para buscar produtos por nome
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);  // Consulta para buscar nome
    }

    // Deletar produto por ID
    public void deletarProduto(Integer id) {
        produtoRepository.deleteById(id);  // Deleta o produto do banco
    }
}
