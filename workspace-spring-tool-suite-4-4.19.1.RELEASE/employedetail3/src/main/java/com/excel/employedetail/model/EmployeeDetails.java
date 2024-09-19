package com.excel.employedetail.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "emp_details1")
@Setter
@Getter
public class EmployeeDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "empid must be enter & diffrent")
	private String empid;
	private String firstname;
	private String lastname;
	private Integer age;
	private String email;
	private String password;
	private Date dateOfBirth;

	private String gender;

	private String department;

	private String jobTitle;

	private BigDecimal salary;

	private Date hireDate;

	private String contactInfo;
	@Transient
	private String employeeStatus;
//	public void setDateOfBirth(java.util.Date dateCellValue) {
//		// TODO Auto-generated method stub
//		
//	}
	
	@OneToMany(targetEntity=Address.class, mappedBy="empDetais",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	@JsonManagedReference
	private List<Address> addresses;
	
	
public EmployeeDetails(Long id, String empid, String firstname, String lastname, Integer age, String email,
		Date dateOfBirth, String gender, String department, String jobTitle, BigDecimal salary, Date hireDate,
		String contactInfo, String employeeStatus, List<Address> addresses) {
	super();
	this.id = id;
	this.empid = empid;
	this.firstname = firstname;
	this.lastname = lastname;
	this.age = age;
	this.email = email;
	this.dateOfBirth = dateOfBirth;
	this.gender = gender;
	this.department = department;
	this.jobTitle = jobTitle;
	this.salary = salary;
	this.hireDate = hireDate;
	this.contactInfo = contactInfo;
	this.employeeStatus = employeeStatus;
	this.addresses = addresses;
}


public EmployeeDetails() {

	// TODO Auto-generated constructor stub
}


@Override
public String toString() {
	return "EmployeeDetails [id=" + id + ", empid=" + empid + ", firstname=" + firstname + ", lastname=" + lastname
			+ ", age=" + age + ", email=" + email + ", password=" + password + ", dateOfBirth=" + dateOfBirth
			+ ", gender=" + gender + ", department=" + department + ", jobTitle=" + jobTitle + ", salary=" + salary
			+ ", hireDate=" + hireDate + ", contactInfo=" + contactInfo + ", employeeStatus=" + employeeStatus
			+ ", addresses=" + addresses + "]";
}
	
	

}
