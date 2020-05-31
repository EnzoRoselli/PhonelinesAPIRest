package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Dto.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Repositories.IInvoiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class InvoiceServiceTest {
    InvoiceService invoiceService;
    @Mock
    IInvoiceRepository invoiceRepository;
    @Before
    public void setUp() {
        initMocks(this);
        invoiceService = new InvoiceService(invoiceRepository);
    }

    @Test
    public void testGetAllInvoicesOk(){
        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice=Invoice.builder().callsQuantity(2).build();
        invoiceList.add(invoice);
        invoice=Invoice.builder().callsQuantity(5).build();
        invoiceList.add(invoice);
        when(invoiceRepository.findAll()).thenReturn(invoiceList);
        List<Invoice> invoicesDb=invoiceService.getAllInvoices();
        assertEquals(invoicesDb,invoiceList);
    }
    @Test
    public void testGetAllByUserIdOk(){
        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice=Invoice.builder().callsQuantity(2).build();
        invoiceList.add(invoice);
        invoice=Invoice.builder().callsQuantity(5).build();
        invoiceList.add(invoice);
        when(invoiceRepository.findByPhonelineUserId(2)).thenReturn(invoiceList);
        List<Invoice> invoicesDb=invoiceService.getAllByUserId(2);
        assertEquals(invoicesDb,invoiceList);
    }
    @Test
    public void testGetByUserAndBetweenDatesOk(){
        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice=Invoice.builder().callsQuantity(2).build();
        invoiceList.add(invoice);
        invoice=Invoice.builder().callsQuantity(5).build();
        invoiceList.add(invoice);
        Date dateStart=new Date(2020, Calendar.AUGUST,23);
        Date dateEnd=new Date(2021, Calendar.AUGUST,23);
        SearchBetweenDatesDTO dates= new SearchBetweenDatesDTO(dateStart,dateEnd);
        when(invoiceRepository.findAllByPhonelineUserIdAndDateBetween(2,dates.getStart(),dates.getEnd())).thenReturn(invoiceList);
        List<Invoice> invoicesDb=invoiceService.getByUserAndBetweenDates(2,dates);
        assertEquals(invoicesDb,invoiceList);
    }

}
