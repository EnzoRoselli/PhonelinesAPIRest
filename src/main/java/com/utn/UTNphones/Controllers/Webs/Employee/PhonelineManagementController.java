package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Controllers.PhonelineController;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Domains.User;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
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
@RequestMapping("/phonelineManagement")
class PhonelineManagementController {
    private final SessionManager sessionManager;
    private final PhonelineController phonelineController;

    @Autowired
    public PhonelineManagementController(SessionManager sessionManager, PhonelineController phonelineController) {
        this.sessionManager = sessionManager;
        this.phonelineController = phonelineController;
    }

    @PostMapping
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody Phoneline newPhoneline) throws Exception {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        Phoneline phoneline = phonelineController.add(newPhoneline);
        return ResponseEntity.created(getLocation(phoneline)).build();
    }

    @GetMapping("/phonelines/{phonelineId}")
    public ResponseEntity<Phoneline> getPhoneline(@RequestHeader("Authorization") String sessionToken, @PathVariable("phonelineId") Integer phonelineId) throws PhonelineDoesntExist {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        Phoneline phoneline = phonelineController.getById(phonelineId);
        return ResponseEntity.ok(phoneline);
    }


    @DeleteMapping("/phonelines/{phoneNumber}")
    public ResponseEntity delete(@RequestHeader("Authorization") String sessionToken, @PathVariable("phoneNumber") String phoneNumber) throws PhonelineDoesntExist {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        this.phonelineController.remove(phoneNumber);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/phonelines/{phoneId}")
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken, @PathVariable("phoneId") Integer phoneId, @RequestBody Phoneline phonelineUpdating) throws Exception {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        this.phonelineController.update(phoneId, phonelineUpdating);
        return ResponseEntity.ok().build();
    }

    private URI getLocation(Phoneline phoneline) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(phoneline.getId())
                .toUri();
    }

}
