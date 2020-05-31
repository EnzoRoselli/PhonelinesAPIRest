package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Domains.Dto.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;

import java.util.Date;
import java.util.List;

public interface IInvoiceService {

    List<Invoice> getAllInvoices();

    List<Invoice> getAllByUserId(Integer userId);

    List<Invoice> getByUserAndBetweenDates(Integer id, SearchBetweenDatesDTO datesDto);

    List<Invoice> getByUserStartDate(Integer id, Date startDate);

    List<Invoice> getByUserEndDate(Integer id, Date endDate);
}
