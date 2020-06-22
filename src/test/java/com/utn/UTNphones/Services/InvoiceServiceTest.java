package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Repositories.IInvoiceRepository;
import com.utn.UTNphones.Utils.ObjectCreator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public void testGetAllByUserIdOk() {
        List<Invoice> invoiceList = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        invoiceList.add(ObjectCreator.createInvoice());
        invoiceList.add(ObjectCreator.createInvoice());
        when(invoiceRepository.findByPhonelineUserId(2, pageable)).thenReturn(invoiceList);
        assertEquals(invoiceService.getAllByUserId(2), invoiceList);
    }

    @Test
    public void testGetByUserAndBetweenDatesOk() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(ObjectCreator.createInvoice());
        invoiceList.add(ObjectCreator.createInvoice());
        SearchBetweenDatesDTO dates = ObjectCreator.createSearchBetweenDatesDTO();
        when(invoiceRepository.findAllByPhonelineUserIdAndDateBetweenOrderByIdDesc(2, Date.valueOf(dates.getStart()), Date.valueOf(dates.getEnd()), pageable)).thenReturn(invoiceList);
        assertEquals(invoiceService.getByUserAndBetweenDates(2, dates), invoiceList);
    }

}
