package com.utn.UTNphones.Controllers.Webs;

import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Domains.Dto.Requests.LoginDTO;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Sessions.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class LoginController {
    private final UserController userController;
    private final SessionManager sessionManager;

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
