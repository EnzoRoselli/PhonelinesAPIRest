package com.utn.UTNphones.Controllers.Webs.Client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Controllers.Webs.AdviceController;
import com.utn.UTNphones.Domains.Dto.Responses.CityTop;
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

public class CallClientControllerTest {
    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    CallsClientController callsClientController;

    @Mock
    CallController callController;

    @Mock
    SessionManager sessionManager;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(callsClientController)
                .setControllerAdvice(new AdviceController())
                .build();
    }

    @Test
    public void mostDestinationsCalled() throws Exception {
        List<CityTop> cityTopList = new ArrayList<>();
        CityTop city1 = ObjectCreator.createCityTop();
        CityTop city2 = ObjectCreator.createCityTop();
        cityTopList.add(city1);
        cityTopList.add(city2);
        User user= User.builder().id(1).build();
        Integer userId = 1;
        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(user));
        when(callController.getTopDestinationsCalled(userId)).thenReturn(cityTopList);
        MvcResult result = mockMvc.perform(get("/client/calls/mostDestinationsCalled")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "token"))
                .andExpect(status().isOk())
                .andReturn();
        List u= new ObjectMapper().readValue(result.getResponse().getContentAsString(),List.class);
        assertEquals(u.size(),cityTopList.size());
    }
}

