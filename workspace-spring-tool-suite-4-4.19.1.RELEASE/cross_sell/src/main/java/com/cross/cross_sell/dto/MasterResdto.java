package com.cross.cross_sell.dto;

import java.util.List;

import com.cross.cross_sell.modal.CrossSell;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterResdto {

	
	private String message;
	private Integer statuscode;
	private boolean result;
	private List<CrossSell>list;
}
