package com.excel.empdetails.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.excel.empdetails.eDTO.MasterResDto;
import com.excel.empdetails.model.Employee;
import com.excel.empdetails.service.Services;




@RestController
public class Controller {
	
	@Autowired
	private Services inf;
	
	
	
	 @PostMapping("/create")
		public ResponseEntity<MasterResDto> uploadExcel(MultipartFile file) {
			
			if (!inf.isValidExcelFile(file)) {

				MasterResDto res = new MasterResDto();
				res.setMessage("uploade valid file");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

			}

			return inf.readExcel(file);

		}
	
	@PostMapping("/created")
	public ResponseEntity<MasterResDto> CreateTable(@RequestBody Employee list) {
		boolean create = inf.createEmployeeByEmail(list);

		if (create) {

			MasterResDto res = inf.createresponce();
			

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		} else {
			MasterResDto res = inf.createrespose();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		}
	}

	
	@GetMapping("/getallbyfname/{fname}")
	public ResponseEntity<?> getAllbyfname(@PathVariable ("fname")String firstName) {
		List<Employee> employees = inf.getAllfirstName(firstName);
		
		if (employees != null) {
			return ResponseEntity.ok(employees);
		} else {
			MasterResDto res= inf.empNotFound();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
		
	}
	
	@GetMapping("/getallbylname/{lname}")
	public ResponseEntity<?> getAllbylname(@PathVariable ("lname")String lastName) {
		List<Employee> employees = inf.getAlllastName(lastName);
		
		if (employees != null) {
			return ResponseEntity.ok(employees);
		} else {
			MasterResDto res= inf.empNotFound();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
		
	}

	@GetMapping("/getallbyemail/{email}")
	public ResponseEntity<?> getAllbyemail(@PathVariable ("email")String email) {
		List<Employee> employees = inf.getAllbyemail(email);
		
		if (employees != null) {
			return ResponseEntity.ok(employees);
		} else {
			MasterResDto res= inf.empNotFound();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
		
	}
	
	@GetMapping("/getallbyDate/{date}")
	public ResponseEntity<?> getAllbydate(@PathVariable ("date")String date) {
		
		List<Employee> employees = inf.getAllbydate(date);
		
		if (employees != null) {
			return ResponseEntity.ok(employees);
		} else {
			MasterResDto res= inf.empNotFound();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
		
	}
	
	
	 @GetMapping("/sortedByInsertDate")
	    public List<Employee> getAllSortedByInsertDate() {
	        return inf.getAllSortedByInsertDate();
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 // read data from csv file and return that as json format 
	  @GetMapping("/convert")
	    public ResponseEntity<String> convertCsvToJson() throws IOException {
	    	
	    	
	    	//String file = "C:\\Users\\aadit\\OneDrive\\Desktop\\my sql practice/data_store.txt";
		  String file = "C:\\Users\\nqe00567\\Downloads/states.csv";
		  String file2 = "C:\\Users\\nqe00567\\Downloads/states.csv";
		 // C:\Users\nqe00567\Downloads
	        String json = inf.convertCsvToJsons(file);
			return ResponseEntity.ok(json);
	    }
	    
	 
	    @PostMapping("/convert")
	    public ResponseEntity<String> convertCsvToJson(@RequestParam("file") MultipartFile file) throws IOException {
	    	
	    	
	        String json = inf.convertCsvToJson(file);
			return ResponseEntity.ok(json);
	    }
	    
	    
	    
	    @GetMapping("/data")
	    public ResponseEntity<String> getJsonData() {
	        String jsonData = inf.getJsonData();
	        if (jsonData == null) {
	            return ResponseEntity.status(404).body("No data available. Please convert a CSV file first.");
	        }
	        return ResponseEntity.ok(jsonData);
	    }
	    
	    
	    
	    
	    
	    @GetMapping("/convertnotpass")
	    public ResponseEntity<String> convertCsvToJsonwithoutpath() throws IOException {
	    	
	    	
	    
	    	 try {
	             String json = inf.convertCsvToJsons();
	             if (json == null || json.isEmpty()) {
	                 return ResponseEntity.badRequest().body("Conversion failed: No data found.");
	             } else {
	                 return ResponseEntity.ok(json);
	             }
	         } catch (IllegalArgumentException e) {
	             return ResponseEntity.status(400).body("Invalid file: " + e.getMessage());
	         } catch (IOException e) {
	             return ResponseEntity.status(500).body("Failed to convert CSV to JSON: " + e.getMessage());
	         }
	     }
			
	    }
	     
	    
	    
	    
