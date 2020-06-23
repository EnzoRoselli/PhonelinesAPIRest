package com.utn.UTNphones.repositories;


import com.utn.UTNphones.domains.Phoneline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPhonelineRepository extends JpaRepository<Phoneline, Integer> {

    List<Phoneline> findByUserId(Integer id);

    Optional<Phoneline> findByNumber(String number);
}
