package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Domains.Dto.UserRegisterDTO;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_ID;
import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_IDENTIFICATION;

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
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody @Valid UserRegisterDTO userRegistering) throws Exception {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
         User user=userController.register(userRegistering);
        return ResponseEntity.created(getLocation(user)).build();
    }

    @GetMapping(USER_ID)
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String sessionToken, @PathVariable("userId") Integer userId) throws UserDoesntExist {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        User user=this.userController.findById(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(USER_IDENTIFICATION)
    public ResponseEntity delete(@RequestHeader("Authorization") String sessionToken, @PathVariable("identification") String identification) throws UserDoesntExist {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        this.userController.delete(identification);
        return ResponseEntity.ok().build();

    }

    @PatchMapping
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken, @RequestBody User userUpdating) throws Exception {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        this.userController.update(userUpdating);
        return ResponseEntity.ok().build();
    }
    public URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
    }

}
