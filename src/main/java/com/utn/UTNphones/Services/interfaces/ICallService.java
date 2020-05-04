package com.utn.UTNphones.Services.interfaces;

import com.utn.UTNphones.Models.Call;

import java.util.List;

public interface ICallService {

    List<Call> getCallsByUserId(int userId);
}
