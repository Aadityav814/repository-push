package com.crosssellmaster.crosssell.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crosssellmaster.crosssell.mdto.ResponseDto;
import com.crosssellmaster.crosssell.modal.Crossmaster;
import com.crosssellmaster.crosssell.service.Crossinf;






@Controller
@RequestMapping("/CrossMaster")
public class MasterController {
	
	@Autowired
	private Crossinf inf;
	
	@PostMapping("/created")
	public ResponseEntity<ResponseDto> CreateTable(@RequestBody Crossmaster list) {
		boolean create = inf.createEmployeeByEmpid(list);

		if (create) {

			ResponseDto res = inf.createresponce();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		} else {
			ResponseDto res = inf.createrespose();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		}
	}

	@GetMapping("/readbyisActive/{isAactive}")
	public ResponseEntity<?> getEmployeeByEmpid(@PathVariable String isAactive) {
		
		
		List<Crossmaster> employeeDTO = inf.getByisActive(isAactive);
        if (employeeDTO != null) {
        	return ResponseEntity.ok(employeeDTO);
        }else {
		
		
		ResponseDto res = inf.crResponce();
        return ResponseEntity.status(HttpStatus.CREATED).body(res);}
	}
	
	@GetMapping("/readbyisActive")
	public ResponseEntity<?> getEmployeeByEmpid() {
		
		String checkfalg="1";
		List<Crossmaster> employeeDTO = inf.getByisActive2(checkfalg);
        if (employeeDTO != null) {
        	return ResponseEntity.ok(employeeDTO);
        }else {
		
		
		ResponseDto res = inf.crResponce();
        return ResponseEntity.status(HttpStatus.CREATED).body(res);}
	}
	
	
	
	
	@PostMapping("/json-to-pdf")
    public byte[] convertJsonToPdf(@RequestBody String json) throws IOException {
        // Create a PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(org.apache.pdfbox.pdmodel.common.PDRectangle.A4);
        document.addPage(page);

        // Create a content stream for the page
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Set the font and size (adjust as needed)
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

        // Write JSON data to the PDF
        contentStream.beginText();
        contentStream.newLineAtOffset(20, 700);
        contentStream.showText("JSON Data:");
        contentStream.newLine();

        contentStream.showText(json); // Assuming the JSON fits on one line
        contentStream.endText();

        // Close the content stream and save the document
        contentStream.close();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        document.close();

        return outputStream.toByteArray();
    }
	 
	
	
}
