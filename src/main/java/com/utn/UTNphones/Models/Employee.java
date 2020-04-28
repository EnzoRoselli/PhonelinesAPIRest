package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="employees")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name_employee")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "identification_card")
    private Integer identification;

    @Column(name = "password_employee")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_city")
    private City city;
}
