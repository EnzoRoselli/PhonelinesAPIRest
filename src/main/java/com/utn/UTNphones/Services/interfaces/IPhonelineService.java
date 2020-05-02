package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Models.Phoneline;

public interface IPhonelineService {
    Phoneline add(Phoneline phoneline);

    Boolean disable(Integer phoneNumber);

    Boolean enable(Integer phoneNumber);

    void remove(Integer phonelineId);
}
