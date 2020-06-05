package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Domains.Dto.UserPatchUpdateDTO;
import com.utn.UTNphones.Domains.Dto.UserRegisterDTO;
import com.utn.UTNphones.Domains.Dto.UserUpdateDTO;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Sessions.SessionManager;
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

import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping("employee/clients")
public class ClientManagementController {
    private final UserController userController;
    private final SessionManager sessionManager;


    @PostMapping
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody @Valid UserRegisterDTO userRegistering) throws Exception {
        User user = userController.register(userRegistering);
        return ResponseEntity.created(getLocation(user)).build();
        
    }

    @GetMapping(USER_ID)
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String sessionToken, @PathVariable("userId") Integer userId) throws UserDoesntExist {
        User user = this.userController.findById(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(USER_ID)
    public ResponseEntity delete(@RequestHeader("Authorization") String sessionToken, @PathVariable("userId") Integer userId) throws UserDoesntExist {
         this.userController.delete(userId);
        return ResponseEntity.ok().build();
    }


    @PatchMapping
    public ResponseEntity<User> modification(@RequestHeader("Authorization") String sessionToken, @RequestBody @Valid UserPatchUpdateDTO userUpdating) throws Exception {
        User user = this.userController.modification(userUpdating);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestHeader("Authorization") String sessionToken, @RequestBody @Valid UserUpdateDTO userUpdating) throws Exception {
        User user = this.userController.update(userUpdating);
        return ResponseEntity.ok(user);
    }

    public URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
    }

}
