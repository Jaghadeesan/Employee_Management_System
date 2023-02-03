package com.deloitte.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.employee.Employee;
import com.deloitte.employee.dao.EMSDao;

@Service
public class EMSService {
	
	@Autowired
	private EMSDao dao;
	
	public List<Employee> listAll() {
		return dao.findAll();
	}
	
	public void save(Employee emp) {
		dao.save(emp);
	}
	
	public Employee get(int id) {
		return dao.getReferenceById(id);
	}
	
	public void delete(int id) {
		dao.deleteById(id);
	}
}
