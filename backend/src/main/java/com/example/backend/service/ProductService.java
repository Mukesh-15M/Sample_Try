package com.example.backend.service;

import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;
    public List<Product> getProducts() {
        return repository.findAll();
    }

    public boolean addProducts(Long id,Product product) {
        if(repository.existsById(id)|| product.getName().equals("null")||product.getQuantity()<=0)
            return false;
        repository.save(product);
        return true;
    }

    public Product getProductsId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
