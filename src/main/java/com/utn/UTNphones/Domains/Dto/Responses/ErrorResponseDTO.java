package com.utn.UTNphones.Domains.Dto.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ErrorResponseDTO {


    int code;

    String description;

    public ErrorResponseDTO(int code, String description) {
        this.code = code;
        this.description = description;
    }


}