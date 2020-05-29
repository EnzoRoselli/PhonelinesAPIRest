package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Controllers.RateController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/rateManagement")
public class RateManagementController {

    private final RateController rateController;
    private final SessionManager sessionManager;

    @Autowired
    public RateManagementController(RateController rateController, SessionManager sessionManager) {
        this.rateController = rateController;
        this.sessionManager = sessionManager;
    }

    @GetMapping
    public ResponseEntity<List<Rate>> getAll(@RequestHeader("Authorization") String sessionToken){
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)){
            return ResponseEntity.status(403).build();
        }
        List<Rate> Allrates=this.rateController.getAllRates();
        return (Allrates.size()>0)? ResponseEntity.ok(Allrates): ResponseEntity.status(204).build();
    }

    @GetMapping("/origin/{originCityId}/destination/{destinationCityId}")//todo cambiar a un put el metodo
    public ResponseEntity<Rate> getByOriginAndDestination(@RequestHeader("Authorization") String sessionToken,
                                                          @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("originCityId") @NotNull Integer originCityId,
                                                          @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("destinationCityId") @NotNull Integer destinationCityId) throws ParametersException, RateDoesntExist {
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
       Rate rateInfo=this.rateController.getByOriginAndDestination(originCityId,destinationCityId);
       return ResponseEntity.ok(rateInfo);
    }




}
