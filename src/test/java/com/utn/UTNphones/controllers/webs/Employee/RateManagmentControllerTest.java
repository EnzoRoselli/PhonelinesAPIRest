package com.utn.UTNphones.controllers.webs.Employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.UTNphones.controllers.RateController;
import com.utn.UTNphones.controllers.webs.AdviceController;
import com.utn.UTNphones.domains.City;
import com.utn.UTNphones.domains.Rate;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RateManagmentControllerTest {
    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    RateManagementController rateManagementController;

    @Mock
    RateController rateController;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rateManagementController)
                .setControllerAdvice(new AdviceController())
                .build();
    }

    @Test
    public void findByOriginAndDestination() throws Exception {
        List<Rate> rates = new ArrayList<>();
        Rate rate1 = ObjectCreator.createRate();
        rates.add(rate1);
        City city1 = ObjectCreator.createCity();
        City city2 = ObjectCreator.createCity();


        when(rateController.findByOriginAndDestination(city1.getId(), city2.getId())).thenReturn(ObjectCreator.createRate());
        MvcResult result = mockMvc.perform(get("/employee/rates?OriginCity=1&DestinationCity=1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isOk())
                .andReturn();

        List u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(u.size(), rates.size());

    }

    @Test
    public void findByOrigin() throws Exception {
        List<Rate> rates = new ArrayList<>();
        rates.add(ObjectCreator.createRate());
        rates.add(ObjectCreator.createRate());
        City city1 = ObjectCreator.createCity();

        when(rateController.findByOrigin(city1.getId())).thenReturn(rates);
        MvcResult result = mockMvc.perform(get("/employee/rates?OriginCity=1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isOk())
                .andReturn();

        List u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(u.size(), rates.size());

    }

    @Test
    public void findByDestination() throws Exception {
        List<Rate> rates = new ArrayList<>();
        Rate rate1 = ObjectCreator.createRate();
        Rate rate2 = ObjectCreator.createRate();
        rates.add(rate1);
        rates.add(rate2);
        City city1 = ObjectCreator.createCity();

        when(rateController.findByDestination(city1.getId())).thenReturn(rates);
        MvcResult result = mockMvc.perform(get("/employee/rates?DestinationCity=1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isOk())
                .andReturn();

        List u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(u.size(), rates.size());

    }

    @Test
    public void getAllRates() throws Exception {
        List<Rate> rates = new ArrayList<>();
        Rate rate1 = ObjectCreator.createRate();
        Rate rate2 = ObjectCreator.createRate();
        rates.add(rate1);
        rates.add(rate2);

        when(rateController.getAllRates()).thenReturn(rates);
        MvcResult result = mockMvc.perform(get("/employee/rates")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isOk())
                .andReturn();

        List u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(u.size(), rates.size());

    }
    @Test
    public void getAllRatesEmpty() throws Exception {
        List<Rate> rates = new ArrayList<>();


        when(rateController.getAllRates()).thenReturn(rates);
        MvcResult result = mockMvc.perform(get("/employee/rates")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isNoContent())
                .andReturn();

    }
}
