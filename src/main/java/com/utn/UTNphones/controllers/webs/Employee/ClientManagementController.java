package com.utn.UTNphones.controllers.webs.Employee;

import com.utn.UTNphones.controllers.UserController;
import com.utn.UTNphones.domains.dto.requests.UserDTO;
import com.utn.UTNphones.domains.User;
import com.utn.UTNphones.exceptions.usersExceptions.UserNotExists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.utn.UTNphones.utils.UserRouter.EMPLOYEE_MAPPING;
import static com.utn.UTNphones.utils.UserRouter.USER_ID;
import static com.utn.UTNphones.utils.UserRouter.USER_ID_PARAM;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE_MAPPING + "/users")
public class ClientManagementController {
    private final UserController userController;

    @PostMapping
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody @Valid UserDTO userRegistering) throws Exception {
        return ResponseEntity.created(getLocation(userController.register(userRegistering))).build();
    }

    @GetMapping(USER_ID)
    public ResponseEntity<User> getUser(@PathVariable(USER_ID_PARAM) Integer userId) throws UserNotExists {
        return ResponseEntity.ok(this.userController.findById(userId));
    }

    @DeleteMapping(USER_ID)
    public ResponseEntity<User> delete(@RequestHeader("Authorization") String sessionToken, @PathVariable(USER_ID_PARAM) Integer userId) {

        return ResponseEntity.ok(this.userController.delete(userId));
    }

    @PutMapping(USER_ID)
    public ResponseEntity<User> update(@RequestHeader("Authorization") String sessionToken,
                                       @PathVariable(USER_ID_PARAM) Integer userId,
                                       @RequestBody @Valid UserDTO userUpdating) throws Exception {
        return ResponseEntity.ok(this.userController.update(userId, userUpdating));
    }

    public URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
    }

}
