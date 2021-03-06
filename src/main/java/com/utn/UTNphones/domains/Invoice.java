package com.utn.UTNphones.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

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
    @JoinColumn(name = "id_phone_number")
    private Phoneline phoneline;

    @Column(name = "calls_quantity")
    private Integer callsQuantity;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "invoice_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "invoice_expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

}
