package com.utn.UTNphones.Domains;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Pattern(regexp="^[\\p{L} .'-]+$", message="Invalid name!")
    @Column(name = "name_user")
    private String name;
    @Pattern(regexp="^[\\p{L} .'-]+$", message="Invalid lastname!")
    @Column(name = "lastname")
    private String lastname;

    @Column(name = "type_user")
    private String type;

    @Column(name = "identification_card")
    @Pattern(regexp="^[1-9]{6,9}$", message="Invalid identification!")
    private String identification;

    @Column(name = "password_user")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;

    public boolean hasNullAtribute() {
        return Stream.of(name, lastname, identification, password, city, type).anyMatch(Objects::isNull);
    }


    //todo pasarlo al service




}

