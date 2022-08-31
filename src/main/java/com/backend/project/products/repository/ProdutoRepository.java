package com.backend.project.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.project.products.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    
    
}
