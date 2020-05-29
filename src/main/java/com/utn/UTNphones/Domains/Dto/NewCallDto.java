package com.utn.UTNphones.Domains.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class NewCallDto {
    @JsonProperty
    String originNumber;
    @JsonProperty
    String destinationNumber;
    @JsonProperty
    Integer duration;
    @JsonProperty
    Date date;
}
