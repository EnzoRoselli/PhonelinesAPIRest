package com.utn.UTNphones.Utils;

import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.City;
import com.utn.UTNphones.Domains.Dto.Requests.ClientLoginDTO;
import com.utn.UTNphones.Domains.Dto.Requests.EmployeeLoginDTO;
import com.utn.UTNphones.Domains.Dto.Requests.NewCallDTO;
import com.utn.UTNphones.Domains.Dto.Requests.PhonelineDTO;
import com.utn.UTNphones.Domains.Dto.Requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.Domains.Dto.Requests.UserDTO;
import com.utn.UTNphones.Domains.Invoice;
import com.utn.UTNphones.Domains.Phoneline;
import com.utn.UTNphones.Domains.Province;
import com.utn.UTNphones.Domains.Rate;
import com.utn.UTNphones.Domains.User;

import java.sql.Date;
import java.time.LocalDate;

public class ObjectCreator {

    public static User createClientUser() {
        return User.builder().id(1).status(true)
                .identification("12345678").password("abc")
                .name("Facundinho").lastname("Silva").type("client").build();
    }
    public static User createEmployeeUser() {
        return User.builder().id(1).status(true)
                .identification("12345678").password("abc")
                .name("Facundinho").lastname("Silva").type("employee").build();
    }

    public static Rate createRate() {
        return Rate.builder().id(1).destinationCity(createCity())
                .originCity(createCity()).costPerMinute(1.0)
                .pricePerMinute(2.0).build();
    }

    public static City createCity() {
        return City.builder().name("Rio de Janeiro")
                .id(1).prefix("112").province(createProvince()).build();
    }

    public static Province createProvince() {
        return Province.builder().name("Sao Paulo").id(1).build();
    }

    public static UserDTO createUserDTO() {
        return UserDTO.builder().name("Facu").lastname("Roselli").type("client")
                .identification("1234578").password("1234").cityId(1).status(true).build();
    }

    public static PhonelineDTO createPhonelineDTO() {
        return PhonelineDTO.builder().type("mobile").cityId(1)
                .userId(2).number("1234567").status(true).build();
    }

    public static Invoice createInvoice() {
        return Invoice.builder().id(1).phoneline(createPhoneline()).date(Date.valueOf(LocalDate.now()))
                .callsQuantity(10).isPaid(true).totalCost(10.0).totalPrice(20.0)
                .expirationDate(Date.valueOf(LocalDate.of(2020, 10, 10)))
                .build();
    }

    public static SearchBetweenDatesDTO createSearchBetweenDatesDTO() {
        return SearchBetweenDatesDTO.builder().start(LocalDate.of(2020, 10, 10))
                .end(LocalDate.now()).build();
    }

    public static Call createCall() {
        return Call.builder().id(1)
                .date(Date.valueOf(LocalDate.now()))
                .destinationPhoneline(createPhoneline())
                .originPhoneline(createPhoneline())
                .invoice(createInvoice()).build();
    }

    public static Phoneline createPhoneline() {
        return Phoneline.builder().number("1234567").id(1)
                .status(true).type("mobile").city(createCity())
                .user(createClientUser()).build();
    }

    public static ClientLoginDTO createClientLoginDTO() {
        return ClientLoginDTO.builder().identification("1234567")
                .password("1234").build();
    }

    public static EmployeeLoginDTO createEmployeeLoginDTO() {
        return EmployeeLoginDTO.builder().identification("1234567")
                .password("123455").build();
    }

    public static NewCallDTO createNewCallDTO() {
        return NewCallDTO.builder().date(Date.valueOf(LocalDate.now()))
                .destinationNumber("12312332")
                .originNumber("12312321").duration(2).build();
    }
}
