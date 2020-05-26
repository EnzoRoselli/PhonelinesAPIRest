package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domain.Dto.SearchBetweenDates;
import com.utn.UTNphones.Domain.Invoice;
import com.utn.UTNphones.Repositories.IInvoiceRepository;
import com.utn.UTNphones.Services.interfaces.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {

    private final IInvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(IInvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> getAllByUserId(Integer userId) {
        return this.invoiceRepository.findByPhonelineUserId(userId);
    }

    @Override
    public List<Invoice> getByUserAndBetweenDates(Integer id, SearchBetweenDates datesDto) {
        return this.invoiceRepository.findAllByPhonelineUserIdAndDateBetween(id,datesDto.getStart(),datesDto.getEnd());
    }
}
