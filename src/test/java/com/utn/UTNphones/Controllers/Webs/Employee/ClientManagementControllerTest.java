package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Controllers.Webs.AdviceController;
import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserIdentificationAlreadyExists;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeDoesntExist;
import com.utn.UTNphones.Sessions.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    public void setUp(){
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ClientManagementController(userController, sessionManager))
                .setControllerAdvice(new AdviceController()).build();
    }

    @Test
    public void registerOk() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User employee = User.builder().type("employee").build();
        User userToRegister = User.builder().name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("client").city(new City()).build();
        User userRegistered = User.builder().id(1).name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("client").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.register(userToRegister)).thenReturn(userRegistered);

        ResponseEntity responseEntity = clientManagementController.register("token", userToRegister);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(clientManagementController.getLocation(userRegistered), responseEntity.getHeaders().getLocation());
    }

    @Test
    public void registerForbidden() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User client = User.builder().type("client").build();
        User userToRegister = User.builder().name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("client").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(client));

        ResponseEntity responseEntity = clientManagementController.register("token", userToRegister);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test(expected = CityDoesntExist.class)
    public void registerCityDoesntExistException() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User employee = User.builder().type("employee").build();
        User userToRegister = User.builder().name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("client").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.register(userToRegister)).thenThrow(new CityDoesntExist());

        ResponseEntity responseEntity = clientManagementController.register("token", userToRegister);
    }

//    @Test
//    public void registerCityDoesntExist() throws Exception {
//        String token = "1";
//        User userToRegister = User.builder().name("Enzo").lastname("Mateu").identification("111111")
//                .password("abc").type("client").city(new City()).build();
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("")
//                 .header("Authorization", token)
//                 .requestAttr("userRegistering", userToRegister))
//                 .andExpect(status().is4xxClientError()
//        );
//
//    }

    @Test(expected = UserIdentificationAlreadyExists.class)
    public void registerUserIdentificationAlreadyExistsException() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User employee = User.builder().type("employee").build();
        User userToRegister = User.builder().name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("client").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.register(userToRegister)).thenThrow(new UserIdentificationAlreadyExists());

        ResponseEntity responseEntity = clientManagementController.register("token", userToRegister);
    }

    @Test(expected = UserTypeDoesntExist.class)
    public void registerUserTypeDoesntExistException() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User employee = User.builder().type("employee").build();
        User userToRegister = User.builder().name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("adssad").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.register(userToRegister)).thenThrow(new UserTypeDoesntExist());

        ResponseEntity responseEntity = clientManagementController.register("token", userToRegister);
    }

    @Test(expected = Exception.class)
    public void registerException() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User employee = User.builder().type("employee").build();
        User userToRegister = User.builder().name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("adssad").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.register(userToRegister)).thenThrow(new Exception());

        ResponseEntity responseEntity = clientManagementController.register("token", userToRegister);
    }

    @Test
    public void getUserOk() throws UserDoesntExist {
        User employee = User.builder().type("employee").build();
        User userReturned = User.builder().id(1).name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("client").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.findById(1)).thenReturn(userReturned);

        ResponseEntity<User> responseEntity = clientManagementController.getUser("token", 1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userReturned, responseEntity.getBody());
    }

    @Test
    public void getUserForbidden() throws Exception {
        User client = User.builder().type("client").build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(client));
        ResponseEntity<User> responseEntity = clientManagementController.getUser("token", 1);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test(expected = UserDoesntExist.class)
    public void getUserUserDoesntExistException() throws Exception {
        User employee = User.builder().type("employee").build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.findById(1)).thenThrow(new UserDoesntExist());

        ResponseEntity<User> responseEntity = clientManagementController.getUser("token", 1);
    }

    @Test
    public void DeleteOk() throws UserDoesntExist {
        String identification = "111111";
        User employee = User.builder().type("employee").build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));

        doNothing().when(userController).delete(identification);

        ResponseEntity<User> responseEntity = clientManagementController.delete("token", identification);

        verify(userController, times(1)).delete(identification);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void DeleteForbidden() throws Exception {
        User client = User.builder().type("client").build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(client));

        ResponseEntity<User> responseEntity = clientManagementController.delete("token", "111111");

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test(expected = UserDoesntExist.class)
    public void DeleteUserDoesntExistException() throws UserDoesntExist {
        String identification = "111111";
        User employee = User.builder().type("employee").build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        doThrow(new UserDoesntExist()).when(userController).delete(identification);

        clientManagementController.delete("token", identification);
    }

    @Test
    public void updateOk() throws Exception {
        User employee = User.builder().type("employee").build();
        User user = User.builder().id(1).name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("client").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.update(user)).thenReturn(user);

        ResponseEntity<User> responseEntity = clientManagementController.update("token", user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateForbidden() throws Exception {
        User client = User.builder().type("client").build();
        User user = User.builder().id(1).name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("client").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(client));
        ResponseEntity<User> responseEntity = clientManagementController.update("token", user);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test(expected = CityDoesntExist.class)
    public void updateCityDoesntExistException() throws Exception {
        User employee = User.builder().type("employee").build();
        User user = User.builder().id(1).name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("client").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.update(user)).thenThrow(new CityDoesntExist());

        ResponseEntity<User> responseEntity = clientManagementController.update("token", user);
    }

    @Test(expected = UserIdentificationAlreadyExists.class)
    public void updateUserIdentificationAlreadyExistsException() throws Exception {
        User employee = User.builder().type("employee").build();
        User user = User.builder().id(1).name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("client").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.update(user)).thenThrow(new UserIdentificationAlreadyExists());

        ResponseEntity<User> responseEntity = clientManagementController.update("token", user);
    }

    @Test(expected = UserTypeDoesntExist.class)
    public void updateUserTypeDoesntExistException() throws Exception {
        User employee = User.builder().type("employee").build();
        User user = User.builder().id(1).name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("asdasd").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.update(user)).thenThrow(new UserTypeDoesntExist());

        ResponseEntity<User> responseEntity = clientManagementController.update("token", user);
    }

    @Test(expected = Exception.class)
    public void updateException() throws Exception {
        User employee = User.builder().type("employee").build();
        User user = User.builder().id(1).name("Enzo").lastname("Mateu").identification("111111")
                .password("abc").type("asdasd").city(new City()).build();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(userController.update(user)).thenThrow(new Exception());

        ResponseEntity<User> responseEntity = clientManagementController.update("token", user);
    }
}
