package com.utn.UTNphones.Domains.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SearchBetweenDatesDTO {
    @JsonProperty
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
     Date start;
    @JsonProperty
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
     Date end;
}
