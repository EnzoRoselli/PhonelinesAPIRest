package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;
import java.util.stream.Stream;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name_user")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name="type_user")
    private String type;

    @Column(name = "identification_card")
    private Integer identification;

    @Column(name = "password_user")
    private String password;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city",nullable=false, updatable=false)
    private City city;

    public boolean hasNullAtribute(){
        if (Stream.of(name, lastname,identification,password,city,type).anyMatch(x -> x == null)) {
            return true;
        }else{
            return false;
        }
    }

}

