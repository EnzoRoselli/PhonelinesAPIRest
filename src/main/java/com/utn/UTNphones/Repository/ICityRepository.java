package com.utn.UTNphones.Repository;


import com.utn.UTNphones.Models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<City,Integer> {
}
