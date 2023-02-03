package com.deloitte.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="employee_details")
public class Employee {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "empid")
	private int empId;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "domain")
	private String domain;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "joiningdate")
	private String date;

	
	
}
