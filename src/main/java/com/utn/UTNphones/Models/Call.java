package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="calls")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Call {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_origin_phone")
    private Phoneline originPhoneline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destination_phone")
    private Phoneline destinationPhoneline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rate")
    private Rate rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @Column(name = "price")
    private Integer price;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "totalPrice")
    private Integer totalPrice;





}
