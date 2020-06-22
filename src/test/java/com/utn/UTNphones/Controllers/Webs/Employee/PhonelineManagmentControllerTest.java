package com.utn.UTNphones.Controllers.Webs.Employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.UTNphones.Controllers.PhonelineController;
import com.utn.UTNphones.Controllers.Webs.AdviceController;
import com.utn.UTNphones.Domains.Dto.Requests.PhonelineDTO;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Utils.ObjectConverter;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PhonelineManagmentControllerTest {

    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    PhonelineManagementController phonelineManagementController;

    @Mock
    PhonelineController phonelineController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(phonelineManagementController)
                .setControllerAdvice(new AdviceController())
                .build();
    }

    @Test
    public void register() throws Exception {
        PhonelineDTO phonelineDTO = ObjectCreator.createPhonelineDTO();
        Phoneline phoneline = ObjectCreator.createPhoneline();
        String requestJson = ObjectConverter.converter(phonelineDTO);
        when(phonelineController.add(phonelineDTO)).thenReturn(phoneline);
        mockMvc.perform(post("/employee/phonelines")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token")
                .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/employee/phonelines/1"))
                .andReturn();
    }

    @Test
    public void getPhoneline() throws Exception {
        Phoneline phoneline = ObjectCreator.createPhoneline();
        when(phonelineController.getById(phoneline.getId())).thenReturn(phoneline);
        MvcResult result = mockMvc.perform(get("/employee/phonelines/" + phoneline.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isOk())
                .andReturn();
        Phoneline aux = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Phoneline.class);
        assertEquals(aux, phoneline);
    }

    @Test
    public void delete() throws Exception {
        Phoneline phoneline = ObjectCreator.createPhoneline();
        when(phonelineController.remove(phoneline.getId())).thenReturn(phoneline);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/employee/phonelines/" + phoneline.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isOk())
                .andReturn();
        Phoneline aux = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Phoneline.class);
        assertEquals(aux, phoneline);
    }

    @Test
    public void update() throws Exception {
        PhonelineDTO phonelineDTO = ObjectCreator.createPhonelineDTO();
        String json = ObjectConverter.converter(phonelineDTO);
        Phoneline phoneline = ObjectCreator.createPhoneline();
        when(phonelineController.update(phoneline.getId(), phonelineDTO)).thenReturn(phoneline);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put("/employee/phonelines/" + phoneline.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token")
                .content(json))
                .andExpect(status().isOk())
                .andReturn();
        Phoneline aux = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Phoneline.class);
        assertEquals(aux, phoneline);
    }
}
