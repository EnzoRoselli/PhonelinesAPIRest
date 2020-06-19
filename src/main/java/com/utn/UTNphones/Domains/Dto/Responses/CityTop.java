package com.utn.UTNphones.Domains.Dto.Responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public interface CityTop {

    Integer getId();

    String getCityName();

    String getPrefix();

    Integer getIdProvince();

    Integer getCant();

}
