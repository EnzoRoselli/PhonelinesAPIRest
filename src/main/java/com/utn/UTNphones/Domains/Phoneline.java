package com.utn.UTNphones.Domains;

import com.utn.UTNphones.Domains.Dto.PhonelineDTO;
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

@Entity
@Table(name = "phonelines")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Phoneline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "phone_number")
    private String number;
    @Column(name = "phoneline_type")
    private String type;
    @Column(name = "phoneline_status")
    private Boolean status;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;


    public static Phoneline fromDto(PhonelineDTO phone) {
        return Phoneline.builder().number(phone.getNumber())
                .type(phone.getType())
                .status(phone.getStatus())
                .user(User.builder().id(phone.getUserId()).build())
                .city(City.builder().id(phone.getCityId()).build()).build();
    }
}
