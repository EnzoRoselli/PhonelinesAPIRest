package com.utn.UTNphones.Controllers;


import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Services.CityService;
import com.utn.UTNphones.Services.PhonelineService;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PhonelineControllerTest {

    PhonelineController phonelineController;
    @Mock
    PhonelineService phonelineService;
    @Mock
    CityService cityService;
//
//    @Before
//    public void setUp(){
//        initMocks(this);
//        phonelineController = new PhonelineController(phonelineService, cityService);
//    }

    @Test
    public void getByIdOk() throws PhonelineDoesntExist {
        Phoneline aux = Phoneline.builder().id(1).number("1111111").type("mobile").status(true)
                .user(new User()).city(new City()).build();

        when(phonelineService.getById(1)).thenReturn(aux);
        Phoneline returnedPhoneline = phonelineController.getById(1);

        assertEquals(aux, returnedPhoneline);
    }

    @Test(expected = PhonelineDoesntExist.class)
    public void getByIdPhonelineDoesntExistException() throws PhonelineDoesntExist {
        when(phonelineService.getById(1)).thenThrow(new PhonelineDoesntExist());
        phonelineController.getById(1);
    }
//
//    @Test
//    public void AddOk() throws Exception {
//        City cityAux = City.builder().id(1).prefix("223").build();
//        User userAux = User.builder().id(1).build();
//
//        Phoneline phoneAux = Phoneline.builder().number("1111111").type("mobile").status(true)
//                .user(userAux).city(cityAux).build();
//
//        Phoneline userReturned = Phoneline.builder().id(1).number("1111111").type("mobile").status(true)
//                .user(userAux).city(cityAux).build();
//
//        when(phonelineService.add(phoneAux)).thenReturn(userReturned);
//        when(cityService.getById(phoneAux.getCity().getId())).thenReturn(cityAux);
//        Phoneline addedPhoneline = phonelineController.add(phoneAux);
//
//        assertEquals(userReturned, addedPhoneline);
//    }
//
//    @Test(expected = ParametersException.class)
//    public void AddParametersException() throws Exception {
//        City cityAux = City.builder().id(1).prefix("223").build();
//        User userAux = User.builder().id(1).build();
//
//        Phoneline phoneAux = Phoneline.builder().number("1111111").status(true)
//                .user(userAux).city(cityAux).build();
//
//        Phoneline addedPhoneline = phonelineController.add(phoneAux);
//    }
//
//    @Test(expected = CityDoesntExist.class)
//    public void AddCityDoesntExistException() throws Exception {
//        City cityAux = City.builder().id(25).prefix("223").build();
//        User userAux = User.builder().id(1).build();
//
//        Phoneline phoneAux = Phoneline.builder().number("1111111").type("mobile").status(true)
//                .user(userAux).city(cityAux).build();
//
//        when(cityService.getById(phoneAux.getCity().getId())).thenThrow(new CityDoesntExist());
//        Phoneline addedPhoneline = phonelineController.add(phoneAux);
//    }
//
//    @Test(expected = PhonelineDigitsCountPlusPrefix.class)
//    public void AddPhonelineDigitsCountPlusPrefixException() throws Exception {
//        City cityAux = City.builder().id(1).prefix("223456").build();
//        User userAux = User.builder().id(1).build();
//
//        Phoneline phoneAux = Phoneline.builder().number("1111111").type("mobile").status(true)
//                .user(userAux).city(cityAux).build();
//
//        when(cityService.getById(phoneAux.getCity().getId())).thenReturn(cityAux);
//        Phoneline addedPhoneline = phonelineController.add(phoneAux);
//    }
//
//    @Test(expected = UserDoesntExist.class)
//    public void AddUserDoesntExistException() throws Exception {
//        City cityAux = City.builder().id(1).prefix("223").build();
//        User userAux = User.builder().id(654).build();
//
//        Phoneline phoneAux = Phoneline.builder().number("1111111").type("mobile").status(true)
//                .user(userAux).city(cityAux).build();
//
//        when(phonelineService.add(phoneAux)).thenThrow(new DataAccessException
//                ("", new ConstraintViolationException("",new SQLException("", null, 1452), "")) {});
//
//        when(cityService.getById(phoneAux.getCity().getId())).thenReturn(cityAux);
//        phonelineController.add(phoneAux);
//    }
//
//    @Test(expected = PhonelineTypeError.class)
//    public void AddPhonelineTypeErrorException() throws Exception {
//        City cityAux = City.builder().id(1).prefix("223").build();
//        User userAux = User.builder().id(1).build();
//
//        Phoneline phoneAux = Phoneline.builder().number("1111111").type("asdds").status(true)
//                .user(userAux).city(cityAux).build();
//
//        when(phonelineService.add(phoneAux)).thenThrow(new DataAccessException
//                ("", new ConstraintViolationException("",new SQLException("", null, 1265), "")) {});
//
//        when(cityService.getById(phoneAux.getCity().getId())).thenReturn(cityAux);
//        phonelineController.add(phoneAux);
//    }
//
//    @Test(expected = PhonelineAlreadyExists.class)
//    public void AddPhonelineAlreadyExistsException() throws Exception {
//        City cityAux = City.builder().id(1).prefix("223").build();
//        User userAux = User.builder().id(1).build();
//
//        Phoneline phoneAux = Phoneline.builder().number("1111111").type("mobile").status(true)
//                .user(userAux).city(cityAux).build();
//
//        when(phonelineService.add(phoneAux)).thenThrow(new DataAccessException
//                ("", new ConstraintViolationException("",new SQLException("", null, 1062), "")) {});
//
//        when(cityService.getById(phoneAux.getCity().getId())).thenReturn(cityAux);
//        phonelineController.add(phoneAux);
//    }
//
//    @Test(expected = Exception.class)
//    public void AddException() throws Exception {
//        City cityAux = City.builder().id(1).prefix("223").build();
//        User userAux = User.builder().id(1).build();
//
//        Phoneline phoneAux = Phoneline.builder().number("1111111").type("mobile").status(true)
//                .user(userAux).city(cityAux).build();
//
//        when(phonelineService.add(phoneAux)).thenThrow(new DataAccessException
//                ("", new ConstraintViolationException("",new SQLException("", null, 1), "")) {});
//
//        when(cityService.getById(phoneAux.getCity().getId())).thenReturn(cityAux);
//        phonelineController.add(phoneAux);
//    }
//
//    @Test
//    public void removeOk() throws PhonelineDoesntExist {
//        String number = "1234567";
//        when(phonelineService.findByNumber(number)).thenReturn(new Phoneline());
//        doNothing().when(phonelineService).removeByNumber(number);
//
//        phonelineController.remove(number);
//        verify(phonelineService, times(1)).removeByNumber(number);
//    }
//
//    @Test(expected = PhonelineDoesntExist.class)
//    public void removePhonelineDoesntExistException() throws PhonelineDoesntExist {
//        String number = "1234567";
//        when(phonelineService.findByNumber(number)).thenThrow(new PhonelineDoesntExist());
//
//        phonelineController.remove(number);
//    }
}
