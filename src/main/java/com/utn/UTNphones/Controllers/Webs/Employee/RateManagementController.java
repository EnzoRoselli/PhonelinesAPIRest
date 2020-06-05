package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.RateController;
import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Sessions.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("employee/rateManagement")
public class RateManagementController {

    private final RateController rateController;
    private final SessionManager sessionManager;


    @GetMapping
    public ResponseEntity<Rate> getByOriginAndDestination(@RequestHeader("Authorization") String sessionToken,
                                                           @PathParam("originCityId")  Integer originCityId,
                                                           @PathParam("destinationCityId")  Integer destinationCityId) throws RateDoesntExist {
      if (originCityId!=null && destinationCityId!=null){
          Rate rateInfo=this.rateController.getByOriginAndDestination(originCityId,destinationCityId);
          return ResponseEntity.ok(rateInfo);
      }else{
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
    }




}
