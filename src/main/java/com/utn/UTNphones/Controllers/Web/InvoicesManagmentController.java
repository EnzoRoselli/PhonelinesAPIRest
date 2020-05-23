package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Controllers.InvoiceController;
import com.utn.UTNphones.Controllers.PermissionsControllers;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Models.Invoice;
import com.utn.UTNphones.Sessions.SessionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/InvoicesManagment")
public class InvoicesManagmentController {
    private final InvoiceController invoiceController;
    private final SessionManager sessionManager;

    public InvoicesManagmentController(InvoiceController invoiceController, SessionManager sessionManager) {
        this.invoiceController = invoiceController;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/User/{id}")
    public ResponseEntity<List<Invoice>> getByUserId(@RequestHeader("Authorization") String sessionToken, @PathVariable("id")@NotNull Integer userId){
        if(!PermissionsControllers.hasEmployeePermissions(sessionManager,sessionToken)) {
            return ResponseEntity.status(403).build();
        }
        List<Invoice>invoices=this.invoiceController.getAllByUserId(userId);
        return invoices.isEmpty() ?  ResponseEntity.status(204).build(): ResponseEntity.ok(invoices);
    }


}
