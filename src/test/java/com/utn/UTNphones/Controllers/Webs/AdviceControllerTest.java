package com.utn.UTNphones.Controllers.Webs;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Controllers.PhonelineController;
import com.utn.UTNphones.Controllers.RateController;
import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Controllers.Webs.Employee.CallsManagementController;
import com.utn.UTNphones.Controllers.Webs.Employee.ClientManagementController;
import com.utn.UTNphones.Controllers.Webs.Employee.PhonelineManagementController;
import com.utn.UTNphones.Controllers.Webs.Employee.RateManagementController;
import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.Dto.Requests.EmployeeLoginDTO;
import com.utn.UTNphones.Domains.Dto.Requests.Login;
import com.utn.UTNphones.Domains.Dto.Requests.PhonelineDTO;
import com.utn.UTNphones.Domains.Dto.Requests.UserDTO;
import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.CityExceptions.CityNotExists;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.IlegalUserForPhoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineAlreadyExists;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDigitsCountPlusPrefix;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineNotExists;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineTypeError;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Exceptions.RateExceptions.RateNotExists;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserNotExists;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeNotExists;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeWithIdentificationAlreadyExists;
import com.utn.UTNphones.Sessions.SessionManager;
import com.utn.UTNphones.Utils.ObjectConverter;
import com.utn.UTNphones.Utils.ObjectCreator;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdviceControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LoginController loginController;

    @InjectMocks
    private RateManagementController rateManagementController;

    @InjectMocks
    private CallsManagementController callsManagementController;

    @Mock
    private CallController callController;

    @Mock
    private RateController rateController;


    @InjectMocks
    private ClientManagementController clientManagementController;

    @InjectMocks
    private PhonelineManagementController phonelineManagementController;

    @Mock
    private UserController userController;

    @Mock
    private PhonelineController phonelineController;

    @Mock
    private SessionManager sessionManager;


    public AdviceControllerTest() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(callsManagementController,rateManagementController, phonelineManagementController, loginController, clientManagementController)
                .setControllerAdvice(new AdviceController())
                .build();
    }

    @Test
    public void userDoesNotExists() throws Exception {
        Login client = ObjectCreator.createClientLoginDTO();
        String requestJson = ObjectConverter.converter(client);
        when(userController.login(client)).thenReturn(User.fromLoginDto(client));
        when(sessionManager.createSession(User.fromLoginDto(client))).thenReturn("I am the token");
        when(loginController.login(ObjectCreator.createClientLoginDTO())).thenThrow(new UserNotExists());
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void LogException() throws Exception {
        Login employee = ObjectCreator.createEmployeeLoginDTO();
        String requestJson = ObjectConverter.converter(employee);
        when(userController.login(employee)).thenReturn(User.fromLoginDto(employee));
        when(sessionManager.createSession(User.fromLoginDto(employee))).thenReturn("I am the token");
        when(loginController.login(ObjectCreator.createClientLoginDTO())).thenThrow(new LogException());
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    public void cityNotExists() throws Exception {
        UserDTO user = ObjectCreator.createUserDTO();
        String requestJson = ObjectConverter.converter(user);
        when(userController.register(user)).thenThrow(new CityNotExists());
        mockMvc.perform(post("/employee/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Token")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void MethodArgumentNotValid() throws Exception {
        UserDTO user = ObjectCreator.createUserDTO();
        user.setType("aaa");
        String requestJson = ObjectConverter.converter(user);
        when(userController.register(user)).thenThrow(new UserTypeNotExists());
        mockMvc.perform(post("/employee/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Token")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void UserTypeWithIdentificationAlreadyExists() throws Exception {
        UserDTO user2 = ObjectCreator.createUserDTO();
        String requestJson = ObjectConverter.converter(user2);
        when(userController.register(user2)).thenThrow(new UserTypeWithIdentificationAlreadyExists());
        mockMvc.perform(post("/employee/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Token")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void PhonelineAlreadyExists() throws Exception {
        PhonelineDTO phonelineDTO2 = ObjectCreator.createPhonelineDTO();
        String requestJson = ObjectConverter.converter(phonelineDTO2);
        when(phonelineController.add(phonelineDTO2)).thenThrow(new PhonelineAlreadyExists());
        mockMvc.perform(post("/employee/phonelines/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Token")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void PhonelineDigitsCountPlusPrefix() throws Exception {
        PhonelineDTO phoneline = ObjectCreator.createPhonelineDTO();
        phoneline.setNumber("123");

        String requestJson = ObjectConverter.converter(phoneline);
        when(phonelineController.add(phoneline)).thenThrow(new PhonelineDigitsCountPlusPrefix());
        mockMvc.perform(post("/employee/phonelines/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Token")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void PhonelineNotExists() throws Exception {
        PhonelineDTO phonelineDTO2 = ObjectCreator.createPhonelineDTO();
        String requestJson = ObjectConverter.converter(phonelineDTO2);
        when(phonelineController.getById(12)).thenThrow(new PhonelineNotExists());
        mockMvc.perform(get("/employee/phonelines/12")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Token")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void IlegalUserForPhoneline() throws Exception {
        PhonelineDTO phonelineDTO = ObjectCreator.createPhonelineDTO();
        EmployeeLoginDTO employeeLoginDTO = ObjectCreator.createEmployeeLoginDTO();
        User user = User.fromLoginDto(employeeLoginDTO);
        user.setType("employee");
        user.setId(1);
        phonelineDTO.setUserId(user.getId());
        String requestJson = ObjectConverter.converter(phonelineDTO);
        when(phonelineController.add(phonelineDTO)).thenThrow(new IlegalUserForPhoneline());
        mockMvc.perform(post("/employee/phonelines")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Token")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void PhonelineTypeError() throws Exception {
        PhonelineDTO phonelineDTO = ObjectCreator.createPhonelineDTO();
        phonelineDTO.setType("randomTypeError");
        String requestJson = ObjectConverter.converter(phonelineDTO);
        when(phonelineController.add(phonelineDTO)).thenThrow(new PhonelineTypeError());
        mockMvc.perform(post("/employee/phonelines")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Token")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    public void MethodArgumentNotValidException() throws Exception {
        PhonelineDTO phonelineDTO = ObjectCreator.createPhonelineDTO();
        phonelineDTO.setType(null);
        String requestJson = ObjectConverter.converter(phonelineDTO);
        BindingResult bindingResult = new MapBindingResult(Collections.singletonMap("a", "b"), "phoneline");
        bindingResult.addError(new ObjectError("type", "type is mandatory"));
        bindingResult.addError(new ObjectError("type", "type must not be null"));
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
        when(phonelineController.add(phonelineDTO)).thenThrow(ex);
        mockMvc.perform(post("/employee/phonelines")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Token")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void RateNotExists() throws Exception {
        Rate rate = ObjectCreator.createRate();
        String requestJson = ObjectConverter.converter(rate);
        when(rateController.findByOriginAndDestination(rate.getOriginCity().getId(),2)).thenThrow(new RateNotExists());
        mockMvc.perform(get("/employee/rates?OriginCity=1&DestinationCity=2")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Token")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void PhonelinesNotRegisteredByUser() throws Exception {
        Integer userId = 1;
        String requestJson = ObjectConverter.converter(userId);
        when(callController.getCallsByUserId(userId)).thenThrow(new PhonelinesNotRegisteredByUser());
        mockMvc.perform(get("/employee/calls/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Token")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


}
