package com.utn.UTNphones.Domains.Dto.Requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@Data
@ToString
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserPatchUpdateDTO {

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid name!")
    private final String name;

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Invalid lastname!")
    private final String lastname;

    private final Boolean status;

    private final String type;

    @Pattern(regexp = "^[1-9]{7,9}$", message = "Invalid identification!")
    private final String identification;

    private final String password;

    private final Integer cityId;
}
