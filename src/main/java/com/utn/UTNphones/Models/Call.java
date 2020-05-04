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
    @JoinColumn(name = "destination_phone")
    private Phoneline destinationPhoneline;

    @ManyToOne
    @JoinColumn(name = "id_rate")
    private Rate rate;

    @ManyToOne
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @Column(name = "date_call")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "total_price")
    private Integer total_price;
    @Column(name = "total_cost")
    private Integer totalCost;

    @Column(name = "duration")
    private Integer duration;




    public boolean hasNullAtribute() {
        if (Stream.of(originPhoneline, destinationPhoneline, rate, date, total_price, duration, invoice,totalCost).anyMatch(x -> x == null)) {
            return true;
        } else {
            return false;
        }
    }


}
