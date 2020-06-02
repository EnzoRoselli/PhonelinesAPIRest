package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Domains.Call;
import com.utn.UTNphones.Domains.Dto.CallsWithNameAndLastname;
import com.utn.UTNphones.Domains.Dto.CityTop;
import com.utn.UTNphones.Domains.Phoneline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ICallRepository extends JpaRepository<Call, Integer> {
    List<Call> findByOriginPhonelineIn(List<Phoneline> phonelineListOrigin);

    @Query(value = "select cities.id, cities.city_name as cityName" +
            ", cities.prefix, cities.id_province as idProvince, count(cities.id) as cant from calls \n" +
            "inner join rates on rates.id=calls.id_rate\n" +
            "inner join cities on rates.id_destination_city=cities.id\n" +
            "where calls.id_origin_phone in (select phonelines.id\n" +
            " from phonelines inner join users on phonelines.id_user= ?1)\n" +
            "group by cities.id\n" +
            "order by(Cant)desc\n" +
            "LIMIT 10;", nativeQuery = true)
    List<CityTop> findTopMostCalledCities(Integer userId);

    List<Call> findAllByOriginPhonelineUserIdAndDateBetween(Integer userId,Date Start, Date End);

    List<Call> findAllByOriginPhonelineUserIdAndDateBefore(Integer userId, Date endDate);

    List<Call> findAllByOriginPhonelineUserIdAndDateAfter(Integer userId, Date startDate);
    @Query(value="select count(calls.id) as quantity,users.user_name as name, users.lastname as lastname from calls \n" +
            "inner join phonelines ph on ph.id=calls.id_origin_phone\n" +
            "inner join users on users.id=ph.id_user\n" +
            "where YEAR(calls.date_call) = ?1 AND MONTH(calls.date_call) = ?2 AND DAY(calls.date_call)=?3\n" +
            "group by(users.id);",
            nativeQuery = true)
    List<CallsWithNameAndLastname> findAllByDate(int year, int month,int day);
}
