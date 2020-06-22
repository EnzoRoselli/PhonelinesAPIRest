package com.utn.UTNphones.Controllers.Webs.Employee;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Controllers.Webs.AdviceController;
import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Sessions.SessionManager;
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
    CallsManagementController callsManagementController;

    @Mock
    CallController callController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(callsManagementController)
                .setControllerAdvice(new AdviceController())
                .build();
    }

    @Test
    public void getUser() throws Exception {
        Call call1 = ObjectCreator.createCall();
        Call call2 = ObjectCreator.createCall();
        List calls=new ArrayList<>();
//        calls.add(call1);
//        calls.add(call2);

        when(callController.getCallsByUserId(1)).thenReturn(calls);
        MvcResult result = mockMvc.perform(get("/employee/calls/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isNoContent())
                .andReturn();
//TODO ver si puedo con lista
//       List callsAux= new ObjectMapper().readValue(result.getResponse().getContentAsString(),List.class);
//       Call aa=new ObjectMapper().readValue((String) callsAux.get(1),Call.class);
//        List<Call> llamadas = new ArrayList<Call>(callsAux);
//        assertEquals(llamadas,calls);

    }

}
