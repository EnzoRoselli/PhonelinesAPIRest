package com.utn.UTNphones.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.stream.Stream;

@Entity
@Table(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phone_number")
    private Phoneline phoneline;

    @Column(name = "calls_quantity")
    private Integer callsQuantity;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "total_cost")
    private Integer totalCost;

    @Column(name = "invoice_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "invoice_expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    public boolean hasNullAtribute() {
        if (Stream.of(phoneline, callsQuantity, totalPrice, isPaid, date, expirationDate,totalCost).anyMatch(x -> x == null)) {
            return true;
        } else {
            return false;
        }
    }
}
