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

    @OneToMany
    @JoinColumn(name = "id_origin_city")
    private City originCity;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destination_city")
    private City destinationCity;

    private Province province;
    @Column(name="cost_per_minute")
    private Integer costPerMinute;


}
