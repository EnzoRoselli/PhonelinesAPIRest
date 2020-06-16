package com.utn.UTNphones.Domains.Dto.Requests;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@ToString
@Builder
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SearchBetweenDatesDTO {

    @JsonDeserialize(using = LocalDateTimeDeserializer.class, as = LocalDateTime.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotBlank(message = "StartDate is mandatory")
    private final LocalDate start;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class, as = LocalDateTime.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotBlank(message = "EndDate is mandatory")
    private final LocalDate end;

    public static SearchBetweenDatesDTO fromDates(LocalDate start, LocalDate end) {
        return SearchBetweenDatesDTO.builder()
                .start(start)
                .end(end)
                .build();
    }
}
