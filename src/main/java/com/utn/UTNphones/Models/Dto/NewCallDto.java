package com.utn.UTNphones.Models.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.stream.Stream;

@NoArgsConstructor
@Data
@ToString
@Builder
public class NewCallDto {
    @JsonProperty
    String originNumber;
    @JsonProperty
    String destinationNumber;
    @JsonProperty
    int duration;
    @JsonProperty
    Date date;

    public boolean hasNullAtribute(){
        return Stream.of(originNumber, destinationNumber, duration,date).anyMatch(x -> x == null);
    }
}
