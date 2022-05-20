/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.store.repository;

import com.store.entity.Category;
import com.store.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Herle
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    public List<Product> findByCategory(Category category);
}
