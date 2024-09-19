package com.excel.employedetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excel.employedetails.Service.Empinf;
import com.excel.employedetails.eDTO.ResponseDto;

@RestController
@RequestMapping("/excel")
public class EmpController {

	@Autowired
	Empinf inf;

	@PostMapping("/upload")
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
	
	
	

}
