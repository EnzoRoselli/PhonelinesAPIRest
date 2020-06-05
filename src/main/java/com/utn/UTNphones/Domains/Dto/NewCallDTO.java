package com.utn.UTNphones.Domains.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class NewCallDTO {
    @JsonProperty
    String originNumber;
    @JsonProperty
    String destinationNumber;
    @JsonProperty
    Integer duration;
    @JsonProperty
    Date date;
}
