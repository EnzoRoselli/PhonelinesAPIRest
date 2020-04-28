package com.utn.UTNphones.Repository;

import com.utn.UTNphones.Models.Call;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICallRepository extends JpaRepository<Call,Integer> {
}
