package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Models.Call;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICallRepository extends JpaRepository<Call,Integer> {
}
