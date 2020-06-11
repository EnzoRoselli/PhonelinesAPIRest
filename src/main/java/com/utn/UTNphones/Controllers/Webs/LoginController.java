package com.utn.UTNphones.Controllers.Webs;

import com.utn.UTNphones.Controllers.UserController;
import com.utn.UTNphones.Domains.Dto.Requests.LoginDTO;
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

import static com.utn.UTNphones.Controllers.Webs.URLconstants.LogRouter.ADMIN_LOGIN;
import static com.utn.UTNphones.Controllers.Webs.URLconstants.LogRouter.CLIENT_LOGIN;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class LoginController {
    private final UserController userController;
    private final SessionManager sessionManager;

    @PostMapping(ADMIN_LOGIN)
    public ResponseEntity<String> adminLogin(@RequestBody @Valid LoginDTO userLogging) {
        String token = sessionManager.createSession(userController.adminlogin(userLogging));
        return ResponseEntity.ok(token);
    }

    @PostMapping(CLIENT_LOGIN)
    public ResponseEntity<String> clientLogin(@RequestBody @Valid LoginDTO userLogging) {
        String token = sessionManager.createSession(userController.clientLogin(userLogging));
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
