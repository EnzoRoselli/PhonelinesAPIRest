package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;

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
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody User userRegistering) throws Exception, UserExceptions {
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
         User user=userController.register(userRegistering);
        return ResponseEntity.created(getLocation(user)).build();
    }

    @GetMapping("{/userId}")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String sessionToken, @PathVariable("userId") @NonNull Integer userId) throws UserDoesntExist {
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        User user=this.userController.getById(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/User/{identification}")
    public ResponseEntity delete(@RequestHeader("Authorization") String sessionToken, @PathVariable("identification")@NotNull String identification) throws ParametersException, UserExceptions {
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)){
            return ResponseEntity.status(401).build();
        }
        this.userController.delete(identification);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken, @RequestBody User userUpdating) throws Exception {
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)) {
            return ResponseEntity.status(401).build();
        }
        this.userController.update(userUpdating);
        return ResponseEntity.ok().build();
    }
    private URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
    }

}
