package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.InvoiceController;
import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Domains.Dto.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;
import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_ID;

@RestController
@RequestMapping("employee/InvoicesManagement")
public class InvoicesManagementController {
    private final InvoiceController invoiceController;
    private final SessionManager sessionManager;

    public InvoicesManagementController(InvoiceController invoiceController, SessionManager sessionManager) {
        this.invoiceController = invoiceController;
        this.sessionManager = sessionManager;
    }

    @GetMapping(USER_ID)
    public ResponseEntity<List<Invoice>> getByUserId(@RequestHeader("Authorization") String sessionToken,
                                                     @PathVariable("id") Integer userId){
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }
        List<Invoice>invoices=this.invoiceController.getAllByUserId(userId);
        return invoices.isEmpty() ?  ResponseEntity.status(204).build(): ResponseEntity.ok(invoices);
    }

    @GetMapping(USER_ID+"/invoices")
    public ResponseEntity<List<Invoice>>getByUserIdBetweenDates(@RequestHeader("Authorization") String sessionToken,
                                                                @DateTimeFormat(pattern = "dd-MM-yyyy") @PathParam("startDate") Date startDate,
                                                                @DateTimeFormat(pattern = "dd-MM-yyyy") @PathParam("endDate") Date endDate,
                                                                @PathVariable("userId") Integer id){
        ResponseEntity response=PermissionsControllers.hasEmployeePermissions(sessionManager, sessionToken);
        if (response.getStatusCode()!= HttpStatus.OK) {
            return response;
        }

        List<Invoice> invoices;
        if(startDate == null && endDate == null){
            invoices = this.invoiceController.getAllByUserId(id);
        }else if(startDate == null){
            invoices = this.invoiceController.getByUserEndDate(id, endDate);
        }else if(endDate == null){
            invoices = this.invoiceController.getByUserStartDate(id, startDate);
        }else{
            SearchBetweenDatesDTO datesDto= SearchBetweenDatesDTO.builder().start(startDate).end(endDate).build();
            invoices = this.invoiceController.getByUserBetweenDates(id, datesDto);
        }

        return invoices.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(invoices);
    }


}
