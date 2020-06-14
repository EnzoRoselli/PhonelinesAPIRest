package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Exceptions.CallExceptions.NoCallsFound;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.EMPLOYEE_MAPPING;
import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_ID;
import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_ID_PARAM;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE_MAPPING + "/calls")
public class CallsManagementController {
    private final CallController callController;

    @GetMapping(USER_ID)
    public ResponseEntity<List<Call>> getByUserId(@RequestHeader("Authorization") String sessionToken,
                                                  @PathVariable(USER_ID_PARAM) Integer userId) throws NoCallsFound, UserDoesntExist, PhonelinesNotRegisteredByUser {
        List<Call> callsByAnUser = this.callController.getCallsByUserId(userId);
        return callsByAnUser.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(callsByAnUser);
    }


}
