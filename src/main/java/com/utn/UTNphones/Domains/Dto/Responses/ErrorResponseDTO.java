package com.utn.UTNphones.Domains.Dto.Responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Data
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ErrorResponseDTO {

    private final int code;

    private final String description;

    public static ErrorResponseDTO fromRunTimeException(RuntimeException rt, Integer code) {
        return ErrorResponseDTO.builder().description(rt.getMessage()).code(code).build();
    }
}