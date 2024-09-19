package com.excel.empdetails.service;

import java.util.List;
import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.excel.empdetails.model.Employee;

@Repository
@EnableScan
public interface MasterRepo extends CrudRepository<Employee, String> {

	Optional<Employee> findByEmail(String email);

	List<Employee> findAllByfirstName(String firstName);

	List<Employee> findAllBylastName(String lastName);

	List<Employee> findAllByEmail(String email);

	List<Employee> findAllByDate(String date);

	List<Employee> findAllByOrderByDateDesc();



}
