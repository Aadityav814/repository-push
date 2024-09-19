package com.excel.employedetails.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.excel.employedetails.eDTO.ResponseDto;

public interface Empinf {

boolean isValidExcelFile(MultipartFile file);

ResponseEntity<ResponseDto>readExcel(MultipartFile file );

	//String isValidExcelFile(MultipartFile file);
	 
	 
	 
}
