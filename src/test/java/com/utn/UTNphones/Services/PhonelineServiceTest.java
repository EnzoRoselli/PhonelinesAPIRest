package com.utn.UTNphones.Services;

import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Repositories.IPhonelineRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhonelineServiceTest {
    PhonelineService phonelineService;
    @Mock
    IPhonelineRepository phonelineRepository;

    @Before
    public void setUp() {
        initMocks(this);
        phonelineService = new PhonelineService(phonelineRepository);
    }

    @Test
    public void testAddOk() throws RateDoesntExist {
        Phoneline newPhoneline = Phoneline.builder().id(1).build();
        when(phonelineRepository.save(newPhoneline)).thenReturn(newPhoneline);
        Phoneline phonelineFromDb = phonelineService.add(newPhoneline);
        assertEquals(newPhoneline, phonelineFromDb);
    }

    @Test(expected = DataAccessException.class)
    public void testAddException() throws DataAccessException {
        Phoneline newPhoneline = Phoneline.builder().number("22").build();
        when(phonelineRepository.save(newPhoneline)).thenThrow(new DataAccessException("") {
        });
        phonelineService.add(newPhoneline);
    }

    @Test
    public void testPhonelineStatusModificationOk() {
        String number = "23418823";
        when(phonelineRepository.disableOrEnable(false, number)).thenReturn(1);
        assertEquals(true, phonelineService.disable(number));
        when(phonelineRepository.disableOrEnable(true, number)).thenReturn(1);
        assertEquals(true, phonelineService.enable(number));
    }

    @Test
    public void testFindByUserIdOk() throws PhonelinesNotRegisteredByUser {
        List<Phoneline> phonelines = new ArrayList<>();
        Phoneline phoneline = Phoneline.builder().number("231231").build();
        phonelines.add(phoneline);
        phoneline.setNumber("123123");
        phonelines.add(phoneline);
        when(phonelineRepository.findByUserId(2)).thenReturn(phonelines);
        List<Phoneline> phonelinesFromDb = phonelineService.findByUserId(2);
        assertEquals(phonelinesFromDb, phonelines);
    }

    @Test(expected = PhonelinesNotRegisteredByUser.class)
    public void testFindByUserIdException() throws PhonelinesNotRegisteredByUser {
        List<Phoneline> emptyList = new ArrayList<>();
        when(phonelineRepository.findByUserId(2)).thenReturn(emptyList);
        phonelineService.findByUserId(2);
    }

    @Test
    public void testFindByNumberOk() throws PhonelineDoesntExist {
        String number = "231231";
        Phoneline phoneline = Phoneline.builder().number("231231").build();
        when(phonelineRepository.findByNumber(number)).thenReturn(phoneline);
        Phoneline phonelineFromDb = phonelineService.findByNumber(number);
        assertEquals(phonelineFromDb, phoneline);
    }

    @Test(expected = PhonelineDoesntExist.class)
    public void testFindByNumberException() throws PhonelineDoesntExist {
        when(phonelineRepository.findByNumber("233")).thenReturn(null);
        phonelineService.findByNumber("223");
    }

    @Test
    public void testGetByIdOk() throws PhonelineDoesntExist {
        Integer id = 2;
        Phoneline phoneline = Phoneline.builder().id(2).build();
        when(phonelineRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(phoneline));
        Phoneline phonelineFromDb = phonelineService.getById(id);
        assertEquals(phonelineFromDb, phoneline);
    }

    @Test(expected = PhonelineDoesntExist.class)
    public void testGetByIdException() throws PhonelineDoesntExist {
        Integer id = 2;
        Optional<Phoneline> x = Optional.empty();
        when(phonelineRepository.findById(id)).thenReturn(x);
        phonelineService.getById(id);
    }

    @Test
    public void testRemoveByNumber() throws PhonelineDoesntExist {
        String number = "223113222";
        when(phonelineRepository.removeByNumber(number)).thenReturn(2L);
        phonelineService.removeByNumber(number);
    }
    @Test(expected = PhonelineDoesntExist.class)
    public void testRemoveByNumberException() throws PhonelineDoesntExist {
        String number = "223113222";
       when(phonelineRepository.removeByNumber(number)).thenThrow(new EmptyResultDataAccessException(1){});
        phonelineService.removeByNumber(number);
    }

    @Test
    public void testExistsOk(){
        String number = "223113222";
        Integer cityId=1;
        City city=City.builder().id(1).build();
        Phoneline phoneline = Phoneline.builder().id(2).number("223113222").city(city).build();

        when(phonelineRepository.findByNumberAndCityId(number,cityId)).thenReturn(phoneline);
        Boolean corroboration=phonelineService.exists(number,cityId);
        assertEquals(corroboration,true);
        when(phonelineRepository.findByNumberAndCityId("312",cityId)).thenReturn(null);
       corroboration=phonelineService.exists("312",cityId);
        assertEquals(corroboration,false);
    }

}
