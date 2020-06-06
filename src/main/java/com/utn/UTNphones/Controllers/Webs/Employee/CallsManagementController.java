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

import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping("employee/calls")
public class CallsManagementController {
    private final CallController callController;

    @GetMapping(USER_ID)
    public ResponseEntity<List<Call>> getByUserId(@RequestHeader("Authorization") String sessionToken,
                                                  @PathVariable("userId") Integer userId) throws NoCallsFound, UserDoesntExist, PhonelinesNotRegisteredByUser {
        List<Call> callsByAnUser = this.callController.getCallsByUserId(userId);
        return callsByAnUser.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(callsByAnUser);
    }


}
