package com.utn.UTNphones.repositories;

import com.utn.UTNphones.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByIdentificationAndPasswordAndType(String ic, String password, String type);

}
