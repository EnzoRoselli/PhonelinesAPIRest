package com.utn.UTNphones.Repositories;


import com.utn.UTNphones.Domains.Phoneline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IPhonelineRepository extends JpaRepository<Phoneline,Integer> {
    @Transactional
    @Modifying
    @Query(value = "update phonelines ph set ph.status_phoneline = ?1 where ph.phone_number = ?2", nativeQuery = true)
    int disableOrEnable(Boolean newStatus,String phoneNumber);
    List<Phoneline> findByUserId(Integer id);
    Phoneline findByNumberAndCityId(String number, Integer cityId);
    Phoneline findByNumber(String number);
    @Transactional
    @Modifying
    long removeByNumber(String number);

}
