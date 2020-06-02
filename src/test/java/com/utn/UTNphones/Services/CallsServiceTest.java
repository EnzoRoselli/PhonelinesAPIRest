package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Dto.CallsWithNameAndLastname;
import com.utn.UTNphones.Repositories.ICallRepository;
import com.utn.UTNphones.Repositories.IInvoiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallsServiceTest {
    CallService callService;
    @Mock
    ICallRepository callRepository;
    @Before
    public void setUp() {
        initMocks(this);
        callService = new CallService(callRepository);
    }

    @Test
    public void testGetByDate(){
        List<CallsWithNameAndLastname> calls=new ArrayList<>();
        List<CallsWithNameAndLastname> empty=new ArrayList<>();
        LocalDate date = LocalDate.now();
        when(callRepository.findAllByDate(date.getYear(),date.getMonthValue(),date.getDayOfYear())).thenReturn(empty);
       calls=this.callRepository.findAllByDate(date.getYear(),date.getMonthValue(),date.getDayOfYear());
        assertEquals(calls,empty);
    }
}
