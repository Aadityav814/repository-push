package com.crosssellmaster.crosssell.mdto;

import java.util.List;

import com.crosssellmaster.crosssell.modal.Crossmaster;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponseDto {
	
	
	
	private String message;
	private Integer statuscode;
	private boolean result;
	private List<Crossmaster>list;

}
