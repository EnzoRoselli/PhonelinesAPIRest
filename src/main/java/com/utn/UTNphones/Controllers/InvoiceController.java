package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    public List<Invoice> getByUserBetweenDates(Integer id, SearchBetweenDatesDTO datesDto) {
        return this.invoiceService.getByUserAndBetweenDates(id, datesDto);
    }

}
