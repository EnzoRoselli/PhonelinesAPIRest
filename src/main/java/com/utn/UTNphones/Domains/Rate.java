package com.utn.UTNphones.Domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name = "rates")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_origin_city")
    private City originCity;

    @ManyToOne
    @JoinColumn(name = "id_destination_city")
    private City destinationCity;

    @Column(name = "cost_per_minute")
    private Double costPerMinute;
    @Column(name = "price_per_minute")
    private Double pricePerMinute;

    public boolean hasNullAttribute() {
        return Stream.of(originCity, destinationCity, costPerMinute, pricePerMinute).anyMatch(Objects::isNull);
    }

}
