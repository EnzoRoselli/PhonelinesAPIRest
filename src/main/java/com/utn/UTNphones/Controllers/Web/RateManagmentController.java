package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Controllers.RateController;
import com.utn.UTNphones.Models.Rate;
import com.utn.UTNphones.Models.User;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (!hasEmployeePermissions(sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        List<Rate> Allrates=this.rateController.getAllRates();
        return (Allrates.size()>0)? ResponseEntity.ok(Allrates): ResponseEntity.status(204).build();
    }


  public Boolean hasEmployeePermissions(String sessionToken) {
      Optional<User> currentUser = sessionManager.getCurrentUser(sessionToken);
      return (!currentUser.isEmpty() && !currentUser.get().getType().equals("client"));
  }


}
