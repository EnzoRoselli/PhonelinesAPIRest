package com.utn.UTNphones.Controllers;

import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.Dto.CityTop;
import com.utn.UTNphones.Domains.Dto.NewCallDTO;
import com.utn.UTNphones.Domains.Dto.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Services.CallService;
import com.utn.UTNphones.Services.PhonelineService;
import com.utn.UTNphones.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CallController {

    private final CallService callService;
    private final UserService userService;
    private final PhonelineService phonelineService;

    public List<Call> getCallsByUserId(Integer userId) {
        userService.findById(userId);
        List<Phoneline> phoneLines = phonelineService.findByUserId(userId);
        return callService.getCallsByPhoneNumbers(phoneLines);
    }

    public List<CityTop> getTopDestinationsCalled(Integer userId) {
        return this.callService.getTopMostCalledCities(userId);
    }

    public List<Call> getByUserBetweenDates(Integer userId, SearchBetweenDatesDTO dates) {
        return this.callService.getByUserAndBetweenDates(userId, dates);
    }

    public void registerCall(NewCallDTO newCall) {
        Call call = Call.builder().originPhone(newCall.getOriginNumber()).destinationPhone(newCall.getDestinationNumber()).duration(newCall.getDuration()).date(newCall.getDate()).build();
        this.callService.add(call);
    }

    public List<Call> getByUserEndDate(Integer id, Date endDate) {
        return this.callService.getByUserEndDate(id, endDate);
    }

    public List<Call> getByUserStartDate(Integer id, Date startDate) {
        return this.callService.getByUserStartDate(id, startDate);
    }
}
