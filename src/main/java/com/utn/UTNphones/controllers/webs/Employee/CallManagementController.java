package com.utn.UTNphones.controllers.webs.Employee;

import com.utn.UTNphones.controllers.CallController;
import com.utn.UTNphones.domains.Call;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.utn.UTNphones.utils.UserRouter.EMPLOYEE_MAPPING;
import static com.utn.UTNphones.utils.UserRouter.USER_ID;
import static com.utn.UTNphones.utils.UserRouter.USER_ID_PARAM;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE_MAPPING + "/calls")
public class CallManagementController {

    private final CallController callController;

    @GetMapping(USER_ID)
    public ResponseEntity<List<Call>> getByUserId(@RequestHeader("Authorization") String sessionToken,
                                                  @PathVariable(USER_ID_PARAM) Integer userId) {
        List<Call> callsByUser = this.callController.getCallsByUserId(userId);
        return callsByUser.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(callsByUser);
    }


}
