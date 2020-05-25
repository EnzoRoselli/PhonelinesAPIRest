package com.utn.UTNphones.Controllers.Web.Infrastructure;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Domain.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Domain.Dto.NewCallDto;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/registerCall")
public class CallRegisterController {
    private final CallController callController;
    private final SessionManager sessionManager;

    @Autowired
    public CallRegisterController(CallController callController, SessionManager sessionManager) {
        this.callController = callController;
        this.sessionManager = sessionManager;
    }

    @PostMapping
    public ResponseEntity registerCall(@RequestHeader("Authorization") String sessionToken, @RequestBody @NotNull NewCallDto newCall) throws CallException {
        if (!PermissionsControllers.hasInfrastructurePermissions(sessionManager,sessionToken)){
            return ResponseEntity.status(403).build();
        }
        this.callController.registerCall(newCall);
        return ResponseEntity.ok().build();
    }
}
