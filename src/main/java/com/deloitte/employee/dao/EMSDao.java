package com.deloitte.employee.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.employee.Employee;

@Repository
public interface EMSDao extends JpaRepository<Employee, Integer>{
	Optional<Employee> findByFirstName(String firstName);
	Optional<Employee> findByEmail(String email);
	
}
