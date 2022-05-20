/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.store.service;

import com.store.entity.Category;
import com.store.entity.Product;
import java.util.List;

/**
 *
 * @author Herle
 */
public interface ProductService {
    
    public List<Product> listAllProduct();
    public Product getProduct(Long id);
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(Long id);
    
    public List<Product> findByCategory(Category category);
    public Product updateStock(Long id, Long quantity);
    
}
