package com.utn.UTNphones.repositories;


import com.utn.UTNphones.domains.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City, Integer> {
}
