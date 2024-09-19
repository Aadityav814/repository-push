package com.crosssellmaster.crosssell.service;


import java.util.List;

import com.crosssellmaster.crosssell.mdto.ResponseDto;
import com.crosssellmaster.crosssell.modal.Crossmaster;

public interface Crossinf {

	boolean createEmployeeByEmpid(Crossmaster list);

	ResponseDto createresponce();

	ResponseDto createrespose();

	java.util.List<Crossmaster> getByisActive(String isAactive);

	ResponseDto crResponce();

	List<Crossmaster> getByisActive2(String checkfalg);

	
	

}
