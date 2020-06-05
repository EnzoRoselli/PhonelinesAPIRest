package com.utn.UTNphones.Repositories;


import com.utn.UTNphones.Domains.Phoneline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPhonelineRepository extends JpaRepository<Phoneline, Integer> {

    List<Phoneline> findByUserId(Integer id);

    Phoneline findByNumberAndCityId(String number, Integer cityId);

    Phoneline findByNumber(String number);
}
