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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.utn.UTNphones.Utils.UserRouter.EMPLOYEE_MAPPING;
import static com.utn.UTNphones.Utils.UserRouter.USER_ID;
import static com.utn.UTNphones.Utils.UserRouter.USER_ID_PARAM;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE_MAPPING + "/invoices")
public class InvoiceManagementController {
    private final InvoiceController invoiceController;

    @GetMapping(USER_ID)
    public ResponseEntity<List<Invoice>> getByUserIdBetweenDates(@RequestHeader("Authorization") String sessionToken,
                                                                 @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startDate,
                                                                 @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> endDate,
                                                                 @PathVariable(USER_ID_PARAM) Integer id) {


       List<Invoice> invoices;
        if (!startDate.isPresent() && !endDate.isPresent()) {
            invoices = invoiceController.findByUserId(id);
        } else {
            invoices = this.invoiceController.findByUserBetweenDates(id,
                    SearchBetweenDatesDTO.fromDates(startDate.orElse(LocalDate.of(2020, 1, 1)),
                            endDate.orElse(LocalDate.now())));
        }
        return invoices.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok(invoices);
    }


}
