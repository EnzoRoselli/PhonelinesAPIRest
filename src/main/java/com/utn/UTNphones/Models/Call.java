package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.stream.Stream;

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

    @Column(name = "date_call")
    private Date date;

    @Column(name = "price")
    private Integer price;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "totalPrice")
    private Integer totalPrice;

    public boolean hasNullAtribute(){
        if (Stream.of(originPhoneline, destinationPhoneline,rate,date,price,duration,totalPrice).anyMatch(x -> x == null)) {
            return true;
        }else{
            return false;
        }
    }




}
