package com.utn.UTNphones.Controllers;


import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Dto.Requests.PhonelineDTO;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.CityExceptions.CityNotExists;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineAlreadyExists;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDigitsCountPlusPrefix;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineNotExists;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineTypeError;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserNotExists;
import com.utn.UTNphones.Services.CityService;
import com.utn.UTNphones.Services.PhonelineService;
import com.utn.UTNphones.Services.UserService;
import com.utn.UTNphones.Utils.ObjectCreator;
import org.hibernate.JDBCException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhonelineControllerTest {

    PhonelineController phonelineController;
    @Mock
    PhonelineService phonelineService;
    @Mock
    CityService cityService;
    @Mock
    UserService userService;


    @Before
    public void setUp() {
        initMocks(this);
        phonelineController = new PhonelineController(phonelineService, cityService, userService);
    }

    @Test
    public void getByIdOk() throws PhonelineNotExists {
        Phoneline aux = ObjectCreator.createPhoneline();
        when(phonelineService.getById(ObjectCreator.createPhoneline().getId())).thenReturn(aux);
        assertEquals(aux, phonelineController.getById(1));
    }

    @Test(expected = PhonelineNotExists.class)
    public void getByIdPhonelineDoesntExistException() throws PhonelineNotExists {
        when(phonelineService.getById(1)).thenThrow(new PhonelineNotExists());
        phonelineController.getById(1);
    }

    @Test
    public void AddOk() throws Exception {
          PhonelineDTO phoneAux = ObjectCreator.createPhonelineDTO();
          Phoneline userReturned = Phoneline.fromDto(phoneAux);
        when(phonelineService.add(userReturned)).thenReturn(userReturned);
        when(userService.findById(phoneAux.getUserId())).thenReturn(ObjectCreator.createClientUser());
        when(cityService.getById(phoneAux.getCityId())).thenReturn(ObjectCreator.createCity());

        assertEquals(userReturned, phonelineController.add(phoneAux));
    }

    @Test(expected = PhonelineAlreadyExists.class)
    public void AddCityDoesntExistException() throws Exception {
        City cityAux = City.builder().id(1).prefix("223").build();
        User userAux = User.builder().id(1).type("client").build();

        PhonelineDTO phoneAux = PhonelineDTO.builder().type("mobile").userId(1).cityId(1).number("1111111").type("mobile").status(true).build();

        Phoneline userReturned = Phoneline.builder().id(1).number("1111111").type("mobile").status(true)
                .user(userAux).city(cityAux).build();

        when(userService.findById(1)).thenReturn(userAux);
        when(cityService.getById(phoneAux.getCityId())).thenReturn(cityAux);
        when(phonelineService.add(Phoneline.fromDto(phoneAux))).thenThrow(new DataAccessException("", new JDBCException("", new SQLException("", null, 1062))) {
        });
        Phoneline addedPhoneline = phonelineController.add(phoneAux);
    }

    @Test(expected = PhonelineDigitsCountPlusPrefix.class)
    public void AddPhonelineDigitsCountPlusPrefixException() throws Exception {
        City cityAux = City.builder().id(1).prefix("123").build();
        User userAux = User.builder().id(1).build();

        PhonelineDTO phoneAux = PhonelineDTO.builder().type("mobile").userId(1).cityId(1).number("123").type("mobile").status(true).build();

        when(cityService.getById(phoneAux.getCityId())).thenReturn(cityAux);
        phonelineController.add(phoneAux);
        Phoneline addedPhoneline = phonelineController.add(phoneAux);
    }

    @Test(expected = PhonelineTypeError.class)
    public void AddPhonelineTypeErrorException() throws Exception {
        City cityAux = City.builder().id(1).prefix("223").build();
        User userAux = User.builder().id(1).type("client").build();

        PhonelineDTO phoneAux = PhonelineDTO.builder().type("asd").userId(1).cityId(1).number("1234567").status(true).build();

        when(phonelineService.add(Phoneline.fromDto(phoneAux))).thenThrow(new DataAccessException("", new JDBCException("", new SQLException("", null, 1265))) {
        });
        when(cityService.getById(phoneAux.getCityId())).thenReturn(cityAux);
        when(userService.findById(1)).thenReturn(userAux);
        phonelineController.add(phoneAux);
    }

    @Test(expected = Exception.class)
    public void AddException() throws Exception {
        City cityAux = City.builder().id(1).prefix("223").build();
        User userAux = User.builder().id(1).build();

        PhonelineDTO phoneAux = PhonelineDTO.builder().type("asd").userId(1).cityId(1).number("1234567").status(true).build();

        when(phonelineService.add(Phoneline.fromDto(phoneAux))).thenThrow(new DataAccessException("", new JDBCException("", new SQLException("", null, 9999))) {
        });

        when(cityService.getById(phoneAux.getCityId())).thenReturn(cityAux);
        when(userService.findById(1)).thenReturn(userAux);
        phonelineController.add(phoneAux);
    }

    @Test
    public void removeOk() throws PhonelineNotExists {
        Integer id = 123;
        doNothing().when(phonelineService).removeById(id);
        Phoneline ph = phonelineController.remove(id);
        verify(phonelineService, times(1)).removeById(id);
    }

    @Test
    public void UpdateOk() throws Exception {
        PhonelineDTO phoneAux = PhonelineDTO.builder().type("mobile").userId(1).cityId(1).number("1111111").type("mobile").status(true).build();
        Phoneline phoneline = Phoneline.fromDto(phoneAux);
        phoneline.setId(1);

        when(phonelineService.update(phoneline)).thenReturn(phoneline);
        Phoneline Phoneline = phonelineController.update(1, phoneAux);

        assertEquals(phoneline, Phoneline);
    }

    @Test(expected = Exception.class)
    public void updateException() throws Exception {
        PhonelineDTO phoneAux = PhonelineDTO.builder().type("mobile").userId(1).cityId(1).number("1111111").type("mobile").status(true).build();
        Phoneline phoneline = Phoneline.fromDto(phoneAux);
        phoneline.setId(1);

        when(phonelineService.add(Phoneline.fromDto(phoneAux))).thenThrow(new DataAccessException("", new JDBCException("", new SQLException("", null, 9999))) {
        });
        phonelineController.add(phoneAux);
    }

    @Test(expected = UserNotExists.class)
    public void updateUserDoesntExist() throws Exception {
        PhonelineDTO phoneAux = PhonelineDTO.builder().type("mobile").userId(123).cityId(123).number("1111111").type("mobile").status(true).build();
        Phoneline phoneline = Phoneline.fromDto(phoneAux);
        phoneline.setId(1);

        when(phonelineService.update(phoneline)).thenThrow(new DataAccessException("", new JDBCException("", new SQLException("", null, 1452))) {
        });
        phonelineController.update(1, phoneAux);
    }

    @Test(expected = Exception.class)
    public void updateException2() throws Exception {
        PhonelineDTO phoneAux = PhonelineDTO.builder().type("mobile").userId(123).cityId(123).number("1111111").type("mobile").status(true).build();
        Phoneline phoneline = Phoneline.fromDto(phoneAux);
        phoneline.setId(1);

        when(phonelineService.update(phoneline)).thenThrow(new DataAccessException("", new JDBCException("", new SQLException("", null, 123123))) {
        });
        phonelineController.update(1, phoneAux);
    }

    @Test(expected = CityNotExists.class)
    public void updateCityDoesntExist() throws Exception {
        PhonelineDTO phoneAux = PhonelineDTO.builder().type("mobile").userId(123).cityId(123).number("1111111").type("mobile").status(true).build();
        Phoneline phoneline = Phoneline.fromDto(phoneAux);
        phoneline.setId(1);

        when(phonelineService.update(phoneline)).thenThrow(new DataAccessException("", new EntityNotFoundException("Domains.City")) {
        });
        phonelineController.update(1, phoneAux);
    }

    @Test(expected = UserNotExists.class)
    public void updateUserDoesntExist2() throws Exception {
        PhonelineDTO phoneAux = PhonelineDTO.builder().type("mobile").userId(123).cityId(123).number("1111111").type("mobile").status(true).build();
        Phoneline phoneline = Phoneline.fromDto(phoneAux);
        phoneline.setId(1);

        when(phonelineService.update(phoneline)).thenThrow(new DataAccessException("", new EntityNotFoundException("Domains.User")) {
        });
        phonelineController.update(1, phoneAux);
    }

    @Test(expected = Exception.class)
    public void updateException3() throws Exception {
        PhonelineDTO phoneAux = PhonelineDTO.builder().type("mobile").userId(123).cityId(123).number("1111111").type("mobile").status(true).build();
        Phoneline phoneline = Phoneline.fromDto(phoneAux);
        phoneline.setId(1);

        when(phonelineService.update(phoneline)).thenThrow(new DataAccessException("", new EntityNotFoundException("External error")) {
        });
        phonelineController.update(1, phoneAux);
    }

}
