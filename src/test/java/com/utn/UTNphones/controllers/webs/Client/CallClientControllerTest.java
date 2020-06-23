package com.utn.UTNphones.controllers.webs.Client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.UTNphones.controllers.CallController;
import com.utn.UTNphones.controllers.webs.AdviceController;
import com.utn.UTNphones.domains.Call;
import com.utn.UTNphones.domains.User;
import com.utn.UTNphones.domains.dto.requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.domains.dto.responses.CityTop;
import com.utn.UTNphones.sessions.SessionManager;
import com.utn.UTNphones.utils.ObjectCreator;
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
        cityTopList.add(ObjectCreator.createCityTop());
        cityTopList.add(ObjectCreator.createCityTop());
        User user = User.builder().id(1).build();
        Integer userId = 1;
        when(sessionManager.getCurrentUser("token")).thenReturn(Optional.ofNullable(user));
        when(callController.getTopDestinationsCalled(userId)).thenReturn(cityTopList);
        MvcResult result = mockMvc.perform(get("/client/calls/mostDestinationsCalled")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "token"))
                .andExpect(status().isOk())
                .andReturn();
        List u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(u.size(), cityTopList.size());
    }
    @Test
    public void mostDestinationsCalledEmpty() throws Exception {
        List<CityTop> cityTopList = new ArrayList<>();
        User user = User.builder().id(1).build();
        Integer userId = 1;
        when(sessionManager.getCurrentUser("token")).thenReturn(Optional.ofNullable(user));
        when(callController.getTopDestinationsCalled(userId)).thenReturn(cityTopList);
        MvcResult result = mockMvc.perform(get("/client/calls/mostDestinationsCalled")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "token"))
                .andExpect(status().isNoContent())
                .andReturn();

    }

    @Test
    public void getCallsBetweenDates() throws Exception {
        List<Call> callList = new ArrayList<>();
        Call call1 = ObjectCreator.createCall();
        Call call2 = ObjectCreator.createCall();
        callList.add(call1);
        callList.add(call2);
        User user = User.builder().id(1).build();
        Integer userId = 1;
        when(sessionManager.getCurrentUser("token")).thenReturn(Optional.ofNullable(user));
        when(callController.getByUserBetweenDates(userId, SearchBetweenDatesDTO
                .fromDates(LocalDate.of(2020, 1, 2)
                        , LocalDate.now()))).thenReturn(callList);
        MvcResult result = mockMvc.perform(get("/client/calls?startDate=2020-01-02")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "token"))
                .andExpect(status().isOk())
                .andReturn();
        List u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(u.size(), callList.size());
    }
    @Test
    public void getCallsBetweenDatesEmpty() throws Exception {
        List<Call> callList = new ArrayList<>();
        User user = User.builder().id(1).build();
        Integer userId = 1;
        when(sessionManager.getCurrentUser("token")).thenReturn(Optional.ofNullable(user));
        when(callController.getByUserBetweenDates(userId, SearchBetweenDatesDTO
                .fromDates(LocalDate.of(2020, 1, 2)
                        , LocalDate.now()))).thenReturn(callList);
        MvcResult result = mockMvc.perform(get("/client/calls?startDate=2020-01-02")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "token"))
                .andExpect(status().isNoContent())
                .andReturn();

    }
}

