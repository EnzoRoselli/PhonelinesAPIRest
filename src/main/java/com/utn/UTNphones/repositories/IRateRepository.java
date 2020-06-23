package com.utn.UTNphones.repositories;


import com.utn.UTNphones.domains.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRateRepository extends JpaRepository<Rate, Integer> {
    Optional<Rate> findByOriginCityIdAndDestinationCityId(Integer originId, Integer destinationId);

    List<Rate> findByOriginCityId(Integer originId);

    List<Rate> findByDestinationCityId(Integer originId);
}
