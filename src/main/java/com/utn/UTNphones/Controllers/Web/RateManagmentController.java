package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Controllers.RateController;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Models.Rate;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rateManagement")
public class RateManagmentController {

    private final RateController rateController;
    private final SessionManager sessionManager;

    @Autowired
    public RateManagmentController(RateController rateController, SessionManager sessionManager) {
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

    @GetMapping("/searchOriginDestination")
    public ResponseEntity<Rate> getByOriginAndDestination(@RequestHeader("Authorization") String sessionToken, @RequestBody @NotNull Rate rate) throws ParametersException {
        if (!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        Optional<Rate> rateInfo=this.rateController.getByOriginAndDestination(rate);
       return (rateInfo.isEmpty()) ?ResponseEntity.status(204).build(): ResponseEntity.ok(rateInfo.get());
    }




}
