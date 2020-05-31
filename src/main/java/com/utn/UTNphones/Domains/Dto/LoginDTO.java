package com.utn.UTNphones.Domains.Dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginDTO {
    @Pattern(regexp="^[1-9]{6,9}$", message="Invalid identification!")
    private String identification;

    @NotBlank(message = "Password is mandatory")
    private String password;

}
