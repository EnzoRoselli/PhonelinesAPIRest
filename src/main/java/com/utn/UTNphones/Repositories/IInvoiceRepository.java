package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice,Integer> {
    List<Invoice> findByPhonelineUserId(Integer userId);
   List<Invoice> findAllByPhonelineUserIdAndDateBetween(Integer userId, Date startDate, Date endDate);
}
