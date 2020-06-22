package com.utn.UTNphones.Controllers.Webs.Employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.UTNphones.Controllers.InvoiceController;
import com.utn.UTNphones.Controllers.Webs.AdviceController;
import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Utils.ObjectCreator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InvoicesManagmentControllerTest {

    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    InvoicesManagementController invoicesManagementController;

    @Mock
    InvoiceController invoiceController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invoicesManagementController)
                .setControllerAdvice(new AdviceController())
                .build();
    }

    @Test
    public void getByUserId() throws Exception {
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(ObjectCreator.createInvoice());
        when(invoiceController.findByUserId(1)).thenReturn(invoices);
        MvcResult result = mockMvc.perform(get("/employee/invoices/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isOk())
                .andReturn();

        List u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(u.size(), invoices.size());

    }

    @Test
    public void getByUserIdBetweenDates() throws Exception {
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(ObjectCreator.createInvoice());
        invoices.add(ObjectCreator.createInvoice());
        when(invoiceController.findByUserBetweenDates(1, SearchBetweenDatesDTO.fromDates(LocalDate.of(2020, 1, 5), LocalDate.now()))).thenReturn(invoices);
        MvcResult result = mockMvc.perform(get("/employee/invoices/1?startDate=2020-01-05")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isOk())
                .andReturn();

        List u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(u.size(), invoices.size());

    }
}
