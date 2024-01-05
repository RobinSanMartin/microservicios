package com.example.shoppingservice.service;

import com.example.shoppingservice.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    public List<Invoice> getAllInvoices();
    public Invoice getInvoice(Long id);
    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Long id);
    public List<Invoice> findByCustomerId(Long customerId);
}
