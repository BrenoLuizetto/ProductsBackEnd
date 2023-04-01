package com.brenobackend.backbreno.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brenobackend.backbreno.Model.Error.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
