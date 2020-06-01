package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Controllers.PhonelineController;
import com.utn.UTNphones.Domains.Dto.PhonelineRegisterDTO;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.utn.UTNphones.Controllers.Webs.URLconstants.PhonelineRouter.PHONELINE_ID;

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
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody @Valid PhonelineRegisterDTO newPhoneline) throws Exception {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        Phoneline phoneline = phonelineController.add(newPhoneline);
        return ResponseEntity.created(getLocation(phoneline)).build();
    }

    @GetMapping(PHONELINE_ID)
    public ResponseEntity<Phoneline> getPhoneline(@RequestHeader("Authorization") String sessionToken,
                                                  @PathVariable("phonelineId") Integer phonelineId) throws PhonelineDoesntExist {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        Phoneline phoneline = phonelineController.getById(phonelineId);
        return ResponseEntity.ok(phoneline);
    }

    @DeleteMapping(PHONELINE_ID)
    public ResponseEntity delete(@RequestHeader("Authorization") String sessionToken,
                                 @PathVariable("phonelineId") Integer phoneId) throws PhonelineDoesntExist {
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        this.phonelineController.remove(phoneId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(PHONELINE_ID)
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,
                                 @PathVariable("phonelineId") Integer phoneId, @RequestBody Phoneline phonelineUpdating) throws Exception {
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
