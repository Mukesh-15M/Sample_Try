package com.example.backend.controller;

import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getProducts()
    {
        return service.getProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductsById(@PathVariable("id") Long id)
    {
        return service.getProductsId(id);
    }

    @PostMapping("/products/{id}")
    public ResponseEntity<?> addProductsId(@PathVariable Long id, @RequestBody Product product)
    {
        boolean result=service.addProducts(id,product);
        if(!result)
            return ResponseEntity.status(400).body("Give Valid Details for product");
        return ResponseEntity.status(201).body(product);
    }

}
