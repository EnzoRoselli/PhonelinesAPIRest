package com.utn.UTNphones.Controllers.Webs.Employee;

import com.utn.UTNphones.Controllers.InvoiceController;
import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.EMPLOYEE_MAPPING;
import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_ID;
import static com.utn.UTNphones.Controllers.Webs.URLconstants.UserRouter.USER_ID_PARAM;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE_MAPPING+"/invoices")
public class InvoicesManagementController {
    private final InvoiceController invoiceController;

    @GetMapping(USER_ID)
    public ResponseEntity<List<Invoice>> getByUserIdBetweenDates(@RequestHeader("Authorization") String sessionToken,
                                                                 @DateTimeFormat(pattern = "dd-MM-yyyy") @PathParam("startDate") Date startDate,
                                                                 @DateTimeFormat(pattern = "dd-MM-yyyy") @PathParam("endDate") Date endDate,
                                                                 @PathVariable(USER_ID_PARAM) Integer id) {
        List<Invoice> invoices;
        if (startDate == null && endDate == null) {
            invoices = this.invoiceController.getAllByUserId(id);
        } else if (startDate == null) {
            invoices = this.invoiceController.getByUserEndDate(id, endDate);
        } else if (endDate == null) {
            invoices = this.invoiceController.getByUserStartDate(id, startDate);
        } else {
            SearchBetweenDatesDTO datesDto = SearchBetweenDatesDTO.builder().start(startDate).end(endDate).build();
            invoices = this.invoiceController.getByUserBetweenDates(id, datesDto);
        }

        return invoices.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(invoices);
    }


}
