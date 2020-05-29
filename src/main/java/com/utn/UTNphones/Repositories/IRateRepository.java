package com.utn.UTNphones.Repositories;


import com.utn.UTNphones.Domains.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRateRepository extends JpaRepository<Rate,Integer> {
    Optional<Rate> findByOriginCityIdAndDestinationCityId(Integer originId,Integer destinationId);

}
