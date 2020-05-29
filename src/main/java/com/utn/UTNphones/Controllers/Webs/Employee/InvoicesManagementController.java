package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.InvoiceController;
import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Domains.Dto.SearchBetweenDates;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/InvoicesManagement")
public class InvoicesManagementController {
    private final InvoiceController invoiceController;
    private final SessionManager sessionManager;

    public InvoicesManagementController(InvoiceController invoiceController, SessionManager sessionManager) {
        this.invoiceController = invoiceController;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Invoice>> getByUserId(@RequestHeader("Authorization") String sessionToken,
                                                     @PathVariable("id")@NotNull Integer userId){
        if(!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        List<Invoice>invoices=this.invoiceController.getAllByUserId(userId);
        return invoices.isEmpty() ?  ResponseEntity.status(204).build(): ResponseEntity.ok(invoices);
    }

    @GetMapping("users/{userId}/start/{startDate}/end/{endDate}")//todo cambiar fechar
    public ResponseEntity<List<Invoice>>getByUserIdBetweenDates(@RequestHeader("Authorization") String sessionToken,
                                                                @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("startDate") @NotNull Date startDate,
                                                                @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("endDate")@NotNull Date endDate,
                                                                @PathVariable("userId") Integer id){
        if (!PermissionsControllers.isLogged(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        SearchBetweenDates datesDto= SearchBetweenDates.builder().start(startDate).end(endDate).build();
        List<Invoice> invoices = this.invoiceController.getByUserBetweenDates(id, datesDto);
        return invoices.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(invoices);
    }


}
