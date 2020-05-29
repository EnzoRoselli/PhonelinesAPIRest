package com.utn.UTNphones.Domains;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name="rates")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Rate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_origin_city")
    private City originCity;

    @ManyToOne
    @JoinColumn(name = "id_destination_city")
    private City destinationCity;

    @Column(name="cost_per_minute")
    private Double costPerMinute;
    @Column(name="price_per_minute")
    private Double pricePerMinute;

    public boolean hasNullAtribute(){
        return Stream.of(originCity, destinationCity, costPerMinute, pricePerMinute).anyMatch(Objects::isNull);
    }

}
