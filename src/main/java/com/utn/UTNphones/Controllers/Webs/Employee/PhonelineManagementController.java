package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.PhonelineController;
import com.utn.UTNphones.Domains.Dto.PhonelineRegisterDTO;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Sessions.SessionManager;
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

import static com.utn.UTNphones.Controllers.Webs.URLconstants.PhonelineRouter.PHONELINE_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping("employee/phonelines")
class PhonelineManagementController {
    private final SessionManager sessionManager;
    private final PhonelineController phonelineController;

    @PostMapping
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody @Valid PhonelineRegisterDTO newPhoneline) throws Exception {

        Phoneline phoneline = phonelineController.add(newPhoneline);
        return ResponseEntity.created(getLocation(phoneline)).build();
    }

    @GetMapping(PHONELINE_ID)
    public ResponseEntity<Phoneline> getPhoneline(@RequestHeader("Authorization") String sessionToken,
                                                  @PathVariable("phonelineId") Integer phonelineId) throws PhonelineDoesntExist {
        Phoneline phoneline = phonelineController.getById(phonelineId);
        return ResponseEntity.ok(phoneline);
    }

    @DeleteMapping(PHONELINE_ID)
    public ResponseEntity delete(@RequestHeader("Authorization") String sessionToken,
                                 @PathVariable("phonelineId") Integer phoneId) throws PhonelineDoesntExist {
        this.phonelineController.remove(phoneId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(PHONELINE_ID)
    public ResponseEntity update(@RequestHeader("Authorization") String sessionToken,
                                 @PathVariable("phonelineId") Integer phoneId, @RequestBody Phoneline phonelineUpdating) throws Exception {
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
