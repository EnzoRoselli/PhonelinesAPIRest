package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    public List<Invoice> getAllByUserId(Integer userId) {
        return this.invoiceService.getAllByUserId(userId);
    }

    public List<Invoice> getByUserBetweenDates(Integer id, SearchBetweenDatesDTO datesDto) {
        return this.invoiceService.getByUserAndBetweenDates(id, datesDto);
    }

    public List<Invoice> getByUserEndDate(Integer id, Date endDate) {
        return this.invoiceService.getByUserEndDate(id, endDate);
    }

    public List<Invoice> getByUserStartDate(Integer id, Date startDate) {
        return this.invoiceService.getByUserStartDate(id, startDate);
    }
}
