package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;
import java.util.stream.Stream;

@Entity
@Table(name="phonelines")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Phoneline {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "phone_number")
    private Integer number;

    @Column(name = "type_user")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_city")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;


    public boolean hasNullAtribute(){
        if (Stream.of(number, type,city,user).anyMatch(x -> x == null)) {
            return true;
        }else{
            return false;
        }
    }
}
