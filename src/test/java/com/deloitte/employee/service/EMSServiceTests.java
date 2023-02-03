package com.deloitte.employee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.employee.Employee;
import com.deloitte.employee.dao.EMSDao;

@SpringBootTest
public class EMSServiceTests {
	
	@Autowired
	private EMSService service;
	
	@Test
	public void listAllEmployeeTest() {		
		assertThat(service.listAll()).isNotEmpty();
		
	}

	@Test
	public void saveEmployeeTest() {
		Employee emp = Employee.builder()
				.firstName("Tester")
				.lastName("Employee")
				.designation("Tester")
				.domain("Testing")
				.address("Hyderabad")
				.location("Telungana")
				.email("tester@deloitte.com")
				.date("2006-05-26")
				.build();
		
		service.save(emp);
		assertEquals(emp.getFirstName(),"Tester");
		
	}

	@Test
	@Transactional
	public void getEmployeeTest() {
		Employee emp = service.get(154);
		System.out.println(emp.getFirstName());
		assertThat(emp.getEmpId()).isEqualTo(154);
		
	}
	
	@Autowired
	EMSDao Dao;
	
	@Test
	public void deleteEmployeeTest() {
		service.delete(Dao.findByFirstName("Tester").get().getEmpId());
		
		Employee checker = null;
		Optional<Employee> optionalEmp = Dao.findByFirstName("Tester");
		if(optionalEmp.isPresent()) {
			checker = optionalEmp.get();
		}
		assertThat(checker).isNull();
		
	}
	
}
