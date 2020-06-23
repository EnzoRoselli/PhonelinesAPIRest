package com.utn.UTNphones.controllers.webs.Employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.UTNphones.controllers.UserController;
import com.utn.UTNphones.controllers.webs.AdviceController;
import com.utn.UTNphones.domains.User;
import com.utn.UTNphones.domains.dto.requests.UserDTO;
import com.utn.UTNphones.sessions.SessionManager;
import com.utn.UTNphones.utils.ObjectConverter;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientManagementControllerTest {

    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    ClientManagementController clientManagementController;

    @Mock
    UserController userController;

    @Mock
    SessionManager sessionManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientManagementController)
                .setControllerAdvice(new AdviceController())
                .build();
    }

    @Test
    public void getUser() throws Exception {
        User client = ObjectCreator.createEmployeeUser();
        when(userController.findById(1)).thenReturn(client);
        MvcResult result = mockMvc.perform(get("/employee/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isOk())
                .andReturn();

        User u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), User.class);
        assertEquals(u, client);

    }

    @Test
    public void register() throws Exception {
        UserDTO userDto = ObjectCreator.createUserDTO();
        String requestJson = ObjectConverter.converter(userDto);
        User user = ObjectCreator.createClientUser();
        when(userController.register(userDto)).thenReturn(user);
        mockMvc.perform(post("/employee/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token")
                .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/employee/users/1"))
                .andReturn();

    }

    @Test
    public void delete() throws Exception {
        User user = ObjectCreator.createEmployeeUser();
        when(userController.delete(1)).thenReturn(user);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/employee/users/{userId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token"))
                .andExpect(status().isOk())
                .andReturn();

        User u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), User.class);
        assertEquals(u, user);
    }

    @Test
    public void update() throws Exception {
        UserDTO userDto = ObjectCreator.createUserDTO();
        String requestJson = ObjectConverter.converter(userDto);
        User user = ObjectCreator.createEmployeeUser();
        when(userController.update(1, userDto)).thenReturn(user);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/employee/users/{userId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "I am the token")
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        User u = new ObjectMapper().readValue(result.getResponse().getContentAsString(), User.class);
        assertEquals(u, user);

    }

}
