package com.cross.cross_sell.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cross.cross_sell.dto.MasterResdto;
import com.cross.cross_sell.modal.CrossSell;
import com.cross.cross_sell.service.Masterinf;



@RestController
@RequestMapping("/CrossMaster")
public class MasterController {
	
	@Autowired
	private Masterinf inf;

	
	
	  @GetMapping("/email")
	    public String sum() {
	    	logger.info("userEmail");
	    	
	    return "raj";	
	    }
	    
	
	private static final Logger logger=LoggerFactory.getLogger(MasterController.class);
	@PostMapping("/create")
	public ResponseEntity<MasterResdto> uploadExcel(MultipartFile file) {
		// if (!inf.isValidExcelFile(file)) {
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		// }
		if (!inf.isValidExcelFile(file)) {

			MasterResdto res = new MasterResdto();
			res.setMessage("uploade valid file");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

		}

		return inf.readExcel(file);

	}
	
	
	@PostMapping("/created")
	public ResponseEntity<MasterResdto> CreateTable(@RequestBody CrossSell list) {
		
		logger.info("Completed...200");
		boolean create = inf.createEmployeeByEmpid(list);

		if (create ) {

			MasterResdto res = inf.createresponce();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		} else {
			MasterResdto res = inf.createrespose();

			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		}
	}

	@DeleteMapping("/deletebyid/{productCode}")
	public ResponseEntity <MasterResdto> getall( @PathVariable ("productCode")String productCode) {
		// if (!inf.isValidExcelFile(file)) {
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		// }
		
		try {
			
			inf.deletebyproduct(productCode);
			
			MasterResdto res= new  MasterResdto();
			res.setMessage("data has been deleted  success");
			res.setStatuscode(200);
			res.setResult(true);
			logger.info("Completed...200");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
	
			
		} catch (Exception e) {
			// TODO: handle exception
			
			
			logger.error(productCode+"not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
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
