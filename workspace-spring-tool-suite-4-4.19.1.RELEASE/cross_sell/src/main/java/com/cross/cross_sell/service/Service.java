package com.cross.cross_sell.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.cross.cross_sell.dto.MasterResdto;
import com.cross.cross_sell.modal.CrossSell;




@org.springframework.stereotype.Service

public class Service implements Masterinf  {

	
		
		@Autowired
		MasterRepository ex;
		
		private static final String EXCEL_EXTENSION = ".xlsx"; // Change to ".xls" for older Excel format

		private static final Logger logger=LoggerFactory.getLogger(Service.class);
		@Override
		public boolean isValidExcelFile(MultipartFile file) {
			String originalFilename = file.getOriginalFilename();

			return (originalFilename != null && originalFilename.toLowerCase().endsWith(EXCEL_EXTENSION));

		}

		@Override
		public ResponseEntity<MasterResdto> readExcel(MultipartFile file) {
			// TODO Auto-generated method stub
			List<CrossSell> list=new ArrayList<>();
			try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
				Sheet sheet = workbook.getSheetAt(0);
				int rowNumber = 0;
				Iterator<Row> rowIterator = sheet.iterator();

				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();
					Boolean isrowEmpty = checkIfRowIsEmpty(sheet.getRow(1));

					if (isrowEmpty) {

						MasterResdto res = new MasterResdto();

						res.setMessage("data not found");

						return ResponseEntity.status(HttpStatus.OK).body(res);
					}

					if (rowNumber == 0) {
						rowNumber++;
						continue;
					}

					Iterator<Cell> cellIterator = row.cellIterator();

				CrossSell dto = new CrossSell();

					if (cellIterator.hasNext()) {
						dto.setCrsellValue(cellIterator.next().toString());
					}
					if (cellIterator.hasNext()) {
						dto.setIsAactive(cellIterator.next().toString());
					}
					if (cellIterator.hasNext()) {
						dto.setProductCode(cellIterator.next().toString());
					}
					
					 list.add(dto);

					

				}
				
				


				MasterResdto res = new MasterResdto();
				res.setResult(true);
				res.setMessage("file uploaded successful");
				res.setStatuscode(200);
				res.setList(list);
	            ex.saveAll(list);
	            
	            logger.info("Compleated ....200");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
			} catch (IOException e) {
				// Handle the exception
   logger.warn("uplode correct folder...");
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

		@Override
		public MasterResdto createresponce() {
			// TODO Auto-generated method stub
			MasterResdto res = new MasterResdto();
			res.setMessage("Table has been Created & data Stored successfull");
			res.setStatuscode(200);
			res.setResult(true);
			return res;
		}

		@Override
		public MasterResdto createrespose() {
			// TODO Auto-generated method stub
			MasterResdto res = new MasterResdto();
			res.setMessage("recored is alredy present");
			res.setStatuscode(200);
			res.setResult(true);
			return res;
		}

		@Override
		public boolean createEmployeeByEmpid(CrossSell list) {
			// TODO Auto-generated method stub
			Optional<CrossSell> employee = ex.findByCrsellValue(list.getCrsellValue());

	        if (!employee.isPresent()) {
	        
	            ex.save(list);
	            return true;
	        } else {
	            return false;
	        }
		}

		@Override
		public void deletebyproduct(String productCode) {
			// TODO Auto-generated method stub
			ex.deleteAllByproductCode(productCode);
			
		}
		
		

		}

