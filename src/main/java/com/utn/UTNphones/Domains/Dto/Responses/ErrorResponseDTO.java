package com.utn.UTNphones.Domains.Dto.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class ErrorResponseDTO {

    @JsonProperty
    int code;
    @JsonProperty
    String description;

    public ErrorResponseDTO(int code, String description) {
        this.code = code;
        this.description = description;
    }


}