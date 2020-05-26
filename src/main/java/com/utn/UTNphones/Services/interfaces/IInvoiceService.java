package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domain.Dto.SearchBetweenDates;
import com.utn.UTNphones.Domain.Invoice;

import java.util.List;

public interface IInvoiceService {

    List<Invoice> getAllInvoices();

    List<Invoice> getAllByUserId(Integer userId);

    List<Invoice> getByUserAndBetweenDates(Integer id, SearchBetweenDates datesDto);
}
