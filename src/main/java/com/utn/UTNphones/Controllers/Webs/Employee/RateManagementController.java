package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.RateController;
import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.EMPLOYEE_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE_MAPPING+"/rates")
public class RateManagementController {

    private final RateController rateController;

    @GetMapping
    public ResponseEntity<List<Rate>> getRates(@RequestHeader("Authorization") String sessionToken,
                                               @PathParam("originCityId") Integer originCityId,
                                               @PathParam("destinationCityId") Integer destinationCityId) throws RateDoesntExist {
        List<Rate> rates = new ArrayList<>();
        if (originCityId != null && destinationCityId != null) {
            rates.add(this.rateController.getByOriginAndDestination(originCityId, destinationCityId));
        } else if (originCityId != null) rates = this.rateController.getByOrigin(originCityId);
        else if (destinationCityId != null) rates = this.rateController.getByDestination(originCityId);
        return rates.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(rates);
    }

}
