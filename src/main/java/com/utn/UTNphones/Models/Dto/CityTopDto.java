package com.utn.UTNphones.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.UTNphones.Models.City;
import lombok.*;

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
