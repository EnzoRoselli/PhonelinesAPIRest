package com.utn.UTNphones.Controllers.Webs.Client;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Dto.Responses.CityTop;
import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Exceptions.CallExceptions.NoCallsFound;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Sessions.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.utn.UTNphones.Controllers.Webs.URLconstants.CallRouter.TOP_DESTINATION;
import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.CLIENT_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(CLIENT_MAPPING+"/calls")
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
                                                           @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam("startDate") Optional<LocalDateTime> startDate,
                                                           @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam("endDate") Optional<LocalDateTime> endDate) {
        List<Call> calls;
        Integer userId = sessionManager.getCurrentUser(sessionToken).get().getId();
        if (startDate.isEmpty() && endDate.isEmpty()) {
            calls = this.callController.getCallsByUserId(userId);
        } else if (startDate.isEmpty()) {
            calls = this.callController.getByUserEndDate(userId, endDate);
        } else if (endDate.isEmpty()) {
            calls = this.callController.getByUserStartDate(userId, startDate.orElse());
        } else {
            calls = this.callController.getByUserBetweenDates(userId, SearchBetweenDatesDTO.fromDates(startDate,endDate));
        }
        return calls.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(calls);
    }
}

