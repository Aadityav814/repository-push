package com.excel.employedetails.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excel.employedetails.eDTO.ResponseDto;
import com.excel.employedetails.model.EmployeeDetails;

import jakarta.transaction.Transactional;

@Service
public class ServiceEmp implements Empinf {
	@Autowired
	ExcelDataRepository ex;

	private static final String EXCEL_EXTENSION = ".xlsx"; // Change to ".xls" for older Excel format

	@Override
	public boolean isValidExcelFile(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();

		return (originalFilename != null && originalFilename.toLowerCase().endsWith(EXCEL_EXTENSION));

	}

	@Override
	public ResponseEntity<ResponseDto> readExcel(MultipartFile file) {

		List<EmployeeDetails> insertlist = new ArrayList<>();
		List<EmployeeDetails> updatelist = new ArrayList<>();
		List<String> deletelist = new ArrayList<>();
		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);
			int rowNumber = 0;
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				Boolean isrowEmpty=checkIfRowIsEmpty(sheet.getRow(1));

				 if(isrowEmpty) {

					 ResponseDto res= new ResponseDto();

	            	 res.setMessage("data not found");
	           
	            	return ResponseEntity.status(HttpStatus.OK).body(res); }

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellIterator = row.cellIterator();

				EmployeeDetails dto = new EmployeeDetails();

				if (cellIterator.hasNext()) {
					dto.setEmpid(cellIterator.next().toString());
				}
				if (cellIterator.hasNext()) {
					dto.setFirstname(cellIterator.next().toString());
				}
				if (cellIterator.hasNext()) {
					dto.setLastname(cellIterator.next().toString());
				}
				if (cellIterator.hasNext()) {
					dto.setMobile(cellIterator.next().toString());
				}
				if (cellIterator.hasNext()) {
					dto.setAge((int) cellIterator.next().getNumericCellValue());
				}
				if (cellIterator.hasNext()) {
					dto.setDesignation(cellIterator.next().toString());
				}
				if (cellIterator.hasNext()) {
					dto.setStatus(cellIterator.next().toString());
				}

				if ("new".equalsIgnoreCase(dto.getStatus())) {
					insertlist.add(dto);

				}
				if ("update".equalsIgnoreCase(dto.getStatus())) {
					updatelist.add(dto);
				}
				if ("delete".equalsIgnoreCase(dto.getStatus())) {

					deletelist.add(dto.getEmpid());
				}

			}

			insertopration(insertlist);
			updateopration(updatelist);
			deleteopration(deletelist);

			ResponseDto res = new ResponseDto();
			res.setResult(true);
			res.setMessage("file uploaded successful");
			res.setStatuscode(200);

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
		} catch (IOException e) {
			// Handle the exception

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}
	private boolean checkIfRowIsEmpty(Row row) {
	    if (row == null) {
	        return true;
	    }    if (row.getLastCellNum() <= 0) {
	        return true;
	    }    for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
	        Cell cell = row.getCell(cellNum);
	        if (cell != null && cell.getCellType() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
	            return false;
	        }
	    }
	    return true;
	}

	@Transactional
	private void deleteopration(List<String> deletelist) {
		// TODO Auto-generated method stub
		try {
			ex.deletebyempids(deletelist);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Transactional
	private void updateopration(List<EmployeeDetails> updatelist) {
		// TODO Auto-generated method stub
//		try {
//      
//	for(EmployeeDetails x:updatelist) {
//		ex.updateExcel(x.getFirstname(),x.getLastname(),x.getMobile(), x.getAge(), x.getDesignation(),x.getEmpid());
//      }
//		} catch (Exception e) {
//			// TODO: handle exception
	
		try {

			for (EmployeeDetails x : updatelist) {
				System.out.print("empid"+x.getEmpid());

				ex.updation(x.getEmpid(), x.getFirstname(), x.getLastname(), x.getAge(), x.getMobile(), x.getDesignation());

				//ex.updation(x.getFristname(),x.getLastname(),x.getMobile(), x.getAge(), x.getDesignation(),x.getEmpid());

			}

			

 

		} catch (Exception e) {

			// TODO: handle exception

		}

	}

	

	private void insertopration(List<EmployeeDetails> insertlist) {
		// TODO Auto-generated method stub

		try {
			ex.saveAll(insertlist);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
}
