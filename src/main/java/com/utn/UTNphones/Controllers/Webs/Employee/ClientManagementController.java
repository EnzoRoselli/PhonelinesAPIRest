package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Domains.Dto.Requests.UserDTO;
import com.utn.UTNphones.Domains.Dto.Requests.UserPatchUpdateDTO;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.EMPLOYEE_MAPPING;
import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_ID;
import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_ID_PARAM;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE_MAPPING+"/clients")
public class ClientManagementController {
    private final UserController userController;

    @PostMapping
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody @Valid UserDTO userRegistering) throws Exception {
        return ResponseEntity.created(getLocation(userController.register(userRegistering))).build();

    }

    @GetMapping(USER_ID)
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String sessionToken, @PathVariable(USER_ID_PARAM) Integer userId) throws UserDoesntExist {
        return ResponseEntity.ok(this.userController.findById(userId));
    }

    @DeleteMapping(USER_ID)
    public ResponseEntity delete(@RequestHeader("Authorization") String sessionToken, @PathVariable(USER_ID_PARAM) Integer userId) throws UserDoesntExist {
        this.userController.delete(userId);
        return ResponseEntity.ok().build();
    }


    @PatchMapping(USER_ID)
    public ResponseEntity<User> modification(@RequestHeader("Authorization") String sessionToken,
                                             @PathVariable(USER_ID_PARAM) Integer userId,
                                             @RequestBody @Valid UserPatchUpdateDTO userUpdating) throws Exception {
        return ResponseEntity.ok(this.userController.modification(userId, userUpdating));
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
