package com.utn.UTNphones.Repositories;


import com.utn.UTNphones.Domains.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City, Integer> {
}
