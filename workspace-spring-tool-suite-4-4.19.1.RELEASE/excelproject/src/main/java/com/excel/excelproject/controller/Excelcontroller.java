package com.excel.excelproject.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.excel.excelproject.dto.Exceldto;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;



@RestController
@RequestMapping("/excel")
public class Excelcontroller {
	
	 @PostMapping("/upload")
	    public List<Exceldto> uploadExcel(@RequestParam("file") MultipartFile file) {
	        List<Exceldto> data = new ArrayList<>();

	        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
	            Sheet sheet = workbook.getSheetAt(0);
	            for (Row row : sheet) {
	                Cell cell1 = row.getCell(0);
	                Cell cell2 = row.getCell(1);
	                Cell cell3 = row.getCell(2); // Assuming the integer column is the third column

	                Exceldto dto = new Exceldto();
	                dto.setFirtname(cell1.toString());
	                dto.setLastname(cell2.toString());
	                dto.setMarks(cell3.toString());

	                data.add(dto);
	            }
	            
	        } catch (IOException e) {
	            // Handle the exception
	        }

	        return data;
	    }

}
