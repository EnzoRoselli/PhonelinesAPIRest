package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;

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
    private String name_user;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "identification_card")
    private Integer identification;
}

