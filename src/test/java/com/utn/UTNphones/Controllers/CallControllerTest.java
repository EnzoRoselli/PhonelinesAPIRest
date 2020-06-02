package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Services.CallService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallControllerTest {

    @Autowired
    @InjectMocks
    CallController callController;
    @Mock
    CallService callService;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void getAllByDestinationPhoneOk(){
        String destinationPhone = "2253333333";
        List<Call> callList = new ArrayList<>();
        Call auxCall = Call.builder().id(1).originPhone("2236960257").destinationPhone(destinationPhone).build();
        callList.add(auxCall);
        auxCall = Call.builder().id(2).originPhone("2237561234").destinationPhone(destinationPhone).build();
        callList.add(auxCall);

        when(callService.getAllByDestinationPhone(destinationPhone)).thenReturn(callList);

        List callsRecived = callController.getAllByDestinationPhone(destinationPhone);

        assertEquals(callList, callsRecived);
    }
}
