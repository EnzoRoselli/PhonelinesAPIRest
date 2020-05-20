package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Controllers.CallController;
import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineExceptions;
import com.utn.UTNphones.Exceptions.ProvinceExceptions.ProvinceDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.Dto.CityTop;
import com.utn.UTNphones.Models.Dto.CityTopDto;
import com.utn.UTNphones.Models.Dto.NewCallDto;
import com.utn.UTNphones.Models.Dto.SearchBetweenDates;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/callsManagementController")
public class CallsManagementController {
    private final CallController callController;
    private final SessionManager sessionManager;

    @Autowired
    public CallsManagementController(CallController callController, SessionManager sessionManager) {
        this.callController = callController;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/searchByUser/{UserId}")
    public ResponseEntity<List<Call>> getByUserId(@RequestHeader("Authorization") String sessionToken, @PathVariable("UserId") @NotNull Integer userId) throws CallException, PhonelineExceptions, UserExceptions, ParametersException {
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)){
            return ResponseEntity.status(403).build();
        }
        List<Call> callsByAnUser = this.callController.getCallsByUserId(userId);
        return callsByAnUser.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(callsByAnUser);
    }

    @GetMapping("/mostDestinationsCalled")
    public ResponseEntity<List<CityTop>> mostDestinationsCalled(@RequestHeader("Authorization") String sessionToken) throws UserExceptions, CallException, ParametersException, ProvinceDoesntExist {
        if (!PermissionsControllers.isLogged(sessionManager,sessionToken)){
            return ResponseEntity.status(403).build();
        }
        List<CityTop> citiesWithCounter = this.callController.getTopDestinationsCalled(sessionManager.getCurrentUser(sessionToken).get().getId());
        return citiesWithCounter.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(citiesWithCounter);
    }

    @GetMapping("/getCallsBetweenDates/{startDate}/{endDate}")
    public ResponseEntity<List<Call>> getCallsBetweenDates(@RequestHeader("Authorization") String sessionToken,
                                                           @PathVariable("startDate") @NotNull Date startDate,
                                                           @PathVariable("endDate")@NotNull Date endDate) throws UserExceptions, ParametersException {
        if (!PermissionsControllers.isLogged(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        SearchBetweenDates datesDto= SearchBetweenDates.builder().start(startDate).end(endDate).build();
        List<Call> calls = this.callController.getByUserBetweenDates(sessionManager.getCurrentUser(sessionToken).get().getId(), datesDto);
        return calls.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(calls);
    }

    @PostMapping
    public ResponseEntity registerCall(@RequestHeader("Authorization") String sessionToken,@RequestBody @NotNull NewCallDto newCall) throws CallException {
        if (!PermissionsControllers.hasInfrastructurePermissions(sessionManager,sessionToken)){
            return ResponseEntity.status(403).build();
        }
        this.callController.registerCall(newCall);
        return ResponseEntity.ok().build();
    }

}
