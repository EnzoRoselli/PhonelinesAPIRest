package com.utn.UTNphones.Service;

import com.utn.UTNphones.Repository.IPhonelineRepository;
import com.utn.UTNphones.Service.interfaces.IPhonelineService;
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
