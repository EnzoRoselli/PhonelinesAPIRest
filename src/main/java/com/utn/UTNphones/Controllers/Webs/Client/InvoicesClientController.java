package com.utn.UTNphones.Controllers.Webs.Client;

import com.utn.UTNphones.Controllers.InvoiceController;
import com.utn.UTNphones.Domains.Dto.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Sessions.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("client/invoices")
public class InvoicesClientController {
    private final InvoiceController invoiceController;
    private final SessionManager sessionManager;

    @GetMapping("/start/{startDate}/end/{endDate}")//////todo cambiar
    public ResponseEntity<List<Invoice>> getByUserIdBetweenDates(@RequestHeader("Authorization") String sessionToken,
                                                                 @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("startDate") Date startDate,
                                                                 @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable("endDate") Date endDate) {
        SearchBetweenDatesDTO datesDto = SearchBetweenDatesDTO.builder().start(startDate).end(endDate).build();
        List<Invoice> invoices = this.invoiceController.getByUserBetweenDates(sessionManager.getCurrentUser(sessionToken).get().getId(), datesDto);
        return invoices.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(invoices);
    }
}