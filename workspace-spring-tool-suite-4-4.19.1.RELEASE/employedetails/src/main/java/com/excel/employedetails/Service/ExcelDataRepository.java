package com.excel.employedetails.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.excel.employedetails.model.EmployeeDetails;

import jakarta.transaction.Transactional;

@Repository
public interface ExcelDataRepository extends JpaRepository<EmployeeDetails, Long> {

@Modifying
@Transactional
@Query("delete from EmployeeDetails where empid IN(:deletelist)") 
void deletebyempids(@Param("deletelist")java.util.List<String> deletelist);

@Modifying
@Transactional
@Query("update EmployeeDetails SET firstname=:firstname,lastname=:lastname,mobile=:mobile,age=:age,designation=:designation where empid=:empid" )
void updation(@Param("empid")String empid,@Param("firstname")String fristname,@Param("lastname")String lastname,@Param("age") Integer age,@Param("mobile")String mobile, @Param("designation")String designation);


//@Modifying
//@Transactional
//@Query("UPDATE  EmployeeDetails  SET firstname=:fname, lastName=:lname,mobile=:mobile,age=:age,designation=:desi WHERE empid=:eid ")
//void uoperation( @Param("fname")String firstname,@Param("lname")String
//		 lastname,@Param("mobile")String mobile,@Param("age")Integer
//	 age,@Param("desi")String designation,@Param("eid")String empid);
//@Modifying
//@Transactional
//@Query("UPDATE  EmployeeDetails  SET firstName=:fname, lastName=:lname,mobile=:mobile,age=:age,designation=:desi WHERE empid=:eid ")
//void updateExcel(@Param("fname")String firstName,@Param("lname")String
//		 lastName,@Param("mobile")String mobile,@Param("age")int
//		 age,@Param("desi")String designation,@Param("eid")String empid);
//

 
  
 

}
