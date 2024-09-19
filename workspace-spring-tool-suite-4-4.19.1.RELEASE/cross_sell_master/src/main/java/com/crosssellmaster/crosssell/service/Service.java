package com.crosssellmaster.crosssell.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.crosssellmaster.crosssell.mdto.ResponseDto;
import com.crosssellmaster.crosssell.modal.Crossmaster;



@org.springframework.stereotype.Service
public class Service  implements Crossinf{
	
	
	@Autowired
	CrSellRepository ex;

	@Override
	public boolean createEmployeeByEmpid(Crossmaster list) {
		// TODO Auto-generated method stub
		Optional<Crossmaster> employee = ex.findByCrsellValue(list.getCrsellValue());

        if (!employee.isPresent()) {
        
            ex.save(list);
            return true;
        } else {
            return false;
        }
	}

	@Override
	public ResponseDto createresponce() {
		// TODO Auto-generated method stub
		ResponseDto res = new ResponseDto();
		res.setMessage("Table has been Created & data Stored successfull");
		res.setStatuscode(200);
		res.setResult(true);
		return res;
	}

	@Override
	public ResponseDto createrespose() {
		// TODO Auto-generated method stub
		ResponseDto res = new ResponseDto();
		res.setMessage("recored is alredy present");
		res.setStatuscode(200);
		res.setResult(true);
		return res;
	}

	@Override
	public List<Crossmaster> getByisActive(String isAactive) {
		// TODO Auto-generated method stub
		List<Crossmaster> employee = ex.findAllByisAactive(isAactive);
		
		return employee;
	}

	@Override
	public ResponseDto crResponce() {
		// TODO Auto-generated method stub
		ResponseDto res = new ResponseDto();
		res.setMessage("record not exist");
		res.setStatuscode(200);
		res.setResult(true);
		return res;
	}

	@Override
	public List<Crossmaster> getByisActive2(String checkfalg) {
		// TODO Auto-generated method stub
List<Crossmaster> employee = ex.findAllByisAactive(checkfalg);
		
		return employee;
	}

	

}
