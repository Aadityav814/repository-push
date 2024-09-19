package com.excel.exceitrat.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.excel.exceitrat.exceldtoo.Exceldto;
import com.excel.exceitrat.exceldtoo.ResponceDto;
import com.excel.exceitrat.service.TestServiceinf;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/excel")

public class Exceiterator {

    private final TestServiceinf excelFileValidator;

    public Exceiterator(TestServiceinf excelFileValidator) {
        this.excelFileValidator = excelFileValidator;
    }
	
    @Autowired 
    TestServiceinf inf;
	@PostMapping("/upload")
public ResponseEntity<ResponceDto> uploadExcel(MultipartFile file) {
		if (!excelFileValidator.isValidExcelFile(file)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


		
		 return inf.readExcel(file);
        
		 
    }



}
