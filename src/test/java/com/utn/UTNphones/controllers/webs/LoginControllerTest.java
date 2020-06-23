package com.utn.UTNphones.controllers.webs;

import com.utn.UTNphones.controllers.UserController;
import com.utn.UTNphones.domains.User;
import com.utn.UTNphones.domains.dto.requests.ClientLoginDTO;
import com.utn.UTNphones.domains.dto.requests.EmployeeLoginDTO;
import com.utn.UTNphones.domains.dto.requests.Login;
import com.utn.UTNphones.services.UserService;
import com.utn.UTNphones.sessions.SessionManager;
import com.utn.UTNphones.utils.ObjectConverter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private LoginController loginController;

    //@Mock
    private LoginController loginMock;

    @Mock
    private UserController userController;

    @Mock
    private SessionManager sessionManager;

    @Mock
    private UserService userService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        loginMock = new LoginController(userController, sessionManager);
    }

    @Test
    public void loginOk() throws Exception {
        Login client = ClientLoginDTO.builder().identification("12345678").password("12345").build();
        String requestJson = ObjectConverter.converter(client);
        when(userController.login(client)).thenReturn(User.fromLoginDto(client));
        when(sessionManager.createSession(User.fromLoginDto(client))).thenReturn("I am the token");
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("I am the token"))
                .andReturn();
    }

    @Test
    public void loginEmployeeOk() throws Exception {
        Login client = EmployeeLoginDTO.builder().identification("12345678").password("12345").build();
        String requestJson = ObjectConverter.converter(client);
        when(userController.login(client)).thenReturn(User.fromLoginDto(client));
        when(sessionManager.createSession(User.fromLoginDto(client))).thenReturn("I am the token");
        mockMvc.perform(post("/Employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("I am the token"))
                .andReturn();
    }

    @Test
    public void logoutOk() throws Exception {
        String requestJson = "oCodigoMaisComplicadoDoMundo";
        doNothing().when(sessionManager).removeSession(requestJson);
        mockMvc.perform(post("/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "oCodigoMaisComplicadoDoMundo"))
                .andExpect(status().isOk());
    }

    @Test
    public void createHeaderOk() throws Exception {
        String token = "Auth code";
        HttpHeaders response = new HttpHeaders();
        response.set("Authorization", token);
        assertEquals(loginMock.createHeaders("Auth code"), response);

    }
}
