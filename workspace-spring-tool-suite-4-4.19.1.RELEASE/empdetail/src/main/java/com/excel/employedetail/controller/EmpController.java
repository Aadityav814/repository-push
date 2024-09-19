package com.excel.employedetail.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excel.employedetail.Service.Empinf;
import com.excel.employedetail.eDTO.ResponseDto;
import com.excel.employedetail.model.Address;
import com.excel.employedetail.model.EmployeeDetails;

import jakarta.servlet.http.HttpServletResponse;






@Controller
@RequestMapping("/excel")
public class EmpController {

	@Autowired
	private Empinf inf;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> uploadExcel(MultipartFile file) {
		// if (!inf.isValidExcelFile(file)) {
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		// }
		if (!inf.isValidExcelFile(file)) {

			ResponseDto res = new ResponseDto();
			res.setMessage("uploade valid file");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

		}

		return inf.readExcel(file);

	}

//	@PostMapping("/createtable")
//	public ResponseEntity<ResponseDto> create( @RequestBody EmployeeDetails list,@RequestBody String empid) {
//
//		if (inf.doesEmployeeExist(empid)) {
//		ResponseDto res = new ResponseDto();
//		res.setMessage("data has been deleted");
//		res.setStatuscode(200);
//		res.setResult(true);
//		return ResponseEntity.status(HttpStatus.CREATED).body(res);
//		} else {
//
//		
//		 inf.create(list);
//
//		ResponseDto res = new ResponseDto();
//		res.setMessage("successfull");
//		res.setStatuscode(200);
//		res.setResult(true);
//
//		return ResponseEntity.status(HttpStatus.CREATED).body(res);
//		}
//	}

	// create Table

	@PostMapping("/createtable")
	public ResponseEntity<ResponseDto> create(@RequestBody EmployeeDetails list) {

		if (inf.employeeExists(list.getEmpid())) {
			// Employee exists, return the existing data
			ResponseDto res = inf.createrespose();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);

		} else {
			// Employee does not exist, create a new record
			EmployeeDetails created = inf.createEmployee(list);
			ResponseDto res = inf.createresponce();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		}
	}

	// refer this
	@GetMapping("/readbyEmpid/{empid}")
	public ResponseEntity<?> getEmployeeByEmpid(@PathVariable String empid) {
		EmployeeDetails employeeDTO = inf.getEmployeeByEmpid(empid);

		if (employeeDTO != null) {
			return ResponseEntity.ok(employeeDTO);
		} else {
			ResponseDto res= inf.empNotFound();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
	}

	@GetMapping("/getallemp")
	public ResponseEntity<List<EmployeeDetails>> getAllEmployees() {
		List<EmployeeDetails> employees = inf.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	 @GetMapping("/sortedid")
	    public List<EmployeeDetails> getSortedEmployeeIdsDepartment() {
	     List<EmployeeDetails> emp= inf.getIdsSortedDepartment();
	       return emp;
	
} 

	// delete operation

	@GetMapping("/delete/{empid}")
	public ResponseEntity<ResponseDto> deletebyid(@PathVariable String empid) {
		boolean deleted = inf.deleteEmployeeByEmpid(empid);

		if (deleted) {
			ResponseDto res = inf.delresponse();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		} else {
			ResponseDto res = inf.deleteresponse();
			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		}

	}

	// delete by using query
	@GetMapping("/deleted/{empid}")
	public ResponseEntity<ResponseDto> deleteEmployeeByEmpid(@PathVariable String empid) {

		boolean delete = inf.deleteemployeeByEmpide(empid);
		if (delete) {
			ResponseDto res = inf.delresponse();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		} else {
			ResponseDto res = inf.deleteresponse();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		}

	}

	@PostMapping("/update/{empid}")
	public ResponseEntity<ResponseDto> updatebyide(@PathVariable String empid, @RequestBody EmployeeDetails list) {
		boolean update = inf.updateEmployeeByEmpid(empid, list);

		if (update) {
			ResponseDto res = inf.response();
			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		} else {
			ResponseDto res = inf.upresponse();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		}
	}

	// update operation query
	@PostMapping("/updated")
	public ResponseEntity<ResponseDto> updateEmployeeNames(@RequestBody EmployeeDetails details) {

		boolean update = inf.updateEmployeeNamesByEmpid(details);
		if (update) {
			ResponseDto res = inf.response();
			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		} else {
			ResponseDto res = inf.upresponse();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		}

	}


	// refer this
	@PostMapping("/created")
	public ResponseEntity<ResponseDto> updatebyid(@RequestBody EmployeeDetails list) {
		boolean create = inf.createEmployeeByEmpid(list);

		if (create) {

			ResponseDto res = inf.createresponce();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		} else {
			ResponseDto res = inf.createrespose();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		}
	}





@PostMapping("/createAddressDetail")
public ResponseEntity<ResponseDto> create(@RequestBody Address list) {
   
	if (inf.employeeExists(list.getId())) {

		// Employee exists, return the existing data

		ResponseDto res = inf.createrespose();



		return ResponseEntity.status(HttpStatus.CREATED).body(res);



	} else {

		// Employee does not exist, create a new record

		Address created = inf.createAddress(list);

		ResponseDto res = inf.createresponce();



		return ResponseEntity.status(HttpStatus.CREATED).body(res);

	}
}

//@PostMapping("/update/{empid}")
//public ResponseEntity<ResponseDto> updatebyid(@RequestBody String empid,
//
//@RequestBody String firstname,
//@RequestBody String lastname,
//@RequestBody Integer age,
//@RequestBody String email,
//
//@RequestBody Date dateOfBirth,
//
//@RequestBody String gender,
//
//@RequestBody String department,
//
//@RequestBody String jobTitle,
//
//@RequestBody BigDecimal salary,
//
//@RequestBody Date hireDate,
//
//@RequestBody String contactInfo)
//{
//
//         inf.addEmployee( empid,firstname, lastname, age,email,dateOfBirth,gender,department,
//          jobTitle, salary,hireDate, contactInfo);
//            ResponseDto res = new ResponseDto();
//    		res.setMessage("data has been deleted");
//    		res.setStatuscode(200);
//    		res.setResult(true);
//
//    		return ResponseEntity.status(HttpStatus.CREATED).body(res);
//        }

//if (inf.doesEmployeeExist(empid)) {
//ResponseDto res = new ResponseDto();
//res.setMessage("data has been deleted");
//res.setStatuscode(200);
//res.setResult(true);
//return ResponseEntity.status(HttpStatus.CREATED).body(res);
//} else {

//
//@GetMapping("/readExcelByEmpId/")
//public ResponseEntity<EmployeeDetails> readExcelByEmpId(@RequestParam String empId) {
//    EmployeeDetails employee = inf.getEmployeeByEmpId(empId);
//    if (employee != null) {
//        return ResponseEntity.ok(employee);
//    } else {
//        return ResponseEntity.notFound().build();
//    }
//}
//}
//
//
//

// @GetMapping("/getempbyempid/{id}")
//    public ResponseEntity<EmployeeDetails> getEmployeeById(@PathVariable Long id) {
//        EmployeeDetails employee = inf.getEmployeeById(id);
//        if (employee != null) {
//            return ResponseEntity.ok(employee);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//@GetMapping("/getempbyempid/{id}")
//public ResponseEntity<EmployeeDetails> getEmployeeById(@PathVariable Long id){
//	return inf.get
//}
//
//@GetMapping("/getallemp")
//public ResponseEntity<List<EmployeeDetails>> getAllEmployeeDetails() {
//    List<EmployeeDetails> employees = inf.getAllEmployeeDetails();
//    return ResponseEntity.ok(employees);}
//

//@GetMapping("/getempid/id")
//public EmployeeDetails getcid(@PathVariable Long id) {
//	return this.inf.getcid(id);
//
//}

@PostMapping("/createdAddress")
public ResponseEntity<ResponseDto> createAddress(@RequestBody Address list) {
	ResponseDto create = inf.createEmployeeAddress(list);

	return ResponseEntity.status(HttpStatus.CREATED).body(create);
}



@PostMapping("/createdAddressEmp")
public ResponseEntity<ResponseDto> createAdd(@RequestBody Address list) {
	ResponseDto create = inf.createAdd(list);

	return ResponseEntity.status(HttpStatus.CREATED).body(create);
}

// web application form
@GetMapping("/home")
public String  home(Model model) {
	System.out.println("this is my home page ");
	model.addAttribute("formData",new EmployeeDetails());
	
	return "home";
}
	@PostMapping("/prosess")
	public String prosessData(@Valid @ModelAttribute ("formData")EmployeeDetails formData,BindingResult result) {
		
		
//		if(result.hasErrors()) {
//			
//			System.out.println(result);
//			return "home";
//		
//		}
		
		boolean create = inf.createEmployeeByEmpid(formData);

		if (create) {

			

			System.out.println(formData.getEmail());
			return "success";
		} else {
			System.out.println(" is alredy present");

			return "page";
		}
		
	}
	
	@GetMapping("/page")
	public String  page(Model model) {
		System.out.println("this is my home page ");
		
		
		return "page";
	}
	
	
	
	// pipe separated  program
	 @GetMapping("/exportData")
	    public ResponseEntity<Resource> exportData() throws IOException {
	        String filePath = "C:\\Users\\aadit\\OneDrive\\Desktop\\my sql practice/data_store.txt";
	        inf.exportDataToFile(filePath);

	        // Create a Resource and ResponseEntity for file download
	        Resource resource = new FileSystemResource(filePath);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data_store.txt");
	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(resource.getFile().length())
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .body(resource);
	    }
	 
	 
	 
	 
	 
	 
	
	 
	 //web application Pagination using Spring Data JPA fetch
	 @GetMapping("/")
public String home2(Model m) {

			//return findPaginated(0, m);

			return listItems(1, m);

		}
	 @GetMapping("/items/{pageno}")
public String listItems(@PathVariable int pageno , Model model) {

			int pageSize = 5; // Number of items per page

			Page<EmployeeDetails> itemPage = inf.findPaginated(pageno, pageSize);

			

			model.addAttribute("currentPage", pageno);

			model.addAttribute("totalPages", itemPage.getTotalPages());

			model.addAttribute("totalItems", itemPage.getTotalElements());

			model.addAttribute("items", itemPage.getContent());

			return "items";

		}
	 
	 @GetMapping("/item1/{pageno}")
	 public String listItems(@PathVariable int pageno ,@RequestParam(name="department",required = false)String department, Model model) {

	 			int pageSize = 5; // Number of items per page

	 			Page<EmployeeDetails> itemPage = inf.findPaginated(pageno, department,pageSize);

	 			

	 			model.addAttribute("currentPage", pageno);

	 			model.addAttribute("totalPages", itemPage.getTotalPages());

	 			model.addAttribute("totalItems", itemPage.getTotalElements());

	 			model.addAttribute("items", itemPage.getContent());
	 			model.addAttribute(" department",department);

	 			return "item1";

	 		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
//	 @GetMapping("/item1/{pageno}")
//	 public String listItems(@PathVariable int pageno ,@RequestParam(name="department",required = false)String department, Model model) {
//
//	 			int pageSize = 5; // Number of items per page
//
//	 			Page<EmployeeDetails> itemPage = inf.findPaginated(pageno, department,pageSize);
//
//	 			
//
//	 			model.addAttribute("currentPage", pageno);
//
//	 			model.addAttribute("totalPages", itemPage.getTotalPages());
//
//	 			model.addAttribute("totalItems", itemPage.getTotalElements());
//
//	 			model.addAttribute("items", itemPage.getContent());
//
//	 			return "items";
//
//	 		}
	 
	 
	 
	

	 @GetMapping("/excelExport")

	 public void generateExcelReport(HttpServletResponse response) {

	     try {

	         response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

	         String headerKey = "Content-Disposition";

	         String headerValue = "attachment;filename=employee_details.xlsx";

	         response.setHeader(headerKey, headerValue);


	         inf.generateExcel(response);

	     } catch (Exception e) {

	         e.printStackTrace();

	     }
	     
	    
	 }
	 @GetMapping("/sorted-ids")
	    public Map<String, List<Long>> getSortedEmployeeIdsByDepartment() {
	        return inf.getIdsSortedByDepartment();
	
}
	
}



