package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Domains.Invoice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findByPhonelineUserId(Integer userId, Pageable pageable);

    List<Invoice> findAllByPhonelineUserIdAndDateBetweenOrderByIdDesc(Integer userId, Date startDate, Date endDate, Pageable pageable);

    List<Invoice> findAllByPhonelineUserIdAndDateAfterOrderByIdDesc(Integer id, Date startDate, Pageable pageable);

    List<Invoice> findAllByPhonelineUserIdAndDateBeforeOrderByIdDesc(Integer id, Date endDate, Pageable pageable);
}
