package com.utn.UTNphones.Domains;

import com.utn.UTNphones.Domains.Dto.LoginDTO;
import com.utn.UTNphones.Domains.Dto.UserRegisterDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class User {

    public User(LoginDTO loginDTO){
        identification = loginDTO.getIdentification();
        password = loginDTO.getPassword();
    }

    public User(UserRegisterDTO userRegisterDTO){
        name = userRegisterDTO.getName();
        lastname = userRegisterDTO.getLastname();
        status = userRegisterDTO.getStatus();
        type = userRegisterDTO.getType();
        identification = userRegisterDTO.getIdentification();
        password = userRegisterDTO.getPassword();
        city = City.builder().id(userRegisterDTO.getCityId()).build();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Pattern(regexp="^[\\p{L} .'-]+$", message="Invalid name!")
    @Column(name = "name_user")
    private String name;

    @Pattern(regexp="^[\\p{L} .'-]+$", message="Invalid lastname!")
    @Column(name = "lastname")
    private String lastname;

    @Column(name = "status_user")
    private Boolean status;

    @Column(name = "type_user")
    private String type;

    @Column(name = "identification_card")
    private String identification;

    @Column(name = "password_user")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;
}

