package com.utn.UTNphones.Domains.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class SearchBetweenDates {
    @JsonProperty
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
     Date start;
    @JsonProperty
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
     Date end;
}
