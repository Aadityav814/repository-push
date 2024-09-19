package com.csvfileRead.csvTojson.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ENTITY_CSVFILEdd")
public class State {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private Integer id;
	 
	 
	    private String abbreviation;
	    private String state;
		public String getAbbreviation() {
			return abbreviation;
		}
		public void setAbbreviation(String abbreviation) {
			this.abbreviation = abbreviation;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}

}
