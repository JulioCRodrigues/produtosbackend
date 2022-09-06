package com.backend.project.products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.project.products.model.Produto;
import com.backend.project.products.repository.ProdutoRepository_old;
import com.backend.project.products.shared.ProdutoDTO;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository_old produtoRepository;

    // Obter todos 
    public List<ProdutoDTO> obterTodos(){

        List<Produto> produtos = produtoRepository.obterTodos();
        return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    // Obter produto por ID
    public Optional<ProdutoDTO> obterPorId(Integer id){
         return produtoRepository.obterPorId(id);
    }

    // Adicionar o produto na lista de produtos
    public ProdutoDTO adicionar(ProdutoDTO produto){
        return produtoRepository.adicionar(produto);
    }

    // Deletando produto da lista 
    public void deletar(Integer id){
         produtoRepository.deletar(id);
    }

    // Atualizar produto da lista
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produto){

        // Fazer alguma validação no ID
        produto.setId(id);
        
        // Atualizar produto
        return produtoRepository.atualizar(produto);
    }
    
}
