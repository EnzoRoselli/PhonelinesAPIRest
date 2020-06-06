package com.utn.UTNphones.Domains.Dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDTO {

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid name!")
    private String name;

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid lastname!")
    private String lastname;

    @NotNull(message = "Status is mandatory")
    private Boolean status;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @Pattern(regexp = "^[1-9]{7,9}$", message = "Invalid identification!")
    @NotBlank(message = "Identification is mandatory")
    private String identification;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotNull(message = "City id is mandatory")
    @Min(value = 1, message = "City id is invalid")
    private Integer cityId;
}
