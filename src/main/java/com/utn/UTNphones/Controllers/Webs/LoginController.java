package com.utn.UTNphones.Controllers.Webs;

import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Domains.Dto.LoginDTO;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/user")
public class LoginController {
    private final UserController userController;
    private final SessionManager sessionManager;

    @Autowired
    public LoginController(UserController userController, SessionManager sessionManager) {
        this.userController = userController;
        this.sessionManager = sessionManager;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO userLogging) throws LogException {
        User u = userController.login(userLogging);
        String token = sessionManager.createSession(u);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        sessionManager.removeSession(token);
        return ResponseEntity.ok().build();
    }

    public HttpHeaders createHeaders(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return responseHeaders;
    }
}
