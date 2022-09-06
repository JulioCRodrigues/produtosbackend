package com.backend.project.products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.project.products.model.Produto;
import com.backend.project.products.model.exception.ResourceNotFoundException;
import com.backend.project.products.repository.ProdutoRepository;
import com.backend.project.products.shared.ProdutoDTO;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Obter todos 
    public List<ProdutoDTO> obterTodos(){

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    // Obter produto por ID
    public Optional<ProdutoDTO> obterPorId(Integer id){

         Optional<Produto> produto = produtoRepository.findById(id);

         // Se não encontrar o produto
         if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id" + id + "não encontrado");
         }

         // Transformando em produto DTO
         ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
         return Optional.of(dto); 
    }

    // Adicionar o produto na lista de produtos
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){

        // Removendo ID para cadastrar o produto no banco 
        produtoDto.setId(null);

        // Criando objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // Converter para Produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // Salvar o produto no banco
        produto = produtoRepository.save(produto);

        // Retornar ProdutoDTO atualizado
        produtoDto.setId(produto.getId());

        return produtoDto;
    }

    // Deletando produto da lista 
    public void deletar(Integer id){

        // Verificar se o produto existe 
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o produto do id" + id );
        }
        
        // Deletar o produto pelo ID
         produtoRepository.deleteById(id);
    }

    // Atualizar produto da lista
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto){

        // Passar o Id para o produtoDto
        produtoDto.setId(id);

        // Criar um objeto de mapeamento 
        ModelMapper mapper = new ModelMapper();

        // Converter o DTO para Produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // Atualizar o produto no banco de dados
        produtoRepository.save(produto);
        
        // Retornar o produtoDTO atualizado
        return produtoDto;
    }
    
}
