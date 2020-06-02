package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Sessions.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallsManagmentControllerTest {

    @Autowired
    @InjectMocks
    CallsManagementController callsManagementController;
    @Mock
    CallController callController;
    @Mock
    SessionManager sessionManager;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void getAllByDestinationPhoneOk() {
        User employee = User.builder().type("employee").build();
        String destinationPhone = "2253333333";
        List<Call> callList = new ArrayList<>();
        Call auxCall = Call.builder().id(1).originPhone("2236960257").destinationPhone(destinationPhone).build();
        callList.add(auxCall);
        auxCall = Call.builder().id(2).originPhone("2237561234").destinationPhone(destinationPhone).build();
        callList.add(auxCall);

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(callController.getAllByDestinationPhone(destinationPhone)).thenReturn(callList);

        ResponseEntity<List<Call>> responseEntity = callsManagementController.getAllByDestinationPhone("token", destinationPhone);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(callList, responseEntity.getBody());
    }

    @Test
    public void getAllByDestinationPhoneUnauthorized() {
        User client = User.builder().type("client").build();
        String destinationPhone = "2253333333";

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.of(client));

        ResponseEntity<List<Call>> responseEntity = callsManagementController.getAllByDestinationPhone("token", destinationPhone);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void getAllByDestinationPhoneForbidden() {
        String destinationPhone = "2253333333";

        when(sessionManager.getCurrentUser("token")).thenReturn(Optional.empty());

        ResponseEntity<List<Call>> responseEntity = callsManagementController.getAllByDestinationPhone("token", destinationPhone);
    }

    @Test
    public void getAllByDestinationPhoneNoContent() {
        User employee = User.builder().type("employee").build();
        String destinationPhone = "2253333333";
        List<Call> callList = new ArrayList<>();

        when(sessionManager.getCurrentUser("token")).thenReturn(java.util.Optional.ofNullable(employee));
        when(callController.getAllByDestinationPhone(destinationPhone)).thenReturn(callList);

        ResponseEntity<List<Call>> responseEntity = callsManagementController.getAllByDestinationPhone("token", destinationPhone);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
