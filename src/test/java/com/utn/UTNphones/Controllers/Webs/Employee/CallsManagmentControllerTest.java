package com.utn.UTNphones.Controllers.Webs.Employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Controllers.Webs.AdviceController;
import com.utn.UTNphones.Domains.Call;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CallsManagmentControllerTest {
    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    CallManagementController callManagementController;

    @Mock
    CallController callController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(callManagementController)
                .setControllerAdvice(new AdviceController())
                .build();
    }

    @Test
    public void getUser() throws Exception {
        List calls = new ArrayList<>();
        calls.add(ObjectCreator.createCall());
        calls.add(ObjectCreator.createCall());

        when(callController.getCallsByUserId(1)).thenReturn(calls);
        MvcResult result = mockMvc.perform(get("/employee/calls/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isOk())
                .andReturn();

        List u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(u.size(), calls.size());
    }
    @Test
    public void getUserEmpty() throws Exception {
        List calls = new ArrayList<>();

        when(callController.getCallsByUserId(1)).thenReturn(calls);
        MvcResult result = mockMvc.perform(get("/employee/calls/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isNoContent())
                .andReturn();

    }

}
