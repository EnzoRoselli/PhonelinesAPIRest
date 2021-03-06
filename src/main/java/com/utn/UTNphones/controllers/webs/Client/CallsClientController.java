package com.utn.UTNphones.controllers.webs.Client;

import com.utn.UTNphones.controllers.CallController;
import com.utn.UTNphones.domains.Call;
import com.utn.UTNphones.domains.dto.requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.domains.dto.responses.CityTop;
import com.utn.UTNphones.sessions.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.utn.UTNphones.utils.CallRouter.TOP_DESTINATION;
import static com.utn.UTNphones.utils.UserRouter.CLIENT_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(CLIENT_MAPPING + "/calls")
public class CallsClientController {
    private final CallController callController;
    private final SessionManager sessionManager;


    @GetMapping(TOP_DESTINATION)
    public ResponseEntity<List<CityTop>> mostDestinationsCalled(@RequestHeader("Authorization") String sessionToken) {
        List<CityTop> citiesWithCounter = this.callController.getTopDestinationsCalled(sessionManager.getCurrentUser(sessionToken).get().getId());
        return citiesWithCounter.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(citiesWithCounter);
    }

    @GetMapping
    public ResponseEntity<List<Call>> getCallsBetweenDates(@RequestHeader("Authorization") String sessionToken,
                                                           @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                           @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Integer userId = sessionManager.getCurrentUser(sessionToken).get().getId();
        List<Call> calls = this.callController.getByUserBetweenDates(userId,
                SearchBetweenDatesDTO.fromDates(Optional.ofNullable(startDate)
                                .orElse(LocalDate.of(2020, 1, 1)),
                        Optional.ofNullable(endDate)
                                .orElse(LocalDate.now())));

        return calls.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(calls);
    }
}

