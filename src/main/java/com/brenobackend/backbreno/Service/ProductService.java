package com.brenobackend.backbreno.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.brenobackend.backbreno.Model.Error.Product;
import com.brenobackend.backbreno.Model.Exception.ResourceNotFoundException;
import com.brenobackend.backbreno.Repository.ProductRepository;
import com.brenobackend.backbreno.Shared.ProductDTO;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getProducts() {
        
        List<Product> products = productRepository.findAll();
        
        return products.stream()
        .map(product -> new ModelMapper().map(product, ProductDTO.class))
        .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getByID(Integer id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Produto com id: "+ id + " não encontrado");
        }

        ProductDTO productDTO = new ModelMapper().map(product.get(), ProductDTO.class);
        return Optional.of(productDTO);
    }

    public ProductDTO add(ProductDTO productDTO) {
        
        productDTO.setId(null);

        ModelMapper mapper = new ModelMapper();

        Product product = mapper.map(productDTO, Product.class);

        product = productRepository.save(product);

        productDTO.setId(product.getId());

        return productDTO;
    }

    public void remove(Integer id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel deletar o produto com o ID: " + id + "- Produto inexistente");
        }

        productRepository.deleteById(id);;
    }

    public ProductDTO atualizar(Integer id, ProductDTO productDTO) {
        productDTO.setId(id);
        
        ModelMapper mapper = new ModelMapper();

        Product product = mapper.map(productDTO, Product.class);

        productRepository.save(product);

        return productDTO;
    }

}
