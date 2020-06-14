package com.utn.UTNphones.Services;

import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Dto.Responses.CityTop;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Exceptions.CallExceptions.NoCallsFound;
import com.utn.UTNphones.Repositories.ICallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CallService {

    private final ICallRepository callRepository;

    private final Pageable pageable = PageRequest.of(0, 10);

    public void add(Call call) {
        callRepository.save(call);
    }

    public List<Call> getCallsByPhoneNumbers(List<Phoneline> phoneListOfTheUser) {
        List<Call> calls = callRepository.findByOriginPhonelineIn(phoneListOfTheUser, pageable);
        if (calls.isEmpty()) {
            throw new NoCallsFound();
        }
        return calls;
    }

    public List<CityTop> getTopMostCalledCities(Integer userId) {
        List<CityTop> cityPlusCounter = this.callRepository.findTopMostCalledCities(userId);
        if (cityPlusCounter.isEmpty()) {
            throw new NoCallsFound();
        }
        return cityPlusCounter;
    }

    public List<Call> getByUserAndBetweenDates(Integer userId, SearchBetweenDatesDTO dates) {

        return this.callRepository.findAllByOriginPhonelineUserIdAndDateBetweenOrderByIdDesc(userId, dates.getStart(), dates.getEnd(), pageable);
    }

    public List<Call> getByUserEndDate(Integer userId, Date endDate) {
        return this.callRepository.findAllByOriginPhonelineUserIdAndDateBeforeOrderByIdDesc(userId, endDate, pageable);
    }

    public List<Call> getByUserStartDate(Integer userId, Date startDate) {
        return this.callRepository.findAllByOriginPhonelineUserIdAndDateAfterOrderByIdDesc(userId, startDate, pageable);
    }

}
