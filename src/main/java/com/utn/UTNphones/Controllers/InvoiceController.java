package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Models.Invoice;
import com.utn.UTNphones.Models.Rate;
import com.utn.UTNphones.Services.interfaces.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoice/")
public class InvoiceController {

    private final IInvoiceService invoiceService;

    @Autowired
    public InvoiceController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("getAll/")
    public List<Invoice> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }

}
