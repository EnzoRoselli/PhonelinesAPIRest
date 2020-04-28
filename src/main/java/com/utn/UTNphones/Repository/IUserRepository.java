package com.utn.UTNphones.Repository;

import com.utn.UTNphones.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
