package com.utn.UTNphones.Repositories;


import com.utn.UTNphones.Models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends JpaRepository<Province,Integer> {
}
