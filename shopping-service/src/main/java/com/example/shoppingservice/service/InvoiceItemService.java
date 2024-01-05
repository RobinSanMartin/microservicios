package com.example.shoppingservice.service;

import com.example.shoppingservice.entity.InvoiceItem;

import java.util.List;

public interface InvoiceItemService {
    public List<InvoiceItem> getInvoiceItems();
    public InvoiceItem getInvoiceItem(Long id);
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);
    public InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem);
    public InvoiceItem deleteInvoiceItem(Long id);
    public List<InvoiceItem> findByProductId(Long productId);
}
