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
    @Column(name="id",nullable=false, updatable=false)
    private Integer id;

    @Column(name = "phone_number")
    private Integer number;

    @Column(name = "type_user")
    private String type;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city",nullable=false)
    private City city;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user",nullable=false)
    private User user;


    public boolean hasNullAtribute(){
        if (Stream.of(number, type,city,user).anyMatch(x -> x == null)) {
            return true;
        }else{
            return false;
        }
    }
}
