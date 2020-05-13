package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("/managementClient")
public class ClientManagementController {
    private final UserController userController;
    private final SessionManager sessionManager;

    @Autowired
    public ClientManagementController(UserController userController, SessionManager sessionManager) {
        this.userController = userController;
        this.sessionManager = sessionManager;
    }

    @PostMapping
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody User userRegistering) throws Exception {
        if (hasEmployeePermissions(sessionToken)) {
            return ResponseEntity.status(401).build();
        }
        User newUser = userController.register(userRegistering);
        return newUser != null ? ResponseEntity.status(201).build() : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestHeader("Authorization") String sessionToken, @RequestBody String identification) throws ParametersException, UserExceptions {
        if (!hasEmployeePermissions( sessionToken)) {
            return ResponseEntity.status(401).build();
        }
        this.userController.delete(identification);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken, @RequestBody User userUpdating) throws Exception {
        if (!hasEmployeePermissions(sessionToken)) {
            return ResponseEntity.status(401).build();
        }
        this.userController.update(userUpdating);
        return ResponseEntity.ok().build();
    }


    public Boolean hasEmployeePermissions(String sessionToken) {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.getType() == "client" || currentUser == null) {
            return false;
        } else {
            return true;
        }
    }
}
