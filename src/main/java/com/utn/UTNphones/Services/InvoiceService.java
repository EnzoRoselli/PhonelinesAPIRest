package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Repositories.IInvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final IInvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public List<Invoice> getAllByUserId(Integer userId) {
        return this.invoiceRepository.findByPhonelineUserId(userId);
    }

    public List<Invoice> getByUserAndBetweenDates(Integer id, SearchBetweenDatesDTO datesDto) {
        return this.invoiceRepository.findAllByPhonelineUserIdAndDateBetween(id, datesDto.getStart(), datesDto.getEnd());
    }

    public List<Invoice> getByUserEndDate(Integer id, Date endDate) {
        return this.invoiceRepository.findAllByPhonelineUserIdAndDateBefore(id, endDate);
    }

    public List<Invoice> getByUserStartDate(Integer id, Date startDate) {
        return this.invoiceRepository.findAllByPhonelineUserIdAndDateAfter(id, startDate);
    }
}
