package com.utn.UTNphones.Controllers.Webs.Infrastructure;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Domains.Dto.Requests.NewCallDTO;
import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.INFRASTRUCTURE_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(INFRASTRUCTURE_MAPPING+"/registerCall")
public class CallRegisterController {
    private final CallController callController;

    @PostMapping
    public ResponseEntity registerCall(@RequestHeader("Authorization") String sessionToken, @RequestBody NewCallDTO newCall) throws CallException {
        this.callController.registerCall(newCall);
        return ResponseEntity.ok().build();
    }
}
