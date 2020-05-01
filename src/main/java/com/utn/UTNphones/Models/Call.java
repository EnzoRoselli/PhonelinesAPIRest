package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.stream.Stream;

@Entity
@Table(name = "calls")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "origin_phone")
    private Phoneline originPhoneline;

    @ManyToOne
    @JoinColumn(name = "destination_phone", nullable = false, updatable = false)
    private Phoneline destinationPhoneline;

    @ManyToOne
    @JoinColumn(name = "id_rate", nullable = false, updatable = false)
    private Rate rate;

    @ManyToOne
    @JoinColumn(name = "id_invoice", nullable = false, updatable = false)
    private Invoice invoice;

    @Column(name = "date_call")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "price")
    private Integer price;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "totalPrice")
    private Integer totalPrice;

    public boolean hasNullAtribute() {
        if (Stream.of(originPhoneline, destinationPhoneline, rate, date, price, duration, totalPrice,invoice).anyMatch(x -> x == null)) {
            return true;
        } else {
            return false;
        }
    }


}
