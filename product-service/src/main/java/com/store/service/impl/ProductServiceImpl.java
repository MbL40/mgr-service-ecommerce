/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.store.service.impl;

import com.store.entity.Category;
import com.store.entity.Product;
import com.store.repository.ProductRepository;
import com.store.service.ProductService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Herle
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        product.setStatus("CREADO");
        product.setCreateAt(new Date());

        return productRepository.save(product);
    }

    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);
        if (null == productDB) {
            return null;
        }
        productDB.setStatus("ELIMINADO");
        return productRepository.save(productDB);
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public Product updateStock(Long id, Long quantity) {
        Product productDB = getProduct(id);
        if (null == productDB) {
            return null;
        }
        Long stock = productDB.getStock() + quantity;
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }

    
    public Product updateProduct(Product product) {
        Product productDB = getProduct(product.getId());
        if (null == productDB) {
            return null;
        }
        product.setName(product.getName());
        product.setDescription(product.getDescription());
        product.setCategory(product.getCategory());
        product.setPrice(product.getPrice());

        return productRepository.save(productDB);
    }

}
