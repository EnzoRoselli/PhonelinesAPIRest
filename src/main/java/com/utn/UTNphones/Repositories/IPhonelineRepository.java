package com.utn.UTNphones.Repositories;


import com.utn.UTNphones.Domains.Phoneline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPhonelineRepository extends JpaRepository<Phoneline, Integer> {

    List<Phoneline> findByUserId(Integer id);

    Optional<Phoneline> findByNumberAndCityId(String number, Integer cityId);

    Optional<Phoneline> findByNumber(String number);
}
