package com.utn.UTNphones.Services;

import com.utn.UTNphones.Repositories.IPhonelineRepository;
import com.utn.UTNphones.Services.interfaces.IPhonelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhonelineService implements IPhonelineService {

    private final IPhonelineRepository invoiceRepository;

    @Autowired
    public PhonelineService(IPhonelineRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
}
