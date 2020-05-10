package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.Phoneline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICallRepository extends JpaRepository<Call,Integer> {
    List<Call> findByOriginPhonelineIn(List<Phoneline> phonelineListOrigin);
    @Query(value = "select cities.* , count(cities.id) as contador  from calls \n" +
            "inner join rates on rates.id=calls.id_rate\n" +
            "inner join cities on rates.id_destination_city=cities.id\n" +
            "where calls.id_origin_phone in (select phonelines.id\n" +
            " from phonelines inner join users on phonelines.id_user=?1)\n" +
            "group by cities.id\n" +
            "order by(contador)desc\n" +
            "LIMIT 3; ", nativeQuery = true)
    List<Object> findTopMostCalledCities(Integer userId);
    int disableOrEnable(Boolean newStatus,String phoneNumber);
}
