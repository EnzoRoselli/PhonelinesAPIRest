package com.utn.UTNphones.Controllers.Webs.Client;

import com.utn.UTNphones.Controllers.InvoiceController;
import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Sessions.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.CLIENT_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(CLIENT_MAPPING + "/invoices")
public class InvoicesClientController {
    private final InvoiceController invoiceController;
    private final SessionManager sessionManager;

    @GetMapping
    public ResponseEntity<List<Invoice>> getInvoicesBetweenDates(@RequestHeader("Authorization") String sessionToken,
                                                                 @DateTimeFormat(pattern = "dd-MM-yyyy") @PathParam("startDate") Date startDate,
                                                                 @DateTimeFormat(pattern = "dd-MM-yyyy") @PathParam("endDate") Date endDate) {
        List<Invoice> invoices;
        Integer userId = sessionManager.getCurrentUser(sessionToken).get().getId();
        if (startDate == null && endDate == null) {
            invoices = this.invoiceController.getAllByUserId(userId);
        } else if (endDate == null) {
            invoices = this.invoiceController.getByUserStartDate(userId, startDate);
        } else if (startDate == null) {
            invoices = this.invoiceController.getByUserEndDate(userId, endDate);
        } else {
            invoices = this.invoiceController.getByUserBetweenDates(userId, SearchBetweenDatesDTO.fromDates(startDate, endDate));
        }
        return invoices.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(invoices);
    }
}