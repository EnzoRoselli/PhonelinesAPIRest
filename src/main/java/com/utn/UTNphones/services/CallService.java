package com.utn.UTNphones.services;

import com.utn.UTNphones.domains.Call;
import com.utn.UTNphones.domains.dto.requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.domains.dto.responses.CityTop;
import com.utn.UTNphones.domains.Phoneline;
import com.utn.UTNphones.repositories.ICallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
        return callRepository.findByOriginPhonelineIn(phoneListOfTheUser, pageable);
    }

    public List<CityTop> getTopMostCalledCities(Integer userId) {
        return this.callRepository.findTopMostCalledCities(userId);
    }

    public List<Call> getByUserAndBetweenDates(Integer userId, SearchBetweenDatesDTO dates) {

        return this.callRepository.findAllByOriginPhonelineUserIdAndDateBetweenOrderByIdDesc(userId, Date.valueOf(dates.getStart()), Date.valueOf(dates.getEnd()), pageable);
    }


}
