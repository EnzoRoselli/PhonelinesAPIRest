package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.PhonelineController;
import com.utn.UTNphones.Domains.Dto.Requests.PhonelineDTO;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
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
    private final PhonelineController phonelineController;

    @PostMapping
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody @Valid PhonelineDTO newPhoneline) throws Exception {
        return ResponseEntity.created(getLocation(phonelineController.add(newPhoneline))).build();
    }

    @GetMapping(PHONELINE_ID)
    public ResponseEntity<Phoneline> getPhoneline(@RequestHeader("Authorization") String sessionToken,
                                                  @PathVariable("phonelineId") Integer phonelineId) throws PhonelineDoesntExist {
        return ResponseEntity.ok(phonelineController.getById(phonelineId));
    }

    @DeleteMapping(PHONELINE_ID)
    public ResponseEntity delete(@RequestHeader("Authorization") String sessionToken,
                                 @PathVariable("phonelineId") Integer phoneId) throws PhonelineDoesntExist {
        this.phonelineController.remove(phoneId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(PHONELINE_ID)
    public ResponseEntity<Phoneline> update(@RequestHeader("Authorization") String sessionToken,
                                            @PathVariable("phonelineId") Integer phoneId, @RequestBody PhonelineDTO phonelineUpdating) throws Exception {

        return ResponseEntity.ok(this.phonelineController.update(phoneId, phonelineUpdating));
    }

    private URI getLocation(Phoneline phoneline) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(phoneline.getId())
                .toUri();
    }

}
