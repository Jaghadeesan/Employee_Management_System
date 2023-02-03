package com.deloitte.employee;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeTest {
	@Test
	void contextLoads() {
	}
	
	@Test
	public void toStringTest() {
		assertNotNull(new Employee().toString());
	}

}
