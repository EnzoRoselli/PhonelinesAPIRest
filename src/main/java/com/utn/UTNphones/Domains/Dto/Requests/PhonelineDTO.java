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
@RequiredArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PhonelineDTO {

    @Pattern(regexp = "^[1-9]\\d*$", message = "Invalid number!")
    @NotBlank(message = "Number is mandatory")
    private final String number;

    @NotBlank(message = "Type is mandatory")
    private final String type;

    @NotNull(message = "Status is mandatory")
    private final Boolean status;

    @NotNull(message = "User id is mandatory")
    @Min(value = 1, message = "User id is invalid")
    private final Integer userId;

    @NotNull(message = "City id is mandatory")
    @Min(value = 1, message = "City id is invalid")
    private final Integer cityId;

    public boolean validNumberWithPrefix(String prefix) {
        return (String.valueOf(number).length() + String.valueOf(prefix).length()) == 10;
    }
}
