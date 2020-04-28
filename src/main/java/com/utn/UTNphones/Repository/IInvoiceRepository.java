package com.utn.UTNphones.Repository;

import com.utn.UTNphones.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceRepository extends JpaRepository<Invoice,Integer> {
}
