package com.utn.UTNphones.Repositories;


import com.utn.UTNphones.Models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRateRepository extends JpaRepository<Rate,Integer> {


}
