package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Dto.Requests.EmployeeLoginDTO;
import com.utn.UTNphones.Domains.Dto.Requests.Login;
import com.utn.UTNphones.Domains.Province;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Repositories.IUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    UserService userService;
    @Mock
    IUserRepository userRepository;

    @Before
    public void setUp() {
        initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testLoginOk() throws LogException {
        Login aux = EmployeeLoginDTO.builder().identification("12345678").password("1234").build();

        User loggedUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("employee").status(true)
                .identification("12345678").password("1234").city(City.builder().name("Mar del Plata").build()).build();

        when(userRepository.findByIdentificationAndPasswordAndType(aux.getIdentification(), aux.getPassword(), aux.getType())).thenReturn(Optional.ofNullable(loggedUser));
        User u = userService.login(User.fromLoginDto(aux));

        assertEquals(u, loggedUser);
    }

    @Test(expected = LogException.class)
    public void testLoginLogException() throws LogException {
        User aux = User.builder().identification("1").password("1234").type("employee").status(false).build();

        User loggedUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("employee").status(false)
                .identification("1").password("1234").city(City.builder().name("Mar del Plata").build()).build();

        when(userRepository.findByIdentificationAndPasswordAndType(aux.getIdentification(), aux.getPassword(), aux.getType())).thenReturn(Optional.ofNullable(loggedUser));
        User u = userService.login(aux);

        assertEquals(u, loggedUser);
    }

    @Test
    public void testRegisterOk() throws UserDoesntExist, DataAccessException {
        City citySended = City.builder().id(1).build();
        User userSended = User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(citySended).build();

        Province province = Province.builder().id(1).name("Buenos Aires").build();
        City city = City.builder().id(1).name("Mar del Plata").prefix("223").province(province).build();
        User registeredUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(userSended)).thenReturn(registeredUser);

        User u = userService.register(userSended);

        assertEquals(u, registeredUser);
    }

    @Test(expected = DataAccessException.class)
    public void testRegisterDataAccessException() throws UserDoesntExist, DataAccessException {
        Province province = Province.builder().name("Buenos Aires").build();
        City city = City.builder().name("Mar del Plata").prefix("223").province(province).build();

        User registeredUser = User.builder().name("Enzo").lastname("Roselli").type("asd")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(registeredUser)).thenThrow(new DataAccessException("") {
        });
        User u = userService.register(registeredUser);
    }

    @Test
    public void testDeleteByIdOk() {
        doNothing().when(userRepository).deleteById(12);
        userService.delete(12);
        verify(userRepository, times(1)).deleteById(12);
    }

    @Test
    public void testUpdateOk() throws UserDoesntExist {
        City citySended = City.builder().id(1).build();
        User userSended = User.builder().name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(citySended).build();

        Province province = Province.builder().id(1).name("Buenos Aires").build();
        City city = City.builder().id(1).name("Mar del Plata").prefix("223").province(province).build();
        User registeredUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.save(userSended)).thenReturn(registeredUser);

        User u = userService.update(userSended);

        assertEquals(u, registeredUser);
    }

    @Test
    public void testFindByIdOk() throws UserDoesntExist {

        Province province = Province.builder().id(1).name("Buenos Aires").build();
        City city = City.builder().id(1).name("Mar del Plata").prefix("223").province(province).build();
        User registeredUser = User.builder().id(1).name("Enzo").lastname("Roselli").type("client")
                .identification("1").password("1234").city(city).build();

        when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(registeredUser));

        User u = userService.findById(1);

        assertEquals(u, registeredUser);
    }

    @Test(expected = UserDoesntExist.class)
    public void testFindByIdUserDoesntExistException() throws UserDoesntExist {
        Optional<User> x = Optional.empty();
        when(userRepository.findById(1)).thenReturn(x);
        User u = userService.findById(1);
    }

}
