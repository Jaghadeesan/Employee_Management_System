package com.deloitte.employee.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.employee.Employee;
import com.deloitte.employee.dao.EMSDao;


@SpringBootTest
class EMSControllerTests {

	@Autowired
	EMSController ctrl;
	
	@Autowired
	EMSDao dao;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void homePageTest() throws Exception {
		this.mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("homepage"))
			    .andExpect(model().attributeExists("emp"))
			    .andExpect(model().attributeExists("employeeList"))
				.andReturn();
		
	}

	@Test
	public void addEmployeeTest() throws Exception {
		mockMvc.perform(get("/addEmployee"))
				.andExpect(status().isOk())
				.andExpect(view().name("newEmployeeForm"))
			    .andExpect(model().attributeExists("employee"))
				.andReturn();
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
		assertEquals("redirect:/", ctrl.saveEmployee(emp));
		
	}
	
	@Test
	public void updateEmployeeTest() {
		assertThat(ctrl.updateEmployee(154)).isInstanceOf(ModelAndView.class);
	}
	
	@Test
	public void deleteEmployeeTest() {
		int id = dao.findByFirstName("Tester").get().getEmpId();
		assertEquals(ctrl.deleteEmployee(id), "redirect:/");
	}
	
	@Test
	public void getEmployeeTest() throws Exception {
		int id = dao.findByFirstName("Jaghadeesan").get().getEmpId();
		mockMvc.perform(post("/getEmployee")
				.param("empId",Integer.toString(id))
				.flashAttr("emp", new Employee()))
				.andExpect(status().isOk());
	}
}
