package com.utn.UTNphones.utils;

import com.utn.UTNphones.domains.Call;
import com.utn.UTNphones.domains.City;
import com.utn.UTNphones.domains.Invoice;
import com.utn.UTNphones.domains.Phoneline;
import com.utn.UTNphones.domains.Province;
import com.utn.UTNphones.domains.Rate;
import com.utn.UTNphones.domains.User;
import com.utn.UTNphones.domains.dto.requests.ClientLoginDTO;
import com.utn.UTNphones.domains.dto.requests.EmployeeLoginDTO;
import com.utn.UTNphones.domains.dto.requests.NewCallDTO;
import com.utn.UTNphones.domains.dto.requests.PhonelineDTO;
import com.utn.UTNphones.domains.dto.requests.SearchBetweenDatesDTO;
import com.utn.UTNphones.domains.dto.requests.UserDTO;
import com.utn.UTNphones.domains.dto.responses.CityTop;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.sql.Date;
import java.time.LocalDate;

public class ObjectCreator {

    public static User createClientUser() {
        return User.builder().id(1).status(true)
                .identification("12345678").password("abc")
                .name("Facundinho").lastname("Silva").type("client").build();
    }

    public static CityTop createCityTop() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        CityTop a = factory.createProjection(CityTop.class);
        a.setPrefix("223");
        a.setId(1);
        a.setCant(10);
        a.setCityName("Rio de Janeiro");
        a.setIdProvince(1);
        return a;
    }

    public static User createEmployeeUser() {
        return User.builder().id(1).status(true)
                .identification("12345678").password("abaac")
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
