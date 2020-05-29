package com.utn.UTNphones.Domains;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name="provinces")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Province {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="province_name")
    private String name;

    public boolean hasNullAttribute(){

        return Stream.of(name).anyMatch(Objects::isNull);
    }
}
