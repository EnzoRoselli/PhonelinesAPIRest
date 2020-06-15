package com.utn.UTNphones.Controllers.Webs.Client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Dto.Responses.CityTop;
import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Sessions.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.utn.UTNphones.Controllers.Webs.URLconstants.CallRouter.TOP_DESTINATION;
import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.CLIENT_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(CLIENT_MAPPING + "/calls")
public class CallsClientController {
    private final CallController callController;
    private final SessionManager sessionManager;


    @GetMapping(TOP_DESTINATION)
    public ResponseEntity<List<CityTop>> mostDestinationsCalled(@RequestHeader("Authorization") String sessionToken) throws CallException {
        List<CityTop> citiesWithCounter = this.callController.getTopDestinationsCalled(sessionManager.getCurrentUser(sessionToken).get().getId());
        return citiesWithCounter.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(citiesWithCounter);
    }

    @GetMapping
    public ResponseEntity<List<Call>> getCallsBetweenDates(@RequestHeader("Authorization") String sessionToken,
                                                           @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                           @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Integer userId = sessionManager.getCurrentUser(sessionToken).get().getId();
        List<Call> calls = this.callController.getByUserBetweenDates(userId,
                SearchBetweenDatesDTO.fromDates(Optional.ofNullable(startDate).orElse(LocalDate.of(2020, 1, 1)),
                        Optional.ofNullable(endDate).orElse(LocalDate.now())));

        return calls.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(calls);
    }
}

