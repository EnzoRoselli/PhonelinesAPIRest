package com.utn.UTNphones.domains.dto.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public interface CityTop {

    Integer getId();

    void setId(Integer id);

    String getCityName();

    void setCityName(String cityName);

    String getPrefix();

    void setPrefix(String prefix);

    Integer getIdProvince();

    void setIdProvince(Integer idProvince);

    Integer getCant();

    void setCant(Integer cant);

}
