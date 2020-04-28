package com.utn.UTNphones.Repository;


import com.utn.UTNphones.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
}
