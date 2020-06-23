package com.utn.UTNphones.services;

import com.utn.UTNphones.domains.dto.requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.domains.Invoice;
import com.utn.UTNphones.repositories.IInvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final IInvoiceRepository invoiceRepository;

    private final Pageable pageable = PageRequest.of(0, 10);

    public List<Invoice> getAllByUserId(Integer userId) {
        return this.invoiceRepository.findByPhonelineUserId(userId, pageable);
    }

    public List<Invoice> getByUserAndBetweenDates(Integer id, SearchBetweenDatesDTO datesDto) {
        return this.invoiceRepository.findAllByPhonelineUserIdAndDateBetweenOrderByIdDesc(id, Date.valueOf(datesDto.getStart()), Date.valueOf(datesDto.getEnd()), pageable);
    }

}
