package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Exceptions.CallException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions;
import com.utn.UTNphones.Exceptions.UserExceptions;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.Phoneline;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.interfaces.ICallService;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import com.utn.UTNphones.Services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/call/")
public class CallController {

    private final ICallService callService;
    private final IUserService userService;
    private final IPhonelineService phonelineService;


    @Autowired
    public CallController(ICallService callService, IUserService userService, IPhonelineService phonelineService) {
        this.callService = callService;
        this.userService = userService;
        this.phonelineService = phonelineService;
    }


    @GetMapping("callsByUserId/")
    public List<Call> getCallsByUserId(@RequestBody @NotNull Integer userId) throws UserExceptions, CallException, PhonelineExceptions {
        //Throw exception if doesn´t exist
        userService.findById(userId);
        List<Phoneline> phoneLines = phonelineService.findByUserId(userId);
        List<Call> calls = callService.getCallsByPhoneNumbers(phoneLines);
        return calls;
    }
}
