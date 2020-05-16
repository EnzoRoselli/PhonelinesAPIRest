package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Models.Invoice;
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


    public List<Invoice> getAllByUserId(Integer userId) throws ParametersException {
        if (userId==null){
            throw new ParametersException("Parameter can´t contain null values");
        }

            return this.invoiceService.getAllByUserId(userId);
    }
}
