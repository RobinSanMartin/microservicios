package com.example.shoppingservice.repository;

import com.example.shoppingservice.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long>{
    public List<InvoiceItem> findByProductId(Long productId);
}
