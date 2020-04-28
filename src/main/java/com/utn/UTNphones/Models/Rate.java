package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;

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

    @Column(name="id_city")
    @OneToMany
    @JoinColumn(name = "id_city")
    private City city;

    @Column(name="cost_per_minute")
    private Integer cost_per_minute;


}
