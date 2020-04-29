package com.utn.UTNphones.Service;

import com.utn.UTNphones.Repository.IInvoiceRepository;
import com.utn.UTNphones.Service.interfaces.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService implements IInvoiceService {

    private final IInvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(IInvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
}
