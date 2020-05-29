package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class PermissionsControllers {
//hola enzo
    public static Boolean hasEmployeePermissions(SessionManager sessionManager,String sessionToken) {
        Optional<User> currentUser = sessionManager.getCurrentUser(sessionToken);
        return (!currentUser.isEmpty() && currentUser.get().getType().equals("employee"));
    }

    public static Boolean hasInfrastructurePermissions(SessionManager sessionManager,String sessionToken) {
        Optional<User> currentUser = sessionManager.getCurrentUser(sessionToken);
        return (!currentUser.isEmpty() && currentUser.get().getType().equals("infrastructure"));
    }

    public static Boolean isLogged(SessionManager sessionManager,String sessionToken){
        Optional<User> currentUser = sessionManager.getCurrentUser(sessionToken);
        return currentUser.isPresent();
    }



}
