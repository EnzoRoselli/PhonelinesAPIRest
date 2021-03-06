package com.utn.UTNphones.domains.dto.requests;

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


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PhonelineDTO {

    @Pattern(regexp = "^[1-9]\\d*$", message = "Invalid number!")
    @NotBlank(message = "Number is mandatory")
    private String number;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotNull(message = "Status is mandatory")
    private Boolean status;

    @NotNull(message = "User id is mandatory")
    @Min(value = 1, message = "User id is invalid")
    private Integer userId;

    @NotNull(message = "City id is mandatory")
    @Min(value = 1, message = "City id is invalid")
    private Integer cityId;

    public boolean validNumberWithPrefix(String prefix) {
        return (String.valueOf(number).length() + String.valueOf(prefix).length()) == 10;
    }
}
