package com.utn.UTNphones.Domain.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

@NoArgsConstructor
@Data
@ToString
@Builder
public class CityTopDto {

    public CityTopDto(BigInteger id, BigInteger b){
        id = id;
        count = b;
    }

    @JsonProperty
    BigInteger id;
    @JsonProperty
    BigInteger count;
}
