package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByIdentificationAndPassword(String ic, String password);

    User findByIdentification(String identification);

    void deleteByIdentification(String identification);
}
