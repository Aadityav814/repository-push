package com.excel.employedetail.Service;


import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import com.excel.employedetail.eDTO.ResponseDto;
import com.excel.employedetail.model.Address;
import com.excel.employedetail.model.EmployeeDetails;

import jakarta.servlet.http.HttpServletResponse;



public interface Empinf {

	boolean isValidExcelFile(MultipartFile file);

	ResponseEntity<ResponseDto> readExcel(MultipartFile file);
	

	List<EmployeeDetails> getAllEmployees();

	

	

//	void addEmployee(String empid, String firstname, String lastname, Integer age, String email, Date dateOfBirth,
//			String gender, String department, String jobTitle, BigDecimal salary, Date hireDate, String contactInfo);

	

	boolean deleteEmployeeByEmpid(String empid);

	boolean updateEmployeeByEmpid(String empid,EmployeeDetails list);

	boolean employeeExists(String empid);

	 

	EmployeeDetails getEmployeeByEmpid(String empid);

	EmployeeDetails createEmployee(EmployeeDetails list);

	boolean createEmployeeByEmpid(EmployeeDetails list);

//	void updateEmployeeNamesByEmpid(String empid, String firstname, String lastname, Integer age, String email,
//			Date dateOfBirth, String gender, String department, String jobTitle, BigDecimal salary, Date hireDate);

	
	boolean updateEmployeeNamesByEmpid(EmployeeDetails details);
	//void deleteemployeeByEmpid(String empid);
    boolean deleteemployeeByEmpide(String empid);
	
	ResponseDto response();

	ResponseDto upresponse();
	ResponseDto delresponse();

	ResponseDto deleteresponse();

	ResponseDto createresponce();

	ResponseDto createrespose();

	

	boolean employeeExists(Long id);

	Address createAddress(Address list);

	ResponseDto createEmployeeAddress(Address list);

	ResponseDto empNotFound();

	ResponseDto createAdd(Address list);

	void exportDataToFile(String filePath) throws IOException;

	Page<EmployeeDetails> findPaginated(int pageno, int pageSize);

	Page<EmployeeDetails> findPaginated(int pageno, String department, int pageSize);

	void generateExcel(HttpServletResponse response);

	Map<String, List<Long>> getIdsSortedByDepartment();

	Map<String, String> getIdSortedByDepartment();

	List<EmployeeDetails> readValuesFromFile(MultipartFile file)throws IOException;
//Store procedure 
	//void bulkInsertEmployees(List<EmployeeDetails> values);

	
	
	
	
	//void insertbulklist(List<EmployeeDetails> values);
	
	
//String Formating Store procedure 
	//void insertBulkDataUsingStoredProcedure(List<EmployeeDetails> values);
	
	
//@param Store proceture
	void insertBulkDataUsingStoredProcedur(List<EmployeeDetails> values);



	

	
	

	

	//boolean employeeExists(Integer id);

//	ResponseDto createEmployeeAddress(EmployeeDetails list);



	



	



	
	
	//ResponseEntity<ResponseDto> getEmployeeDetailsById(Long id);

		//EmployeeDetails create(EmployeeDetails emp);

//		boolean doesEmployeeExist( String empid);
	//void create(EmployeeDetails list);

		//EmployeeDetails getid(long parseLong);
	
	
	

	//boolean createTable(String string);

	



	//void updatebyid(String empid,EmployeeDetails list );

	

	

	

//	EmployeeDetails getEmployeeById(Long id);
//
//	List<EmployeeDetails> getAllEmployeeDetails();

	

//EmployeeDetails createtable(EmployeeDetails emp);
	
//	EmployeeDetails getEmployeeDetailsById(Long id);
//

//
//
//	

	

	

	
	//EmployeeDetails getcid(Long id);

	// String isValidExcelFile(MultipartFile file);

}