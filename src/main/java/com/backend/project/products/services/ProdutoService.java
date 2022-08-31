package com.backend.project.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.project.products.model.Produto;
import com.backend.project.products.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Obter todos 
    public List<Produto> obterTodos(){
        return produtoRepository.obterTodos();
    }

    // Obter produto por ID
    public Optional<Produto> obterPorId(Integer id){
         return produtoRepository.obterPorId(id);
    }

    // Adicionar o produto na lista de produtos
    public Produto adicionar(Produto produto){
        return produtoRepository.adicionar(produto);
    }

    // Deletando produto da lista 
    public void deletar(Integer id){
         produtoRepository.deletar(id);
    }

    // Atualizar produto da lista
    public Produto atualizar(Integer id, Produto produto){

        // Fazer alguma validação no ID
        produto.setId(id);
        
        // Atualizar produto
        return produtoRepository.atualizar(produto);
    }
    
}
