package com.utn.UTNphones.controllers;

import com.utn.UTNphones.domains.dto.requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.domains.Invoice;
import com.utn.UTNphones.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    public List<Invoice> findByUserBetweenDates(Integer id, SearchBetweenDatesDTO datesDto) {

        return this.invoiceService.getByUserAndBetweenDates(id, datesDto);
    }

    public List<Invoice> findByUserId(Integer id) {
        return this.invoiceService.getAllByUserId(id);
    }

}
