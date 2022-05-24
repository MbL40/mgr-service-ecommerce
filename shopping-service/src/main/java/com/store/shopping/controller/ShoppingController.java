package com.store.shopping.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.shopping.entity.Invoice;
import com.store.shopping.service.ShoppingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;

//@Slf4j
@RestController
@RequestMapping("/invoices")
public class ShoppingController {

    @Autowired
    public ShoppingService shoppingService;

    // -------------------Retrieve All Invoices--------------------------------------------
    @GetMapping
    public ResponseEntity<List<Invoice>> listAllInvoices() {
        List<Invoice> invoices = shoppingService.findInvoiceAll();
        if (invoices.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(invoices);
    }

    // -------------------Retrieve Single Invoice------------------------------------------
    @GetMapping(value = "/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") long id) {
      //  log.info("Fetching Invoice with id {}", id);
        Invoice invoice  = shoppingService.getInvoice(id);
        if (null == invoice) {
       //     log.error("Invoice with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(invoice);
    }

    // -------------------Create a Invoice-------------------------------------------
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result) {
       // log.info("Creating Invoice : {}", invoice);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Invoice invoiceDB = shoppingService.createInvoice (invoice);

        return  ResponseEntity.status( HttpStatus.CREATED).body(invoiceDB);
    }

    // ------------------- Update a Invoice ------------------------------------------------
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
        //log.info("Updating Invoice with id {}", id);
        
        invoice.setId(id);
        Invoice currentInvoice=shoppingService.updateInvoice(invoice);

        if (currentInvoice == null) {
            //log.error("Unable to update. Invoice with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(currentInvoice);
    }

    // ------------------- Delete a Invoice-----------------------------------------
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") long id) {
        //log.info("Fetching & Deleting Invoice with id {}", id);

        Invoice invoice = shoppingService.getInvoice(id);
        if (invoice == null) {
            //log.error("Unable to delete. Invoice with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        invoice = shoppingService.deleteInvoice(invoice);
        return ResponseEntity.ok(invoice);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}