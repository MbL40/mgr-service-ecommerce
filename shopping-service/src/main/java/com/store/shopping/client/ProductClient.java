package com.store.shopping.client;

import com.store.shopping.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", path = "/products")


public interface ProductClient {

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

    @PostMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long id, @RequestParam(name = "quantity", required = true) Double quantity);
}
