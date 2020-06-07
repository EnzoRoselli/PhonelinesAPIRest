package com.utn.UTNphones.Repositories;

import com.utn.UTNphones.Domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    User findByIdentificationAndPasswordAndType(String ic, String password,String type);

    User findByIdentificationAndPasswordAndTypeAndStatus(String ic, String password,String type,Boolean status);

    User findByIdentification(String identification);
}
