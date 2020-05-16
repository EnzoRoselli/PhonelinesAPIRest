package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice,Integer> {
    List<Invoice> findByPhonelineUserId(Integer userId);
}
