package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice,Integer> {
}
