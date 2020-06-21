package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Dto.Requests.NewCallDTO;
import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Dto.Responses.CityTop;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Services.CallService;
import com.utn.UTNphones.Services.PhonelineService;
import com.utn.UTNphones.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallControllerTest {

    CallController callController;
    @Mock
    CallService callService;
    @Mock
    UserService userService;
    @Mock
    PhonelineService phonelineService;

    @Before
    public void setUp() {
        initMocks(this);
        callController = new CallController(callService, userService, phonelineService);
    }

    @Test
    public void getCallsByUserIdOk(){
        User user= User.builder().id(2).build();
        when(userService.findById(2)).thenReturn(user);
        Phoneline phoneline=Phoneline.builder().id(1).user(user).build();
        List<Phoneline> phonelines=new ArrayList<>();
        phonelines.add(phoneline);
        Call call1 = Call.builder().id(1).build();
        Call call2 = Call.builder().id(2).build();
        List<Call> calls=new ArrayList<>();
        calls.add(call1);
        calls.add(call2);
        when(phonelineService.findByUserId(user.getId())).thenReturn(phonelines);
        when(callService.getCallsByPhoneNumbers(phonelines)).thenReturn(calls);
       assertEquals(callController.getCallsByUserId(2),calls);
    }

    @Test
    public void getTopDestinationsCalled(){
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        CityTop a=factory.createProjection(CityTop.class);
        a.setPrefix("223");
        a.setId(1);
        a.setCant(10);
        a.setCityName("Rio de Janeiro");
        a.setIdProvince(1);
        CityTop b=factory.createProjection(CityTop.class);
        b.setPrefix("123");
        b.setId(2);
        b.setCant(2);
        b.setCityName("Brasilia");
        b.setIdProvince(2);
        List<CityTop> cityTops=new ArrayList<>();
        cityTops.add(a);
        cityTops.add(b);
        when(callService.getTopMostCalledCities(1)).thenReturn(cityTops);
        assertEquals(callController.getTopDestinationsCalled(1),cityTops);
    }

    @Test
    public void getByUserBetweenDatesOk(){
        SearchBetweenDatesDTO dates= SearchBetweenDatesDTO.builder().end(LocalDate.now()).
                start(LocalDate.of(2020,1,1)).build();

        Call call=Call.builder().id(1).build();
        List<Call> calls=new ArrayList<>();
        calls.add(call);
        when(callService.getByUserAndBetweenDates(1,dates)).thenReturn(calls);
        assertEquals(callController.getByUserBetweenDates(1,dates),calls);
    }

    @Test
    public void registerCallOk(){
        NewCallDTO newCallDTO=NewCallDTO.builder().date(Date.valueOf(LocalDate.now())).destinationNumber("121123122").originNumber("3232323232").duration(1).build();
        doNothing().when(callService).add(Call.fromDto(newCallDTO));
        callController.registerCall(newCallDTO);
    }

}
