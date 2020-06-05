package com.utn.UTNphones.Domains.Dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Data
@ToString
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserUpdateDTO {

    @NotNull(message = "id is mandatory")
    private final Integer id;

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid name!")
    private final String name;

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid lastname!")
    private final String lastname;

    @NotNull(message = "Status is mandatory")
    private final Boolean status;

    @NotBlank(message = "Type is mandatory")
    private final String type;

    @Pattern(regexp = "^[1-9]{7,9}$", message = "Invalid identification!")
    @NotBlank(message = "Identification is mandatory")
    private final String identification;

    @NotBlank(message = "Password is mandatory")
    private final String password;

    @NotNull(message = "City id is mandatory")
    @Min(value = 1, message = "City id is invalid")
    private final Integer cityId;
}
