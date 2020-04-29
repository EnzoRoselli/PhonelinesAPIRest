package com.utn.UTNphones.Repositories;


import com.utn.UTNphones.Models.Phoneline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhonelineRepository extends JpaRepository<Phoneline,Integer> {
}
