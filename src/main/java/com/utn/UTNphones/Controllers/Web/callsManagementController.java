package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Exceptions.CallException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Sessions.SessionManager;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/callsManagementController")
public class callsManagementController {
    private final CallController callController;
    private final SessionManager sessionManager;

    @Autowired
    public callsManagementController(CallController callController, SessionManager sessionManager) {
        this.callController = callController;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/searchByUser")
    public ResponseEntity<List<Call>> getByUserId(@RequestHeader("Authorization") String sessionToken,@RequestBody @NotNull Integer userId) throws CallException, PhonelineExceptions, UserExceptions {
        if (!hasEmployeePermissions(sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        List<Call> callsByAnUser=this.callController.getCallsByUserId(userId);
        return ResponseEntity.ok(callsByAnUser);
    }

    @GetMapping("/mostDestinationsCalled")
    public ResponseEntity<List<City>>

    public Boolean hasEmployeePermissions(String sessionToken) {
        Optional<User> currentUser = sessionManager.getCurrentUser(sessionToken);
        return (!currentUser.isEmpty() && !currentUser.get().getType().equals("client"));
    }
}
