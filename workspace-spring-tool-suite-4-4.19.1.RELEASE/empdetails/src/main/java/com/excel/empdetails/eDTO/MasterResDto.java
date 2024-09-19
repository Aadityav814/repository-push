package com.excel.empdetails.eDTO;

import java.util.List;

import com.excel.empdetails.model.Employee;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MasterResDto {
	private String message;
	private Integer statuscode;
	private boolean result;
	private List<Employee>list;

}
