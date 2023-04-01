package com.brenobackend.backbreno.Repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.brenobackend.backbreno.Model.Error.Product;
import com.brenobackend.backbreno.Model.Exception.ResourceNotFoundException;

@Repository
public class ProductRepository_old {
    
    private List<Product> products = new ArrayList<Product>();
    private Integer lastID = 0;

    public List<Product> getProducts() {
        return products;
    }

    public Optional<Product> getByID(Integer id) {
        return products
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    public Product add(Product product) {

        lastID ++;

        product.setId(lastID);
        products.add(product);

        return product;
    }

    public void remove(Integer id) {
        products.removeIf(product -> product.getId() == id);
    }

    public Product atualizar(Product product) {
        Optional<Product> productToUpdate = getByID(product.getId());

        if (productToUpdate.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        remove(product.getId());

        products.add(product);

        return product;
    }
}
