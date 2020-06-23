package com.utn.UTNphones.controllers.webs.Infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.utn.UTNphones.controllers.CallController;
import com.utn.UTNphones.domains.dto.requests.NewCallDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CallRegisterControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CallRegisterController callRegisterController;
    @Mock
    private CallController callController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(callRegisterController).build();
    }

    @Test
    public void registerCallOk() throws Exception {
        NewCallDTO newCallDTO = NewCallDTO.builder().date(Date.valueOf(LocalDate.now()))
                .destinationNumber("121123122").originNumber("3232323232").duration(1).build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(newCallDTO);

        mockMvc.perform(post("/infrastructure/registerCall")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .header("INFRASTRUCTURE_KEY", "abc123MuySeguro"))
                .andExpect(status().isOk()).andReturn();
    }
}
