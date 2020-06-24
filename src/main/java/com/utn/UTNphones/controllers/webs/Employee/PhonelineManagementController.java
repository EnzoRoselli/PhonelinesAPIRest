package com.utn.UTNphones.controllers.webs.Employee;

import com.utn.UTNphones.controllers.PhonelineController;
import com.utn.UTNphones.domains.dto.requests.PhonelineDTO;
import com.utn.UTNphones.domains.Phoneline;
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

import static com.utn.UTNphones.utils.PhonelineRouter.PHONELINE_ID;
import static com.utn.UTNphones.utils.PhonelineRouter.PHONELINE_ID_PARAM;
import static com.utn.UTNphones.utils.UserRouter.EMPLOYEE_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE_MAPPING + "/phonelines")
public class PhonelineManagementController {
    private final PhonelineController phonelineController;

    @PostMapping
    public ResponseEntity register(@RequestHeader("Authorization") String sessionToken, @RequestBody @Valid PhonelineDTO newPhoneline) throws Exception {

        return ResponseEntity.created(getLocation(phonelineController.add(newPhoneline))).build();
    }

    @GetMapping(PHONELINE_ID)
    public ResponseEntity<Phoneline> getPhoneline(
                                                  @PathVariable(PHONELINE_ID_PARAM) Integer phonelineId) {

        return ResponseEntity.ok(phonelineController.getById(phonelineId));
    }

    @DeleteMapping(PHONELINE_ID)
    public ResponseEntity<Phoneline> delete(
                                            @PathVariable(PHONELINE_ID_PARAM) Integer phoneId) {


        return ResponseEntity.ok(this.phonelineController.remove(phoneId));
    }

    @PutMapping(PHONELINE_ID)
    public ResponseEntity<Phoneline> update(@RequestHeader("Authorization") String sessionToken,
                                            @PathVariable(PHONELINE_ID_PARAM) Integer phoneId,
                                            @Valid @RequestBody PhonelineDTO phonelineUpdating) throws Exception {

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
