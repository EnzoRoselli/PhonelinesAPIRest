package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;
import java.util.stream.Stream;

@Entity
@Table(name="cities")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class City {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="city_name")
    private String name;

    @Column(name="prefix")
    private Integer prefix;

    @ManyToOne
    @JoinColumn(name = "id_province")
    private Province province;


    public boolean hasNullAtribute(){
        if (Stream.of(name, prefix,province).anyMatch(x -> x == null)) {
            return true;
        }else{
            return false;
        }
    }
}
