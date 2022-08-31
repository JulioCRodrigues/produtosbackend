package com.backend.project.products.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.backend.project.products.model.Produto;

@Repository
public class ProdutoRepository {

    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Método para retornar uma lista de produtos
     * @return Lista de produtos
     */
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Método para retornar um produto por ID.
     * @param id do produto que será localizado
     * @return retorna produto por ID selecionado, caso tenha encontrado
     */
    public Optional<Produto> obterPorId(Integer id){
        return produtos
        .stream()
        .filter(produto -> produto.getId() == id)
        .findFirst();
    }
    /**
     * Método para adicionar produto na lista
     * @param produto Produto que será adicionado na lista.
     * @return Retorna um produto adicionado na lista
     */
    public Produto adicionar(Produto produto){

        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;

    }

    /**
     * Remover produto com base no Id informado
     * @param id do produto que será removido
     */

    public void deletar(Integer id){

         produtos.removeIf(produto -> produto.getId() == id);

    }

    public Produto atualizar(Produto produto){

        // Encontrar o produto na lista 
        Optional<Produto> produtoEcontrado = obterPorId(produto.getId());

        if(produtoEcontrado.isEmpty()){
            throw new InputMismatchException("Produto não encontrado!");
        }

        // Remover o produto antigo da lista 
        deletar(produto.getId());
        
        
        // Adicionar um novo produto
        produtos.add(produto);

        return produto;

    }


    
}
