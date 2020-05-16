package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Models.Invoice;

import java.util.List;

public interface IInvoiceService {

    List<Invoice> getAllInvoices();

    List<Invoice> getAllByUserId(Integer userId);
}
