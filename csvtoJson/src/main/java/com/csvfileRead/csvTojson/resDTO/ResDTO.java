package com.csvfileRead.csvTojson.resDTO;

import java.util.List;

import com.csvfileRead.csvTojson.model.State;



public class ResDTO {

	
	
	private String message;
	private Integer statuscode;
	private boolean result;
	private List<State>list;
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
	public List<State> getList() {
		return list;
	}
	public void setList(List<State> list) {
		this.list = list;
	}
}
