package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByIdentificationAndPassword(String ic, String password);

    Optional<User> findByIdentification(String identification);
}
