package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Exceptions.CallExceptions.NoCallsFound;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_ID;

@RestController
@RequestMapping("employee/callsManagement")
public class CallsManagementController {
    private final CallController callController;
    private final SessionManager sessionManager;

    @Autowired
    public CallsManagementController(CallController callController, SessionManager sessionManager) {
        this.callController = callController;
        this.sessionManager = sessionManager;
    }

    @GetMapping(USER_ID)
    public ResponseEntity<List<Call>> getByUserId(@RequestHeader("Authorization") String sessionToken, @PathVariable("userId") Integer userId) throws NoCallsFound, UserDoesntExist, PhonelinesNotRegisteredByUser {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        List<Call> callsByAnUser = this.callController.getCallsByUserId(userId);
        return callsByAnUser.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(callsByAnUser);
    }



}
