package com.utn.UTNphones.Controller;

import com.utn.UTNphones.Service.interfaces.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")
public class CallController {

    private final ICallService callService;

    @Autowired
    public CallController(ICallService callService) {
        this.callService = callService;
    }
}
