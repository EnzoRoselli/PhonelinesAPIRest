package com.utn.UTNphones.Domains;

import com.utn.UTNphones.Domains.Dto.Requests.NewCallDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @JoinColumn(name = "id_origin_phone")
    private Phoneline originPhoneline;

    @Column(name = "origin_phone")
    private String originPhone;

    @ManyToOne
    @JoinColumn(name = "id_destination_phone")
    private Phoneline destinationPhoneline;

    @Column(name = "destination_phone")
    private String destinationPhone;

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
    private Double total_price;
    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "duration")
    private Integer duration;

    public static Call fromDto(NewCallDTO newCallDTO) {
        return Call.builder().originPhone(newCallDTO.getOriginNumber())
                .destinationPhone(newCallDTO.getDestinationNumber())
                .duration(newCallDTO.getDuration())
                .date(newCallDTO.getDate())
                .build();
    }


}
