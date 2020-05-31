package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domains.Dto.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;

import java.util.List;

public interface IInvoiceService {

    List<Invoice> getAllInvoices();

    List<Invoice> getAllByUserId(Integer userId);

    List<Invoice> getByUserAndBetweenDates(Integer id, SearchBetweenDatesDTO datesDto);
}
