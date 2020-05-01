package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Models.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICallRepository extends JpaRepository<Call,Integer> {
}
