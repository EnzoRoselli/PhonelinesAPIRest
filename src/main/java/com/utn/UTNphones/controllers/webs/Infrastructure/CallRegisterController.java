package com.utn.UTNphones.controllers.webs.Infrastructure;

import com.utn.UTNphones.controllers.CallController;
import com.utn.UTNphones.domains.dto.requests.NewCallDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.utn.UTNphones.utils.UserRouter.INFRASTRUCTURE_MAPPING;


@RestController
@RequiredArgsConstructor
@RequestMapping(INFRASTRUCTURE_MAPPING)
public class CallRegisterController {

    private final CallController callController;

    @PostMapping
    public ResponseEntity registerCall(@RequestHeader("INFRASTRUCTURE_KEY") String key,
                                       @RequestBody @Valid NewCallDTO newCall) {
        this.callController.registerCall(newCall);
        return ResponseEntity.ok().build();
    }
}
