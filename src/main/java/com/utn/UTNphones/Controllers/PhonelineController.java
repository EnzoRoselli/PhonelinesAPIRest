package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phoneline")
public class PhonelineController {

    private final IPhonelineService invoiceService;

    @Autowired
    public PhonelineController(IPhonelineService invoiceService) {
        this.invoiceService = invoiceService;
    }
}
