package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.GraphServices;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MainController {
	@Autowired
	 private  GraphServices graph;
	
	@GetMapping("/path")
	public String getMethodName() {
		return "Hello World";
	}
	
	
	 public static final Logger logger= LoggerFactory.getLogger(MainController.class);

	    @PostMapping("/email/{userEmail}")
	    public String createEvent(@PathVariable String userEmail, @RequestBody Map<String, Object> eventDetails) {
	    	
	    	logger.info("Completed...200");
	        try {  
	        	
	        	logger.info(userEmail);
	            return graph.createEvent(userEmail, eventDetails);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error creating event: " + e.getMessage();
	        }
	    }
	  
	    
	    
	    @DeleteMapping("/{userEmail}/{eventId}")
	    public String deleteEvent(@PathVariable String userEmail, @PathVariable String eventId) {
	        try {
	            graph.deleteEvent(userEmail, eventId);
	            return "Event deleted successfully.";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error deleting event: " + e.getMessage();
	        }
	

}
	    
	    
	    
	    @PutMapping("/{userEmail}/{eventId}")
	    public String updateEvent(@PathVariable String userEmail, @PathVariable String eventId, @RequestBody Map<String, Object> eventDetails) {
	        try {
	            graph.updateEvent(userEmail, eventId, eventDetails);
	            return "Event updated successfully.";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error updating event: " + e.getMessage();
	        }}
}
