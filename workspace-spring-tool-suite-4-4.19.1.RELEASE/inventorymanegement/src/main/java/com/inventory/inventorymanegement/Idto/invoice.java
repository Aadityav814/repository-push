package com.inventory.inventorymanegement.Idto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int permissionId;
	private  Integer cId ;
	private  String cName;
	private String cProductName;
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcProductName() {
		return cProductName;
	}
	public void setcProductName(String cProductName) {
		this.cProductName = cProductName;
	}

}
