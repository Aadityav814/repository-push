package com.excel.employedetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.fasterxml.jackson.core.StreamReadConstraints.Builder;
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EmployedetailApplication3 extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EmployedetailApplication3.class, args);
		
		 
	}
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		//you can write any thing in place of builder
        return builder.sources(EmployedetailApplication3.class);
    }
	

}
