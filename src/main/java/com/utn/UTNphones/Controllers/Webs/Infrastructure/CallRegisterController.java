package com.utn.UTNphones.Controllers.Webs.Infrastructure;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Domains.Dto.NewCallDTO;
import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("infrastructure/registerCall")
public class CallRegisterController {
    private final CallController callController;
    private final SessionManager sessionManager;

    @Autowired
    public CallRegisterController(CallController callController, SessionManager sessionManager) {
        this.callController = callController;
        this.sessionManager = sessionManager;
    }

    @PostMapping
    public ResponseEntity registerCall(@RequestHeader("Authorization") String sessionToken, @RequestBody NewCallDTO newCall) throws CallException {
        ResponseEntity response=PermissionsControllers.hasInfrastructurePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        this.callController.registerCall(newCall);
        return ResponseEntity.ok().build();
    }
}
