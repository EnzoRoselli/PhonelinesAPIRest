package com.utn.UTNphones.Domains.Dto.Requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ToString
@Builder
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDTO {

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid name!")
    @NotBlank(message = "Name is mandatory")
    private final String name;

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid lastname!")
    @NotBlank(message = "Last name is mandatory")
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
