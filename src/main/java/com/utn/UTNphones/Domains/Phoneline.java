package com.utn.UTNphones.Domains;

import com.utn.UTNphones.Domains.Dto.PhonelineAddDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name = "phonelines")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Phoneline {

    public Phoneline(PhonelineAddDto phone){
        this.number = phone.getNumber();
        type = phone.getType();
        status = phone.getStatus();
        user = User.builder().id(phone.getUserId()).build();
        city = City.builder().id(phone.getCityId()).build();
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",nullable=false)
    private Integer id;

    @Column(name = "phone_number")
    private String number;

    @Column(name = "phoneline_type")
    private String type;

    @Column(name = "status_phoneline")
    private Boolean status;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;
}
