package com.utn.UTNphones.Domains;

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
import javax.persistence.Table;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name = "provinces")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "province_name")
    private String name;

    public boolean hasNullAttribute() {

        return Stream.of(name).anyMatch(Objects::isNull);
    }
}
