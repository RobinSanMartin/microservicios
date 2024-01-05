package com.example.shoppingservice.service;

import com.example.shoppingservice.entity.Invoice;
import com.example.shoppingservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findById(invoice.getId()).orElse(null);
        if(invoiceDB != null){
            return invoiceDB;
        }

        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);

        return invoiceDB;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findById(invoice.getId()).orElse(null);
        if(invoiceDB == null){
            return null;
        }

        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());

        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice deleteInvoice(Long id) {
        Invoice invoiceDB = invoiceRepository.findById(id).orElse(null);
        if(invoiceDB == null){
            return null;
        }

        invoiceDB.setState("DELETED");

        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public List<Invoice> findByCustomerId(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId);
    }
}
