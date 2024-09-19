package com.excel.exceitrat.exceldtoo;

import java.util.List;

public class ResponceDto {
	
	private String message;
	private Integer statuscode;
	private boolean result;
	private List<Exceldto>list;
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
	public List<Exceldto> getList() {
		return list;
	}
	public void setList(List<Exceldto> list) {
		this.list = list;
	}

}
