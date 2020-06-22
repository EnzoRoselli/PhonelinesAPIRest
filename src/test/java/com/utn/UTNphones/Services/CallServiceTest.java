package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Dto.Responses.CityTop;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Repositories.ICallRepository;
import com.utn.UTNphones.Utils.ObjectCreator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallServiceTest {
    CallService callService;
    @Mock
    ICallRepository callRepository;

    @Before
    public void setUp() {
        initMocks(this);
        callService = new CallService(callRepository);
    }

    @Test
    public void addOk() {
        Call call = ObjectCreator.createCall();
        when(callRepository.save(call)).thenReturn(call);
        callService.add(call);
    }

    @Test
    public void getCallsByPhoneNumberOk() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Phoneline> phonelines = new ArrayList<>();
        phonelines.add(ObjectCreator.createPhoneline());
        phonelines.add(ObjectCreator.createPhoneline());
        List<Call> calls = new ArrayList<>();
        calls.add(ObjectCreator.createCall());
        when(callRepository.findByOriginPhonelineIn(phonelines, pageable)).thenReturn(calls);
        assertEquals(callService.getCallsByPhoneNumbers(phonelines), calls);
    }

    @Test
    public void getTopMostCalledCitiesOk() {
        List<CityTop> calls = callRepository.findTopMostCalledCities(2);
        when(callRepository.findTopMostCalledCities(2)).thenReturn(calls);
        assertEquals(callService.getTopMostCalledCities(2), calls);
    }

    @Test
    public void getByUserAndBetweenDatesOk() {
        Pageable pageable = PageRequest.of(0, 10);
        LocalDate dateStart = LocalDate.of(2020, 1, 8);
        LocalDate dateEnd = LocalDate.of(2019, 1, 8);
        SearchBetweenDatesDTO dates = new SearchBetweenDatesDTO(dateStart, dateEnd);
        Integer userId = 1;
        List<Call> calls = new ArrayList<>();
        calls.add(Call.builder().id(2).build());
        when(callRepository.findAllByOriginPhonelineUserIdAndDateBetweenOrderByIdDesc(userId, Date.valueOf(dates.getStart()), Date.valueOf(dates.getEnd()), pageable)).thenReturn(calls);
        List<Call> callsAux = callService.getByUserAndBetweenDates(userId, dates);
        assertEquals(callsAux, calls);
    }


}
