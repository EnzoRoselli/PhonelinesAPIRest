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

@Entity
@Table(name = "cities")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "city_name")
    private String name;
    @Column(name = "prefix")
    private String prefix;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_province")
    private Province province;

}
