package com.store.shopping.service;

import com.store.shopping.entity.Invoice;

import java.util.List;


public interface ShoppingService {
    public List<Invoice> findInvoiceAll();

    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);

    public Invoice getInvoice(Long id);
}