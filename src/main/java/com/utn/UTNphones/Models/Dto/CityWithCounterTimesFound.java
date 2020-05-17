package com.utn.UTNphones.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.UTNphones.Models.City;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class CityWithCounterTimesFound {

    public CityWithCounterTimesFound(String a, int b){
        city.setName(a);
        count = b;
    }

    @JsonProperty
    City city;
    @JsonProperty
    Integer count;
}
