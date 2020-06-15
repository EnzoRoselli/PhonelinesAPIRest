package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Domains.Invoice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findByPhonelineUserId(Integer userId, Pageable pageable);

    List<Invoice> findAllByPhonelineUserIdAndDateBetweenOrderByIdDesc(Integer userId, Date startDate, Date endDate, Pageable pageable);

}
