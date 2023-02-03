package com.deloitte.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.employee.Employee;
import com.deloitte.employee.service.EMSService;

@Controller
public class EMSController{
	
	@Autowired
	EMSService service;
	
	// Home page will be listed with employee details
	@RequestMapping("/")
	public String HomePage(Model model) {
		List<Employee> list = service.listAll();
		Employee emp = new Employee();
		model.addAttribute("emp", emp);
		model.addAttribute("employeeList", list);
//		System.out.println(model);
		return "homepage";		// redirects to homepage.html web page
		
	}
	
	// Creating a new employee
	// addEmployee --> newEmployeeForm --> saveEmployee --> HomePage
	@GetMapping(value = "/addEmployee")
//	@ResponseBody
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "newEmployeeForm";	// redirects to newEmployeeForm.html web page
	}
	
	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	
	public String saveEmployee(@ModelAttribute Employee emp) { 
		service.save(emp);
		return "redirect:/";
	}
	
	@RequestMapping("/updateEmployee/{id}")
	public ModelAndView updateEmployee(@PathVariable int id) { 
		ModelAndView mav = new ModelAndView("newEmployeeForm");
		Employee emp = service.get(id);
		mav.addObject("employee", emp);
		return mav;
	}
		
	@RequestMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable int id) {
		service.delete(id);
		return "redirect:/";
	}	
	
	@RequestMapping(value = "/getEmployee", method = RequestMethod.POST)
	public String getEmployee(Model model, @ModelAttribute Employee emp) {
		Employee getEmp = service.get(emp.getEmpId());
		model.addAttribute("employee", getEmp);
		return "getemployee";
	}

}
