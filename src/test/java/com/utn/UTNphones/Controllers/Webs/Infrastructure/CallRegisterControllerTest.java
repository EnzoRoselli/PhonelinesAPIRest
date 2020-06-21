package com.utn.UTNphones.Controllers.Webs.Infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.utn.UTNphones.Domains.Dto.Requests.NewCallDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.time.LocalDate;

import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.INFRASTRUCTURE_MAPPING;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CallRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void registerCallOk() throws Exception {
        NewCallDTO newCallDTO = NewCallDTO.builder().date(Date.valueOf(LocalDate.now()))
                .destinationNumber("121123122").originNumber("3232323232").duration(1).build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(newCallDTO);

        mockMvc.perform(post(INFRASTRUCTURE_MAPPING).content(requestJson))
                .andExpect(header().string("INFRASTRUCTURE_KEY", "abc123MuySeguro"))
                .andExpect(status().isOk()).andReturn();

    }
}
