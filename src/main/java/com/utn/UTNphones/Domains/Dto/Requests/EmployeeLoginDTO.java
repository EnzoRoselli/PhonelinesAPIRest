package com.utn.UTNphones.Domains.Dto.Requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EmployeeLoginDTO implements Login {

    @Pattern(regexp = "^[1-9]{7,9}$", message = "Invalid identification!")
    @NotBlank(message = "identification is mandatory")
    private  String identification;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private final String type = "employee";

}
