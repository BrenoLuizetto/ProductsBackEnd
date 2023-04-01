package com.brenobackend.backbreno.View.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brenobackend.backbreno.Model.Error.Product;
import com.brenobackend.backbreno.Service.ProductService;
import com.brenobackend.backbreno.Shared.ProductDTO;
import com.brenobackend.backbreno.View.Controller.Model.ProductResponse;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductDTO> productsDTO = productService.getProducts();

        ModelMapper mapper = new ModelMapper();
        
        List<ProductResponse> productsResponse = productsDTO.stream()
        .map(productDTO -> mapper.map(productDTO, ProductResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(productsResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductResponse product) {
        ProductDTO productDTO = new ModelMapper().map(product, ProductDTO.class);

        productService.add(productDTO);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> getByID(@PathVariable Integer id) {

        try {
            Optional<ProductDTO> productDTO = productService.getByID(id);

            ProductResponse product = new ModelMapper().map(productDTO.get(), ProductResponse.class);
    
            return new ResponseEntity<>(Optional.of(product), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Integer id) {
        productService.remove(id);
        return "ID:" + id + " removido com sucesso";
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> atualizar(@PathVariable Integer id, @RequestBody ProductResponse product) {
        ProductDTO productDTO = new ModelMapper().map(product, ProductDTO.class);

        productService.atualizar(id, productDTO);
        
        return new ResponseEntity<>(product, HttpStatus.OK);
        
    }
}
