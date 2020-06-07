package com.utn.UTNphones.Domains.Dto.Requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class NewCallDTO {
    //todo corroboraciones
    String originNumber;

    String destinationNumber;

    Integer duration;

    Date date;
}
