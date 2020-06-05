package com.utn.UTNphones.Domains;

import com.utn.UTNphones.Domains.Dto.LoginDTO;
import com.utn.UTNphones.Domains.Dto.UserRegisterDTO;
import com.utn.UTNphones.Domains.Dto.UserUpdateDTO;
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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "user_status")
    private Boolean status;
    @Column(name = "user_type")
    private String type;
    @Column(name = "identification_card")
    private String identification;
    @Column(name = "user_password")
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;

    public User(LoginDTO loginDTO) {
        identification = loginDTO.getIdentification();
        password = loginDTO.getPassword();
    }

    public User(UserRegisterDTO userRegisterDTO) {
        name = userRegisterDTO.getName();
        lastname = userRegisterDTO.getLastname();
        status = userRegisterDTO.getStatus();
        type = userRegisterDTO.getType();
        identification = userRegisterDTO.getIdentification();
        password = userRegisterDTO.getPassword();
        city = City.builder().id(userRegisterDTO.getCityId()).build();
    }
    public User(UserUpdateDTO userUpdateDTO) {
        id= userUpdateDTO.getId();
        name = userUpdateDTO.getName();
        lastname = userUpdateDTO.getLastname();
        status = userUpdateDTO.getStatus();
        type = userUpdateDTO.getType();
        identification = userUpdateDTO.getIdentification();
        password = userUpdateDTO.getPassword();
        city = City.builder().id(userUpdateDTO.getCityId()).build();
    }
}

