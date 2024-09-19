package com.excel.employedetail.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.excel.employedetail.model.Address;
import com.excel.employedetail.model.EmployeeDetails;

import io.netty.handler.codec.http.HttpHeaders.Values;
import jakarta.transaction.Transactional;

@Repository
public interface ExcelDataRepository extends JpaRepository<EmployeeDetails, Long> {

	@Modifying
	@Transactional
	@Query("delete from EmployeeDetails where empid IN(:deletelist)")
	void deletebyempids(java.util.List<String> deletelist);

	@Modifying
	@Transactional
	@Query("UPDATE EmployeeDetails e SET e.firstname=:firstname,e.lastname=:lastname,e.age=:age,e.email=:email,e.dateOfBirth=:dateOfBirth,e.gender=:gender,e.department=:department,e.jobTitle=:jobTitle,e.salary=:salary,e.hireDate=:hireDate,e.contactInfo=:contactInfo where e.empid=:empid")
	void updation(@Param("empid") String empid, @Param("firstname") String firstname,
			@Param("lastname") String lastname, @Param("age") Integer age, @Param("email") String email,
			@Param("dateOfBirth") Date dateOfBirth, @Param("gender") String gender,
			@Param("department") String department, @Param("jobTitle") String jobTitle,
			@Param("salary") BigDecimal salary, @Param("hireDate") Date hireDate,
			@Param("contactInfo") String contactInfo);

	@Modifying
	@Query("DELETE FROM EmployeeDetails e WHERE e.empid = :empid")
	void deleteEmployeeByEmpid(@Param("empid") String empid);

	void save(List<EmployeeDetails> list);

//boolean existsByEmpid(String empid);

	Optional<EmployeeDetails> findByEmpid(String empid);
	

	void save(Optional<EmployeeDetails> employee);

	boolean existsByEmpid(String empid);

	Page<EmployeeDetails> findAllBydepartment(String department, Pageable pageable);

	List<EmployeeDetails> findAllBydepartment(String department);
//    @Procedure(procedureName = "insert_bulk_employee_details", name = "dbo.insert_bulk_employee_details")
//	void insertEmployeeDetails(  List<EmployeeDetails> values);
    @Procedure(procedureName = "insert_bulk_employeed_details", name = "dbo.insert_bulk_employeed_details")
	void insertBulkEmployeeDetails(String formattData);

	//void insertBulkEmployeeDetails(String formattedData);

   // @Procedure(name = "insert_employee_detailSS")
    @Procedure(procedureName = "insert_bulk_employee_detailSS", name = "dbo.insert_bulk_employee_detailSS")
    @Query(nativeQuery = true, value = "EXEC insert_bulk_employee_detailSS  :empid, :firstname, :lastname, :age, :email,:password, :dateOfBirth, :gender, :department, :jobTitle, :salary, :hireDate, :contactInfo")
    void insertEmployeeDetails(
    		  
            @Param("empid") String empid,
            @Param("firstname") String firstname,
            @Param("lastname") String lastname, @Param("age") Integer age, @Param("email") String email,@Param("password") String password,
			@Param("dateOfBirth") Date dateOfBirth, @Param("gender") String gender,
			@Param("department") String department, @Param("jobTitle") String jobTitle,
			@Param("salary") BigDecimal salary, @Param("hireDate") Date hireDate,
			@Param("contactInfo") String contactInfo
            // Add other parameters as needed
    );

    
    
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC insert_bulk_employee :empid, :firstname, :lastname, :age, :email,:password, :dateOfBirth, :gender, :department, :jobTitle, :salary, :hireDate, :contactInfo")
	void insertBulkEmployeeDetails(String empid, String firstname, String lastname, Integer age, String email,String password,
			Date dateOfBirth, String gender, String department, String jobTitle, BigDecimal salary, Date hireDate,
			String contactInfo);

	
    
    // insertbulklist
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC insert_bulk_employeelist @employeeDetails = :employeeDetails")
    void insertBulkEmployeelist(@Param ("employeeDetails") List<EmployeeDetails> employeeDetails);

}
//@Repository
//public interface AdDataRepository extends JpaRepository<Address, Long> {
//	
//}



//Optional<EmployeeDetails> findAll(EmployeeDetails list);

//Optional<EmployeeDetails> saveAll(EmployeeDetails list);

//Optional<EmployeeDetails> findAll(EmployeeDetails list);

//Optional<EmployeeDetails> saveAll(EmployeeDetails list);

//Optional<EmployeeDetails> findAll(String empid);

//Optional<EmployeeDetails> save(String empid);

//Optional<EmployeeDetails> findAll(EmployeeDetails list);

//EmployeeDetails findOne(Long id);

//void updation(String empid, String firstname, String lastname, String email, Date dateOfBirth, String gender,
//		String department, String jobTitle, BigDecimal salary, Date hireDate, EmployeeDetails manager,
//		String contactInfo, String employeeStatus);

//void updation( String empid, String fristname,
//		 String lastname);
//

//ResponseEntity<ResponseDto> getOne(String empid);
//EmployeeDetails getOne(String empid);