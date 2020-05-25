package com.utn.UTNphones.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.stream.Stream;

@Entity
@Table(name = "phonelines")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Phoneline {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",nullable=false)
    private Integer id;

    @Pattern(regexp="^[1-9]\\d*$", message="Invalid number!")
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

    public boolean hasNullAtribute() {
        return Stream.of(number, type, city.getId(), user.getId(), status).anyMatch(x -> x == null);
    }

    public boolean validNumberWithPrefix(String prefix){
        if ((String.valueOf(number).length() + String.valueOf(prefix).length())!=10)return false;
        return true;
    }
}
