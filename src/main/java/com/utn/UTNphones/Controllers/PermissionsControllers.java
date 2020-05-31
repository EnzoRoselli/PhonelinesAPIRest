package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class PermissionsControllers {

    public static ResponseEntity hasEmployeePermissions(SessionManager sessionManager, String sessionToken) {
        Optional<User> currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else if(!currentUser.get().getType().equals("employee")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public static ResponseEntity hasInfrastructurePermissions(SessionManager sessionManager,String sessionToken) {
        Optional<User> currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else if(!currentUser.get().getType().equals("infrastructure")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public static ResponseEntity isLogged(SessionManager sessionManager,String sessionToken){
        Optional<User> currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
