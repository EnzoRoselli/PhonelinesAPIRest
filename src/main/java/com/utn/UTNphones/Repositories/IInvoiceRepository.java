package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Domains.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findByPhonelineUserId(Integer userId);

    List<Invoice> findAllByPhonelineUserIdAndDateBetween(Integer userId, Date startDate, Date endDate);

    List<Invoice> findAllByPhonelineUserIdAndDateAfter(Integer id, Date startDate);

    List<Invoice> findAllByPhonelineUserIdAndDateBefore(Integer id, Date endDate);
}
