package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineNotExists;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Exceptions.RateExceptions.RateNotExists;
import com.utn.UTNphones.Repositories.IPhonelineRepository;
import com.utn.UTNphones.Utils.ObjectCreator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
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
    public void testAddOk() throws RateNotExists {
        Phoneline newPhoneline = ObjectCreator.createPhoneline();
        when(phonelineRepository.save(newPhoneline)).thenReturn(newPhoneline);
        Phoneline phonelineFromDb = phonelineService.add(newPhoneline);
        assertEquals(newPhoneline, phonelineFromDb);
    }

    @Test(expected = DataAccessException.class)
    public void testAddDataAccessException() throws DataAccessException {
        Phoneline newPhoneline = ObjectCreator.createPhoneline();
        when(phonelineRepository.save(newPhoneline)).thenThrow(new DataAccessException("") {
        });
        phonelineService.add(newPhoneline);
    }

    @Test
    public void testFindByUserIdOk(){
        List<Phoneline> phonelines = new ArrayList<>();
        Phoneline phoneline = ObjectCreator.createPhoneline();
        phonelines.add(phoneline);
        phonelines.add(phoneline);
        when(phonelineRepository.findByUserId(2)).thenReturn(phonelines);
        assertEquals(phonelineService.findByUserId(2), phonelines);
    }

    @Test(expected = PhonelinesNotRegisteredByUser.class)
    public void testFindByUserIdException() {
        List<Phoneline> emptyList = new ArrayList<>();
        when(phonelineRepository.findByUserId(2)).thenReturn(emptyList);
        phonelineService.findByUserId(2);
    }

    @Test
    public void testFindByNumberOk() throws PhonelineNotExists {
        Phoneline phoneline = ObjectCreator.createPhoneline();
        String number = phoneline.getNumber();
        when(phonelineRepository.findByNumber(number)).thenReturn(Optional.of(phoneline));
        assertEquals(phonelineService.findByNumber(number), phoneline);
    }

    @Test(expected = PhonelineNotExists.class)
    public void testFindByNumberException() throws PhonelineNotExists {
        when(phonelineRepository.findByNumber("1")).thenReturn(Optional.empty());
        phonelineService.findByNumber("1");
    }

    @Test
    public void testGetByIdOk() throws PhonelineNotExists {
        Phoneline phoneline = ObjectCreator.createPhoneline();
        Integer id = phoneline.getId();
        when(phonelineRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(phoneline));
        assertEquals(phonelineService.getById(id), phoneline);
    }

    @Test(expected = PhonelineNotExists.class)
    public void testGetByIdException() throws PhonelineNotExists {
        Integer id = 2;
        Optional<Phoneline> x = Optional.empty();
        when(phonelineRepository.findById(id)).thenReturn(x);
        phonelineService.getById(id);
    }

    @Test
    public void testRemoveByIdOk() throws PhonelineNotExists {
        Integer number = 12;
        doNothing().when(phonelineRepository).deleteById(number);
        phonelineService.removeById(number);
    }

    @Test
    public void testUpdateOk() throws PhonelineNotExists {
        Phoneline phoneline = Phoneline.builder().id(2).build();
        when(phonelineRepository.save(phoneline)).thenReturn(phoneline);
        assertEquals(phonelineService.update(phoneline), phoneline);
    }

}
