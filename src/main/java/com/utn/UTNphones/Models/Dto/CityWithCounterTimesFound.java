package com.utn.UTNphones.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.UTNphones.Models.City;
import lombok.*;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class CityWithCounterTimesFound {

    public CityWithCounterTimesFound(String a, BigInteger b){
        city.setName(a);
        count = b;
    }

    @JsonProperty
    City city;
    @JsonProperty
    BigInteger count;
}
