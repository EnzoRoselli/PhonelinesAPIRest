package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Services.InvoiceService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class InvoiceControllerTest {

    InvoiceController invoiceController;
    @Mock
    InvoiceService invoiceService;

    @Before
    public void setUp() {
        initMocks(this);
        invoiceController = new InvoiceController(invoiceService);
    }

    @Test
    public void getByUserBetweenDatesOk() {
        Integer userId = 1;
        Phoneline phoneline = Phoneline.builder().user(User.builder().id(userId).build()).build();
        SearchBetweenDatesDTO dates = SearchBetweenDatesDTO.builder().end(LocalDate.now()).
                start(LocalDate.of(2020, 1, 1)).build();
        Invoice invoice = Invoice.builder().phoneline(phoneline).id(2).build();
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);
        when(invoiceService.getByUserAndBetweenDates(userId, dates)).thenReturn(invoices);
        List<Invoice> invoicesDb = invoiceController.findByUserBetweenDates(userId, dates);
        assertEquals(invoices, invoicesDb);

    }

    @Test
    public void getByUserIdOk() {
        Integer userId = 1;
        Phoneline phoneline1 = Phoneline.builder().number("12345678").user(User.builder().id(userId).build()).build();
        Phoneline phoneline2 = Phoneline.builder().number("8765421").user(User.builder().id(userId).build()).build();
        Invoice invoice1 = Invoice.builder().phoneline(phoneline1).id(1).build();
        Invoice invoice2 = Invoice.builder().phoneline(phoneline2).id(2).build();
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice1);
        invoices.add(invoice2);
        when(invoiceService.getAllByUserId(userId)).thenReturn(invoices);
        List<Invoice> invoicesDb = invoiceController.findByUserId(userId);
        assertEquals(invoices, invoicesDb);

    }

}
