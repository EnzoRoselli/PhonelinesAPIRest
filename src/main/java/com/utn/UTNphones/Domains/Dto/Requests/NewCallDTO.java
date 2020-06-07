package com.utn.UTNphones.Domains.Dto.Requests;

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

    String originNumber;

    String destinationNumber;

    Integer duration;

    Date date;
}
