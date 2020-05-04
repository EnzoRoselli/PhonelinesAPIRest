package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;
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
    private Float costPerMinute;

    public boolean hasNullAtribute(){
        if (Stream.of(originCity, destinationCity,costPerMinute).anyMatch(x -> x == null)) {
            return true;
        }else{
            return false;
        }
    }

}
