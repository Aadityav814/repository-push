package com.excel.exceitrat.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.excel.exceitrat.exceldtoo.ResponceDto;

public interface TestServiceinf {
	 boolean isValidExcelFile(MultipartFile file);

ResponseEntity<ResponceDto>readExcel(MultipartFile file );
}
