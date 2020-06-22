package com.utn.UTNphones.Controllers.Webs.Client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Controllers.InvoiceController;
import com.utn.UTNphones.Controllers.Webs.AdviceController;
import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Sessions.SessionManager;
import com.utn.UTNphones.Utils.ObjectCreator;
import io.swagger.models.auth.In;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InvoiceClientControllerTest {
    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    InvoicesClientController invoicesClientController;

    @Mock
    InvoiceController invoiceController;

    @Mock
    SessionManager sessionManager;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invoicesClientController)
                .setControllerAdvice(new AdviceController())
                .build();
    }
    @Test
    public void getByUserIdBetweenDates() throws Exception {
        User user = User.builder().id(1).build();
        List<Invoice> invoiceList=new ArrayList<>();
        invoiceList.add(ObjectCreator.createInvoice());
        invoiceList.add(ObjectCreator.createInvoice());
        Integer userId = 1;
        when(sessionManager.getCurrentUser("token")).thenReturn(Optional.ofNullable(user));
        when(invoiceController.findByUserBetweenDates(userId,
                SearchBetweenDatesDTO.fromDates(LocalDate.of(2020, 1, 2),
               LocalDate.now()))).thenReturn(invoiceList);
        MvcResult result = mockMvc.perform(get("/client/invoices?startDate=2020-01-02")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "token"))
                .andExpect(status().isOk())
                .andReturn();
        List u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(u.size(), invoiceList.size());

    }
}
