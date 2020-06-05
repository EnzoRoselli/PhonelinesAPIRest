package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.RateController;
import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employee/rateManagement")
public class RateManagementController {

    private final RateController rateController;
    private final SessionManager sessionManager;

    @Autowired
    public RateManagementController(RateController rateController, SessionManager sessionManager) {
        this.rateController = rateController;
        this.sessionManager = sessionManager;
    }

    @GetMapping
    public ResponseEntity<List<Rate>> getRates(@RequestHeader("Authorization") String sessionToken,
                                               @PathParam("originCityId") Integer originCityId,
                                               @PathParam("destinationCityId") Integer destinationCityId) throws RateDoesntExist {
        List<Rate> rates = new ArrayList<>();
        if (originCityId != null && destinationCityId != null) {
            Rate rate = this.rateController.getByOriginAndDestination(originCityId, destinationCityId);
            rates.add(rate);
            return ResponseEntity.ok(rates);
        } else if (originCityId != null) {
            return ResponseEntity.ok(this.rateController.getByOrigin(originCityId));
        } else if (destinationCityId != null) {
            return ResponseEntity.ok(this.rateController.getByDestination(destinationCityId));
        }
        return ResponseEntity.ok(this.rateController.getAllRates());
    }

}
