package com.cross.cross_sell.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.cross.cross_sell.dto.MasterResdto;
import com.cross.cross_sell.modal.CrossSell;

public interface Masterinf {

	boolean isValidExcelFile(MultipartFile file);

	ResponseEntity<MasterResdto> readExcel(MultipartFile file);

	MasterResdto createresponce();

	MasterResdto createrespose();

	boolean createEmployeeByEmpid(CrossSell list);

	void deletebyproduct(String productCode);

}
