package com.excel.employedetail.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excel.employedetail.eDTO.ResponseDto;
import com.excel.employedetail.model.Address;
import com.excel.employedetail.model.EmployeeDetails;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class ServiceEmp implements Empinf {
	@Autowired
	ExcelDataRepository ex;
	@Autowired
	 AddressRepo er;
	private static final String EXCEL_EXTENSION = ".xlsx"; // Change to ".xls" for older Excel format

	@Override
	public boolean isValidExcelFile(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();

		return (originalFilename != null && originalFilename.toLowerCase().endsWith(EXCEL_EXTENSION));

	}
	//first create table

//	@Override
//	public EmployeeDetails create(EmployeeDetails list) {
//		// TODO Auto-generated method stub
//		return ex.save(list);
//	}

	
	 
	@Override
	public ResponseEntity<ResponseDto> readExcel(MultipartFile file) {

		List<EmployeeDetails> insertlist = new ArrayList<>();
		List<EmployeeDetails> updatelist = new ArrayList<>();
		List<String> deletelist = new ArrayList<>();
		
		List<EmployeeDetails> list=new ArrayList<>();
		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);
			int rowNumber = 0;
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				Boolean isrowEmpty = checkIfRowIsEmpty(sheet.getRow(1));

				if (isrowEmpty) {

					ResponseDto res = new ResponseDto();

					res.setMessage("data not found");

					return ResponseEntity.status(HttpStatus.OK).body(res);
				}

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellIterator = row.cellIterator();

				EmployeeDetails dto = new EmployeeDetails();

				if (cellIterator.hasNext()) {
					dto.setEmpid(cellIterator.next().toString());
				}
				if (cellIterator.hasNext()) {
					dto.setFirstname(cellIterator.next().toString());
				}
				if (cellIterator.hasNext()) {
					dto.setLastname(cellIterator.next().toString());
				}
				if (cellIterator.hasNext()) {
					dto.setAge((int)cellIterator.next().getNumericCellValue());
				}
				
				if (cellIterator.hasNext()) {
					dto.setEmail(cellIterator.next().toString());
				}
				
				if (cellIterator.hasNext()) {
					dto.setDateOfBirth((Date)cellIterator.next().getDateCellValue());
				}
				if (cellIterator.hasNext()) {
					dto.setGender(cellIterator.next().toString());
				}
				if (cellIterator.hasNext()) {
					dto.setDepartment(cellIterator.next().toString());
				}
				
				if (cellIterator.hasNext()) {
					dto.setJobTitle(cellIterator.next().toString());
				}
				
				if (cellIterator.hasNext()) {
					dto.setSalary(new BigDecimal(cellIterator.next().getNumericCellValue()));
				}
				
				if (cellIterator.hasNext()) {
					dto.setHireDate((Date)cellIterator.next().getDateCellValue());
				}
				
				if (cellIterator.hasNext()) {
					dto.setContactInfo(cellIterator.next().toString());
				}
				
		
				if (cellIterator.hasNext()) {
					dto.setEmployeeStatus(cellIterator.next().toString());
				}
				
				 list.add(dto);

				if ("new".equalsIgnoreCase(dto.getEmployeeStatus())) {
					insertlist.add(dto);

				}
				if ("update".equalsIgnoreCase(dto.getEmployeeStatus())) {
					updatelist.add(dto);
				}
				if ("delete".equalsIgnoreCase(dto.getEmployeeStatus())) {

					deletelist.add(dto.getEmpid());
				}
				

			}
			
			

			insertopration(insertlist);
			updateopration(updatelist);
			deleteopration(deletelist);

			ResponseDto res = new ResponseDto();
			res.setResult(true);
			res.setMessage("file uploaded successful");
			res.setStatuscode(200);
			res.setList(list);
            ex.saveAll(list);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
		} catch (IOException e) {
			// Handle the exception

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	private boolean checkIfRowIsEmpty(Row row) {
		if (row == null) {
			return true;
		}
		if (row.getLastCellNum() <= 0) {
			return true;
		}
		for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
			Cell cell = row.getCell(cellNum);
			if (cell != null && cell.getCellType() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
				return false;
			}
		}
		return true;
	}

	

	
	@Transactional
	private void deleteopration(List<String> deletelist) {
		// TODO Auto-generated method stub
		try {
			ex.deletebyempids(deletelist);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Transactional
	private void updateopration(List<EmployeeDetails> updatelist) {

		try {

			for (EmployeeDetails x : updatelist) {
				System.out.print("empid" + x.getEmpid());

				ex.updation(x.getEmpid(), x.getFirstname(), x.getLastname(),x.getAge(),x.getEmail(),x.getDateOfBirth(),x.getGender(),x.getDepartment(),x.getJobTitle(),x.getSalary(),x.getHireDate(),x.getContactInfo());

			}

		} catch (Exception e) {

			// TODO: handle exception

		}

	}

	private void insertopration(List<EmployeeDetails> insertlist) {
		// TODO Auto-generated method stub

		try {
			ex.saveAll(insertlist);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	


	// update opration

//	@Override
//	public void addEmployee(String empid, String firstname, String lastname, Integer age, String email,
//			Date dateOfBirth, String gender, String department, String jobTitle, BigDecimal salary, Date hireDate,
//			String contactInfo) {
//		ex.updation(empid, firstname, lastname, age, email, dateOfBirth, gender, department, jobTitle, salary, hireDate, contactInfo);
//		
//	}

	

	
	
	
	// Read data By ID
	
	@Override
	public EmployeeDetails getEmployeeByEmpid(String empid) {
		// TODO Auto-generated method stub
		 Optional<EmployeeDetails> employee = ex.findByEmpid(empid);

	        if (employee.isPresent()) {
	        	
	        	
	            return ex.save(employee.get());
	        } else {
	            return null; // Employee not found
	        }
	}

	

		
	//return all data
		@Override
		public List<EmployeeDetails> getAllEmployees() {
			// TODO Auto-generated method stub
			return ex.findAll();
		}

	
	// Delete Table
	@Override
	public boolean deleteEmployeeByEmpid(String empid) {
		Optional<EmployeeDetails> employee = ex.findByEmpid(empid);

        if (employee.isPresent()) {
            ex.delete(employee.get());
            return true;
        } else {
            return false;
        }
	}
	
	
	//update table

	@Override
	public boolean updateEmployeeByEmpid(String empid, EmployeeDetails list) {
		Optional<EmployeeDetails> employee = ex.findByEmpid(empid);

        if (employee.isPresent()) {
        
            ex.save(employee.get());
            return true;
        } else {
            return false;
        }
	}

	
	//create table
	@Override
	public boolean employeeExists(String empid) {
		// TODO Auto-generated method stub
		return ex.existsByEmpid(empid);
	}
	
	@Override
	public EmployeeDetails createEmployee(EmployeeDetails list) {
		// TODO Auto-generated method stub
		return ex.save(list);
	}

	@Override
	public boolean createEmployeeByEmpid(EmployeeDetails list) {
		// TODO Auto-generated method stub
		Optional<EmployeeDetails> employee = ex.findByEmpid(list.getEmpid());

        if (!employee.isPresent()) {
        
            ex.save(list);
            return true;
        } else {
            return false;
        }
	}
	//update by query
@Transactional
	@Override
	public boolean updateEmployeeNamesByEmpid(EmployeeDetails details) {
		// TODO Auto-generated method stub
		
		Optional<EmployeeDetails> employee = ex.findByEmpid(details.getEmpid());

        if (employee.isPresent()) {
        
        	ex.updation(details.getEmpid(),details.getFirstname(), details.getLastname(), details.getAge(), details.getEmail(), details.getDateOfBirth(), details.getGender(), details.getDepartment(), details.getJobTitle(), details.getSalary(), details.getHireDate(), details.getContactInfo());
            return true;
        } else {
            return false;
        }
}

	

	
	//delete by query
@Transactional
public boolean deleteemployeeByEmpide(String empid) {
  
	// TODO: handle exception
   Optional<EmployeeDetails> employee = ex.findByEmpid(empid);

   if (employee.isPresent()) {
	   ex.deleteEmployeeByEmpid(empid);
   return true;
} else {
    return false;
}}
	
    

// update responce
@Override
public ResponseDto response() {
	ResponseDto res = new ResponseDto();
	res.setMessage("recored has been updated");
	res.setStatuscode(200);
	res.setResult(true);
	return res;
}

@Override
public ResponseDto upresponse() {
	// TODO Auto-generated method stub
	ResponseDto res = new ResponseDto();
	res.setMessage("record not exist");
	res.setStatuscode(200);
	res.setResult(true);
	return res;
}
//detete response
@Override
public ResponseDto delresponse() {
	// TODO Auto-generated method stub
	ResponseDto res = new ResponseDto();
		res.setMessage("data has been deleted");
		res.setStatuscode(200);
		res.setResult(true);
		return res;
}

@Override
public ResponseDto deleteresponse() {
	// TODO Auto-generated method stub
	ResponseDto res = new ResponseDto();
		res.setMessage("data not exist");
		res.setStatuscode(200);
		res.setResult(true);
		return res;
}
//create reponce

@Override
public ResponseDto createresponce() {
	// TODO Auto-generated method stub
	ResponseDto res = new ResponseDto();
	res.setMessage("Table has been Created & data Stored successfull");
	res.setStatuscode(200);
	res.setResult(true);
	return res;
}

@Override
public ResponseDto createrespose() {
	// TODO Auto-generated method stub
	ResponseDto res = new ResponseDto();
	res.setMessage("recored is alredy present");
	res.setStatuscode(200);
	res.setResult(true);
	return res;
}



@Override
public boolean employeeExists(Long id) {
	// TODO Auto-generated method stub
	return er.existsById(id);
}

@Override
public Address createAddress(Address list) {
	// TODO Auto-generated method stub
	
	return er.save(list);
}

@Override
public ResponseDto createEmployeeAddress(Address list) {
	// TODO Auto-generated method stub
	
	Optional<Address> employee= er.findById(list.getId());
	if (!employee.isPresent()) {
        er.save(list);
        ResponseDto res = new ResponseDto();
    	res.setMessage("Table has been Created & data Stored successfull");
    	res.setStatuscode(200);
    	res.setResult(true);
        return res ;
    } else {
    	ResponseDto res = new ResponseDto();
    	res.setMessage("recored is alredy present");
    	res.setStatuscode(200);
    	res.setResult(true);
        return res;
    }
}

@Override
public ResponseDto empNotFound() {
	// TODO Auto-generated method stub
	ResponseDto res = new ResponseDto();
	res.setMessage("recored is not present");
	res.setStatuscode(200);
	res.setResult(true);
	return res;
}

@Override
public ResponseDto createAdd(Address list) {
	// TODO Auto-generated method stub
	EmployeeDetails emp=new EmployeeDetails();
	Address add = new  Address();
	add.setId(list.getId());
	add.setCity(list.getCity());
	add.setEmpDetais(list.getEmpDetais());
	emp.getAddresses().add(add);
	ex.save(emp);
	ResponseDto res=new ResponseDto();
	res.setMessage("has been created");
	res.setResult(true);
	res.setStatuscode(200);
	return res;
}

@Override
public void exportDataToFile(String filePath)throws IOException {
	// TODO Auto-generated method stub
	
	
	
	List<EmployeeDetails> data = ex.findAll(); // Fetch your data
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        for (EmployeeDetails entity : data) {
            String formattedLine = formatDataAsPipeSeparated(entity); // Format data
            writer.write(formattedLine);
            writer.newLine();
        }
    }
}

private String formatDataAsPipeSeparated(EmployeeDetails entity) {
    // Format your data as pipe-separated values
	
	
	 StringBuilder formattedData = new StringBuilder();

	    // Format your data as pipe-separated values, handling null values
	    formattedData.append(entity.getId() != null ? entity.getId() : "").append("|");
	    formattedData.append(entity.getEmpid() != null ? entity.getEmpid() : "").append("|");
	    formattedData.append(entity.getFirstname() != null ? entity.getFirstname() : "").append("|");
	    formattedData.append(entity.getLastname() != null ? entity.getLastname() : "").append("|");
	    formattedData.append(entity.getAge() != null ? entity.getAge() : "").append("|");
	    formattedData.append(entity.getEmail() != null ? entity.getEmail() : "").append("|");
	    formattedData.append(entity.getPassword() != null ? entity.getPassword() : "").append("|");
	    formattedData.append(entity.getDateOfBirth() != null ? entity.getDateOfBirth() : "").append("|");
	    formattedData.append(entity.getGender() != null ? entity.getGender() : "").append("|");
	    formattedData.append(entity.getDepartment() != null ? entity.getDepartment() : "").append("|");
	    formattedData.append(entity.getJobTitle() != null ? entity.getJobTitle() : "").append("|");
	    formattedData.append(entity.getSalary() != null ? entity.getSalary() : "").append("|");
	    formattedData.append(entity.getHireDate() != null ? entity.getHireDate() : "").append("|");
	    formattedData.append(entity.getContactInfo() != null ? entity.getContactInfo() : "");

	    return formattedData.toString();
//    return entity.getId() + "|" + entity.getEmpid() + "|" + entity.getFirstname()+ "|" + entity.getLastname() + "|" + entity.getAge()
//    + "|" + entity.getEmail() + "|" + entity.getPassword()+ "|" + entity.getDateOfBirth() + "|" + entity.getGender()+ "|" + entity.getDepartment() + "|" + entity.getJobTitle()+ "|" + entity.getSalary() + "|" + entity.getHireDate()+ "|" + entity.getContactInfo() ;
}




@Override
public Page<EmployeeDetails> findPaginated(int pageno, int pageSize) {
	Pageable pageable = PageRequest.of(pageno - 1, pageSize);

	    return this.ex.findAll(pageable);
}

@Override
public Page<EmployeeDetails> findPaginated(int pageno, String department, int pageSize) {
	// TODO Auto-generated method stub
	Pageable pageable = PageRequest.of(pageno - 1, pageSize);
	return ex.findAllBydepartment(department,pageable);
}

@Override
public void generateExcel(HttpServletResponse response) {
	// TODO Auto-generated method stub
	try {
        List<EmployeeDetails> details = ex.findAll();



        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("employee_details");
        XSSFRow headerRow = sheet.createRow(0);



        // Create a CellStyle for the header row
        XSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);



      

     // Define the header labels
        List<String> headerLabels = Arrays.asList(
            "id", "employeeId", "firstName", "lastName","Age", "emailAddress",
            "dateOfBirth", "gender", "department", "jobTitle", "salary",
            "hireDate", "otherContactInformation", "password"
        );

        // Create the header cells and apply the headerCellStyle
        for (int i = 0; i < headerLabels.size(); i++) {
            XSSFCell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headerLabels.get(i));
            headerCell.setCellStyle(headerCellStyle);
        }





        int dataRowIndex = 1;

     // Date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");



        for (EmployeeDetails emp : details) {
            XSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(emp.getId()!=null? emp.getId():0);
            dataRow.createCell(1).setCellValue(emp.getEmpid() != null ? emp.getEmpid() : "");
            dataRow.createCell(2).setCellValue(emp.getFirstname() != null ? emp.getFirstname() : "");
            dataRow.createCell(3).setCellValue(emp.getLastname() != null ? emp.getLastname() : "");
            dataRow.createCell(4).setCellValue(emp.getAge()!=null? emp.getAge():0);
            dataRow.createCell(5).setCellValue(emp.getEmail() != null ? emp.getEmail() : "");
            if (emp.getDateOfBirth() != null) {
                dataRow.createCell(6).setCellValue(dateFormat.format(emp.getDateOfBirth()));
            } else {
                dataRow.createCell(6).setCellValue("");
            }
            dataRow.createCell(7).setCellValue(emp.getGender() != null ? emp.getGender() : "");
            dataRow.createCell(8).setCellValue(emp.getDepartment() != null ? emp.getDepartment() : "");
            dataRow.createCell(9).setCellValue(emp.getJobTitle() != null ? emp.getJobTitle() : "");
            dataRow.createCell(10).setCellValue(emp.getSalary() != null ? emp.getSalary().doubleValue() : 0.0);

            if (emp.getHireDate() != null) {
                dataRow.createCell(11).setCellValue(dateFormat.format(emp.getHireDate()));
            } else {
                dataRow.createCell(11).setCellValue("");
            }
            dataRow.createCell(12).setCellValue(emp.getContactInfo() != null ? emp.getContactInfo() : "");
           
            dataRow.createCell(13).setCellValue(emp.getPassword() != null ? emp.getPassword() : "");
            dataRowIndex++;
        }



        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=employee_details.xlsx");



        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
	



public Map<String, List<Long>> getIdsSortedByDepartment() {
    List<EmployeeDetails> employees = ex.findAll();

    // Create a HashMap to store the IDs sorted by department
    Map<String, List<Long>> sortedIds = new HashMap<>();

    for (EmployeeDetails employee : employees) {
        String department = employee.getDepartment();
        Long id = employee.getId();

        // Add the ID to the department's list, creating the list if it doesn't exist
        sortedIds.computeIfAbsent(department, key -> new ArrayList<>()).add(id);
    }

    return sortedIds;
}

@Override
public List<EmployeeDetails> getIdsSortedDepartment() {
	// TODO Auto-generated method stub
    List<EmployeeDetails> employees = ex.findAll(); 
	return employees;
}

	

}






//@Override
//public EmployeeDetails getEmployeeByEmpid(String empid) {
//	// TODO Auto-generated method stub
//	Optional<EmployeeDetails> employee = ex.findByEmpid(empid);
////	EmployeeDetails list=ex.save(employee.get());
//	return employee.orElse(null);
//}

// create table
//@Override
//public boolean doesEmployeeExist(String empid) {
//	// TODO Auto-generated method stub
//	return ex.existsByEmpid(empid);
//}
//
//@Override
//public void create(EmployeeDetails list) {
//ex.save(list);
//	
//}

//@Override
//public EmployeeDetails getid(Long id) {
////	// TODO Auto-generated method stub
//Optional<EmployeeDetails> optionalEmployee = ex.findById(id);
//    return optionalEmployee.orElse(null);
//}
//

////create table
//@Override
//public boolean createTable(String empid) {
//	Optional<EmployeeDetails> employee =  ex.findByEmpid(empid);
//
//    if (!employee.isPresent()) {
//        ex.save(employee.get());
//        return true;
//    } else {
//        return false;
//    }
//}
//


//@Override
//public boolean doesEmployeeExist(String empid) {
//	// TODO Auto-generated method stub
//	return false;
//}
//
//@Override
//public void create(EmployeeDetails list) {
//	// TODO Auto-generated method stub
//	
//}


//@Override
//public void updatebyid(String empid, EmployeeDetails list) {
//	// TODO Auto-generated method stub
//	EmployeeDetails entitiy=ex.updation(empid, empid, empid, null, empid, null, empid, empid, empid, null, null, empid);
//	
//}
//
//@Override
//public void updatebyid( Long id,EmployeeDetails list) {
//EmployeeDetails	entitiy=ex.findOne(id);
//ex.updation(EXCEL_EXTENSION, EXCEL_EXTENSION, EXCEL_EXTENSION);
//
//
//}
//
//

//@Override
//public EmployeeDetails getEmployeeById(Long id) {
//	// TODO Auto-generated method stub
//	return ex.findById(id);
//}
//


//@Override
//public EmployeeDetails getEmployeeById(Long id) {
//    Optional<EmployeeDetails> optionalEmployee = ex.findById(id);
//    return optionalEmployee.orElse(null); // Return null if not found
//}

//@Override
//public List<EmployeeDetails> getAllEmployeeDetails() {
//// TODO Auto-generated method stub
//return ex.findAll();
//}

