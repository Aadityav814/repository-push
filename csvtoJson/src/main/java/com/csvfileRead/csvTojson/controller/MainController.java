package com.csvfileRead.csvTojson.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.csvfileRead.csvTojson.model.State;
import com.csvfileRead.csvTojson.resDTO.ResDTO;
import com.csvfileRead.csvTojson.service.MainService;

@RestController
public class MainController {
	
	
	

	@Autowired
	private MainService inf;
	
	
	@Autowired
	ResourceLoader resourceLoader;
	 
	
	
	public static final  Logger logger= LoggerFactory.getLogger(MainController.class);
	 
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
	    
	    
	    
	    
	    /*
	     * convert csv to json  read path from resorce  
	     * */
	    @GetMapping("/convertnotpas")
	    public ResponseEntity<String> convertCsvToJsonwithoutpath() throws IOException {
	    	// Resource resource = resourceLoader.getResource("classpath:states.csv");
	    	
	    
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
	    
	    
	   
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    /**
	     *Handles HTTP GET requests to "/convertReadfromJson" endpoint  Read CSV file from Resources .
	     * 
	     * @param parse  fileName("demo.csv") read respective CSV file. 
	     * @return a ResponseEntity containing CSV file information return in  JSON format
	     */
	    @GetMapping("/convertReadfromJson")
	    public ResponseEntity<String> convertCsvToJson(@RequestParam("fileName") String fileName) throws IOException {
	       // String json = inf.convertCsvToJsonsReadFromResource(fileName);
	       // return ResponseEntity.ok(json);
	        
	        
	        try {
	             String json = inf.convertCsvToJsonsReadFromResource(fileName);
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
	    
	    
	    @GetMapping("/data/abbreviation")
	    public ResponseEntity<String> getJsonDataByAbbreviation(@RequestParam("abbreviation") String abbreviation)throws IOException {
	        String jsonData = inf.getJsonDataByAbbreviation(abbreviation);
	        if (jsonData == null) {
	            return ResponseEntity.status(404).body("No data available for the given abbreviation.");
	        }
	        return ResponseEntity.ok(jsonData);
	    }
	   
	    
	    /**
	     *Handles HTTP GET requests to "/data/key&value" endpoint  get data base on key and value  .
	     * 
	     * @param parse  key & value base on key and value get information in JSON.
	     * @return a ResponseEntity containing CSV file information return in  JSON format
	     */
	    @GetMapping("/data/key&value")
	    public ResponseEntity<String> getJsonDataByAbbreviation(@RequestParam("key") String key, @RequestParam("value") String value)throws IOException {
	        String jsonData = inf.getJsonDataByAbbreviation(key,value);
	        if (jsonData == null) {
	            return ResponseEntity.status(404).body("No data available for the given abbreviation.");
	        }
	        return ResponseEntity.ok(jsonData);
	    }
	    
	    
	    
	    
	    
	    @PostMapping("/data/filterAll")
	    public ResponseEntity<String> getALLJsonDataByDynamicKeys(@RequestBody Map<String, String> keyValues) throws IOException {
	        String jsonData = inf.getALLJsonDataByDynamicKeys(keyValues);
	        if (jsonData == null) {
	            return ResponseEntity.status(404).body("No data available for the given keys and values.");
	        }
	        return ResponseEntity.ok(jsonData);
	    }
	    
	    
	    
	    
	    // all is dynamic you can read any csv file and return json but only one file  check at time  
	    
	    @PostMapping("/data/filter")
	    public ResponseEntity<String> getJsonDataByDynamicKeys(@RequestBody Map<String, String> keyValues) throws IOException {
	        String jsonData = inf.getJsonDataByDynamicKeys(keyValues);
	        if (jsonData == null) {
	        	logger.error("No data available for the given keys and values.");
	            return ResponseEntity.status(404).body("No data available for the given keys and values.");
	        }
	        
	        logger.info("created");
	        return ResponseEntity.ok(jsonData);
	    }
	    
	 
	   
	    
	    
	    
	    
	    
	    
	    //  same procedure with many file 
	    
	    @GetMapping("/convertall")
	    public ResponseEntity<String> convertCsvToJsonallcsv(@RequestParam("fileName") String fileName) throws IOException {
	        String json = inf.convertCsvToJsonsallcsv(fileName);
	        return ResponseEntity.ok(json);
	    }
	    
	    @PostMapping("/data/filterfromAllCSV")
	    public ResponseEntity<String> getALLCSVfileJsonDataByDynamicKeys(@RequestBody Map<String, String> keyValues) throws IOException {
	        String jsonData = inf.getALLCSVfileJsonDataByDynamicKeys(keyValues);
	        if (jsonData == null) {
	        	
	        	logger.error("No data available for the given keys and values.");
	            return ResponseEntity.status(404).body("No data available for the given keys and values.");
	        }
	        
	        logger.info("created");
	        return ResponseEntity.ok(jsonData);
	    }
	    
	    
	    
	    @PostMapping("/data/filterAllNew")
	    public ResponseEntity<String> getJsonDataByDynamicKeysNew(@RequestBody Map<String, String> keyValues) throws IOException {
	        String jsonData = inf.getALLCSVfileJsonDataByDynamicKeysNEW(keyValues);
	        
	        
	        if (jsonData == null) {
	        	
	        	logger.error("No data available for the given keys and values.");
	            return ResponseEntity.status(404).body("No data available for the given keys and values.");
	        }
	        
	        logger.info("created");
	        return ResponseEntity.ok(jsonData);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    /**
	     * 
	     * oracle to csv file generate 
	     * 
	     * */
	    
	    
	    @GetMapping("/export")
	    public ResDTO exportStatesToCsv(@RequestParam("fileName") String fileName) {
	        try {
	        	    
	             List<State> a= inf.exportStatesToCsv(fileName);
	             
	             ResDTO res=new ResDTO();
	              res.setList(a);
	             
	              logger.info("created");
	            return res;
	        } catch (IOException e) {
	            e.printStackTrace();
	            ResDTO res = new ResDTO();
	            
	            res.setMessage("Error occurred while exporting CSV file.");
	            return res;
	        }
	    }
	    
	    
	 
}