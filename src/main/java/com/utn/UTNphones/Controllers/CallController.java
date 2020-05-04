package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Services.interfaces.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/call/")
public class CallController {

    private final ICallService callService;

    @Autowired
    public CallController(ICallService callService) {
        this.callService = callService;
    }

    //TODO cambiar userId por originPhoneline.user.Id
    @PostMapping("callsByUserId/")
    public List<Call> getCallsByUserId(@RequestBody @NotNull int userId){
        return null;
    }
}
