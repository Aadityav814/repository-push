package com.excel.excelproject.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.excel.excelproject.exceldtoo.Edto;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/excel")

public class Exceiterator {

	
	@PostMapping("/upload")
    public List<Edto> uploadExcel(@RequestParam("file") MultipartFile file) {
        List<Edto> data = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
            	 Row row = rowIterator.next();
                 Iterator<Cell> cellIterator = row.cellIterator();

                 Edto dto = new Edto();
                 if (cellIterator.hasNext()) {
                     dto.setFirtname(cellIterator.next().toString());
                 }
                 if (cellIterator.hasNext()) {
                     dto.setLastname(cellIterator.next().toString());
                 }
                 if (cellIterator.hasNext()) {
                     dto.setMarks((int) cellIterator.next().getNumericCellValue());
                 }

                data.add(dto);
            }
        } catch (IOException e) {
            // Handle the exception
        }

        return data;
    }
}
