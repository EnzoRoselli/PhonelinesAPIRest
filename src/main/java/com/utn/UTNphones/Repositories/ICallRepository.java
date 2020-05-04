package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Models.Call;
import com.utn.UTNphones.Models.Phoneline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICallRepository extends JpaRepository<Call,Integer> {
    List<Call> findByOriginPhonelineIn(List<Phoneline> phonelineListOrigin);
}
