package com.backend.project.products.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.project.products.model.Produto;
import com.backend.project.products.services.ProdutoService;
import com.backend.project.products.shared.ProdutoDTO;
import com.backend.project.products.view.model.ProdutoRequest;
import com.backend.project.products.view.model.ProdutoResponse;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos(){

        List<ProdutoDTO> produtos =  produtoService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<ProdutoResponse> resposta = produtos.stream().map(produtoDto -> mapper.map(produtoDto, ProdutoResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable Integer id){

        try {
            Optional<ProdutoDTO> dto =  produtoService.obterPorId(id);

            // Converter para ProdutoResponseEntity 
            ProdutoResponse produto = new ModelMapper().map(dto.get(), ProdutoResponse.class);

            // Retorna 
            return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoReq){

        // Converter para produtoDTO
        ModelMapper mapper = new ModelMapper();

        ProdutoDTO produtoDto = mapper.map(produtoReq, ProdutoDTO.class);

        produtoDto = produtoService.adicionar(produtoDto);

        //  Retornar um ProdutoResponse
        return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        produtoService.deletar(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtoReq, @PathVariable Integer id){

        ModelMapper mapper = new ModelMapper();

        ProdutoDTO produtoDto = mapper.map(produtoReq, ProdutoDTO.class);

        produtoDto = produtoService.atualizar(id, produtoDto);

        return new ResponseEntity<>(
            mapper.map(produtoDto, ProdutoResponse.class),
            HttpStatus.OK
        );
    }



    
}
