package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByIdentificationAndPassword(Integer ic,String password);
}
