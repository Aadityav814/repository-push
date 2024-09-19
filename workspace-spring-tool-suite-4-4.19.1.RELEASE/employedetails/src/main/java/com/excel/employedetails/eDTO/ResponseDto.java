package com.excel.employedetails.eDTO;

import java.util.List;

import com.excel.employedetails.model.EmployeeDetails;


public class ResponseDto {
	private String message;
	private Integer statuscode;
	private boolean result;
	private List<EmployeeDetails>list;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(Integer statuscode) {
		this.statuscode = statuscode;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public List<EmployeeDetails> getList() {
		return list;
	}
	public void setList(List<EmployeeDetails> list) {
		this.list = list;
	}
}
