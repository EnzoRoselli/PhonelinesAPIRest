package com.utn.UTNphones.Domains.Dto.Requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginDTO {

    @Pattern(regexp = "^[1-9]{7,9}$", message = "Invalid identification!")
    private String identification;

    @NotBlank(message = "Password is mandatory")
    private String password;

}
