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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
                                                           @DateTimeFormat(pattern = "dd-MM-yyyy") @PathParam("startDate") @NotNull Date startDate,
                                                           @DateTimeFormat(pattern = "dd-MM-yyyy") @PathParam("endDate") @NotNull Date endDate){
       /* List<Call> calls;
        Integer userId = sessionManager.getCurrentUser(sessionToken).get().getId();
        if (startDate == null && endDate == null) {
            calls = this.callController.getCallsByUserId(userId);
        } else if (startDate == null) {
            calls = this.callController.getByUserEndDate(userId, endDate);
        } else if (endDate == null) {
            calls = this.callController.getByUserStartDate(userId, startDate);
        } else {
            SearchBetweenDatesDTO datesDto = SearchBetweenDatesDTO.builder().start(startDate).end(endDate).build(); //todo pasarlo a un FromDTO
            calls = this.callController.getByUserBetweenDates(userId, datesDto);
        }*/

        List<Call> calls = callController.findAll((Specification<Call>) (root, cq, cb) -> {
            Predicate p = cb.conjunction();
            if (Objects.nonNull(startDate) && Objects.nonNull(endDate) && startDate.before(endDate)) {
                p = cb.and(p, cb.between(root.get("date"), startDate, endDate));
            }else if(Objects.nonNull(startDate)){
                p = cb.greaterThan(root.get("date"), startDate);
            }else if(Objects.nonNull(endDate)){
                p = cb.lessThan(root.get("date"), endDate);
            }
            cq.orderBy(cb.desc(root.get("id")));
            return p;
        });
        return calls.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(calls);
    }
}

