package com.excel.empdetails.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import com.excel.empdetails.eDTO.MasterResDto;
import com.excel.empdetails.model.Employee;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;



@org.springframework.stereotype.Service
public class Services {
	
	
	
	
	@Autowired
	private MasterRepo repo;
	
	
	 @Value("${csv.file.path}")
	 private String csv;
	
	private static String jsonData;
	
	private static final String EXCEL_EXTENSION = ".xlsx"; 
	
	public boolean createEmployeeByEmail(Employee list) {
		// TODO Auto-generated method stub
		Optional<Employee> employee = repo.findByEmail(list.getEmail());

        if (!employee.isPresent()) {
        
            repo.save(list);
            return true;
        } else {
            return false;
        }
	}
	public MasterResDto createresponce() {
		// TODO Auto-generated method stub
		MasterResDto res = new MasterResDto();
		res.setMessage("Table has been Created & data Stored successfull");
		res.setStatuscode(200);
		res.setResult(true);
		
		return res;
	}

	
	public MasterResDto createrespose() {
		// TODO Auto-generated method stub
		MasterResDto res = new MasterResDto();
		res.setMessage("recored is alredy present");
		res.setStatuscode(200);
		res.setResult(true);
		return res;
	}

	
	
	public boolean isValidExcelFile(MultipartFile file) {
		// TODO Auto-generated method stub
		String originalFilename = file.getOriginalFilename();

		return (originalFilename != null && originalFilename.toLowerCase().endsWith(EXCEL_EXTENSION));
	}
	
	public ResponseEntity<MasterResDto> readExcel(MultipartFile file) {
		// TODO Auto-generated method stub
		
		
		  if (file == null || file.isEmpty()) {
		        MasterResDto res = new MasterResDto();
		        res.setMessage("File is null or empty");
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		    }

		    List<Employee> list = new ArrayList<>();
		    try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
		        Sheet sheet = workbook.getSheetAt(0);
		        int rowNumber = 0;
		        Iterator<Row> rowIterator = sheet.iterator();

		        while (rowIterator.hasNext()) {
		            Row row = rowIterator.next();
		            Boolean isRowEmpty = checkIfRowIsEmpty(row);

		            if (isRowEmpty) {
		                MasterResDto res = new MasterResDto();
		                res.setMessage("Data not found");
		                return ResponseEntity.status(HttpStatus.OK).body(res);
		            }

		            if (rowNumber == 0) {
		                rowNumber++;
		                continue;
		            }

		            Iterator<Cell> cellIterator = row.cellIterator();
		            Employee dto = new Employee();

		            if (cellIterator.hasNext()) {
		                dto.setFirstName(cellIterator.next().toString());
		            }
		            if (cellIterator.hasNext()) {
		                dto.setLastName(cellIterator.next().toString());
		            }
		            if (cellIterator.hasNext()) {
		                dto.setEmail(cellIterator.next().toString());
		            }   
		            if (cellIterator.hasNext()) {
		                dto.setDate(cellIterator.next().toString());
		            }
                    list.add(dto);
		            
		        }
		      
		        

		        MasterResDto res = new MasterResDto();
		        res.setResult(true);
		        res.setMessage("File uploaded successfully");
		        res.setStatuscode(200);
		        res.setList(list);
		        repo.saveAll(list);

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
	public List<Employee> getAllfirstName(String firstName) {
		
		
		List<Employee> emp= repo.findAllByfirstName(firstName);
		return emp;
	}
	public MasterResDto empNotFound() {
		// TODO Auto-generated method stub
		MasterResDto res = new MasterResDto();
		res.setMessage("recored is not present");
		res.setStatuscode(200);
		res.setResult(true);
		return res;
	}
	public List<Employee> getAlllastName(String lastName) {
		// TODO Auto-generated method stub
		List<Employee> emp= repo.findAllBylastName(lastName);
		
		return emp;
	}
	public List<Employee> getAllbyemail(String email) {
		// TODO Auto-generated method stub
		List<Employee> emp= repo.findAllByEmail(email);
		return emp;
	}
	public List<Employee> getAllbydate(String date) {
		// TODO Auto-generated method stub
		List<Employee> emp= repo.findAllByDate(date);
		return emp;
	}
	public List<Employee> getAllSortedByInsertDate() {
		// TODO Auto-generated method stub
		return repo.findAllByOrderByDateDesc();
	}
	
	
	
	
	public String convertCsvToJson(MultipartFile file) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class)
                .with(csvSchema)
                .readValues(file.getInputStream());
        
        List<Map<String, String>> list = mappingIterator.readAll();
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonData=jsonMapper.writeValueAsString(list);
    }
	public String getJsonData() {
		// TODO Auto-generated method stub
		return jsonData;
	}
	public String convertCsvToJsons(String file)throws IOException {
		// TODO Auto-generated method stub
		CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        File csvFile = new File(file);
        MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class)
                .with(csvSchema)
                .readValues(csvFile);
        List<Map<String, String>> list = mappingIterator.readAll();
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonData = jsonMapper.writeValueAsString(list);
        return jsonData;
    }
	
	
	
	
	
	
	
	
	/*
	 * 
	 * 
	 * */
	public String convertCsvToJsons() throws IOException {
		
		
		 if (!csv.toLowerCase().endsWith(".csv")) {
	            throw new IllegalArgumentException("File must have a .csv extension");
	        }
		// TODO Auto-generated method stub
		CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        File csvFile = new File(csv);
        MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class)
                .with(csvSchema)
                .readValues(csvFile);
        
        List<Map<String, String>> list = mappingIterator.readAll().stream()
                .map(this::removeNullValues) 
                .filter(map -> !map.isEmpty())
                .collect(Collectors.toList());
       // List<Map<String, String>> list = mappingIterator.readAll();
        
        if(list==null||list.isEmpty()) {
        	return null;
        }else {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonData = jsonMapper.writeValueAsString(list);
        return jsonData;}
	}
	
	 private Map<String, String> removeNullValues(Map<String, String> map) {
	        return map.entrySet().stream()
	                .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	    }

	
	
	
}
