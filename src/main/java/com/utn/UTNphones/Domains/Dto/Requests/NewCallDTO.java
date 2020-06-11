package com.utn.UTNphones.Domains.Dto.Requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
@Builder
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class NewCallDTO {
    //todo corroboraciones
   private final String originNumber;

    private final  String destinationNumber;

    private final Integer duration;

    private final  Date date;
}
