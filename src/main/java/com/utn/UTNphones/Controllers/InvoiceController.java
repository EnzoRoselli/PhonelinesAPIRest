package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domain.Dto.SearchBetweenDates;
import com.utn.UTNphones.Domain.Invoice;
import com.utn.UTNphones.Services.interfaces.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InvoiceController {

    private final IInvoiceService invoiceService;

    @Autowired
    public InvoiceController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    public List<Invoice> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }


    public List<Invoice> getAllByUserId(Integer userId) {
            return this.invoiceService.getAllByUserId(userId);
    }

    public List<Invoice> getByUserBetweenDates(Integer id, SearchBetweenDates datesDto) {
        return this.invoiceService.getByUserAndBetweenDates(id,datesDto);
    }
}
