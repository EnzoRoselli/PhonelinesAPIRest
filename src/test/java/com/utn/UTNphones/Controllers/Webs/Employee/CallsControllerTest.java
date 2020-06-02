package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Domains.Dto.CallsWithNameAndLastname;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Repositories.ICallRepository;
import com.utn.UTNphones.Services.CallService;
import com.utn.UTNphones.Services.PhonelineService;
import com.utn.UTNphones.Services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallsControllerTest {
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
        callController = new CallController(callService,userService,phonelineService);
    }
    @Test
   public void getCallsBetweenDates(){
        List<CallsWithNameAndLastname> calls=new ArrayList<>();
        List<CallsWithNameAndLastname> empty=new ArrayList<>();
        LocalDate date = LocalDate.now();
        when(callService.getByDate(date)).thenReturn(empty);
        calls=this.callService.getByDate(date);
        assertEquals(calls, empty);
    }
}
