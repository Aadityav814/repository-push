package com.excel.exceitrat.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.excel.exceitrat.exceldtoo.Exceldto;
import com.excel.exceitrat.exceldtoo.ResponceDto;

@Service
public class TestService implements TestServiceinf {
	
	 private static final String EXCEL_EXTENSION = ".xlsx"; // Change to ".xls" for older Excel format

	    @Override
	    public boolean isValidExcelFile(MultipartFile file) {
	        String originalFilename = file.getOriginalFilename();
	        return originalFilename != null && originalFilename.toLowerCase().endsWith(EXCEL_EXTENSION);
}
	    @Override
	    public ResponseEntity<ResponceDto> readExcel(@RequestParam("file") MultipartFile file){
	    	
	    	
	    	 
			List<Exceldto> list = new ArrayList<>();

	        
	        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
	            Sheet sheet = workbook.getSheetAt(0);
	            int rowNumber=0;
	            Iterator<Row> rowIterator = sheet.iterator();

	            while (rowIterator.hasNext()) {
	            	
	            	
	            	 Row row = rowIterator.next();
	            	 
	            	 if (rowNumber == 0) {
	                     rowNumber++;
	                     continue;
	                 }

	                Iterator<Cell> cellIterator = row.cellIterator();

	                 Exceldto dto = new Exceldto();
	                 
	                 if (cellIterator.hasNext()) {
	                     dto.setFirtname(cellIterator.next().toString());
	                 }
	                 if (cellIterator.hasNext()) {
	                     dto.setLastname(cellIterator.next().toString());
	                 }
	                 if (cellIterator.hasNext()) {
	                     dto.setMarks((int) cellIterator.next().getNumericCellValue());
	                 }

	                list.add(dto);
	            }
	            ResponceDto res= new ResponceDto();
		    	res.setResult(true);
		    	res.setMessage("list return successfull");
		    	res.setStatuscode(200);
		    	res.setList(list);
	           
	            
	            return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
	        } catch (IOException  e) {
	            // Handle the exception
	        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    	
	    	
	   
	    	
	    	
	    }
}