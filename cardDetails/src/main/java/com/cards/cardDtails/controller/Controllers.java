package com.cards.cardDtails.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cards.cardDtails.cardDto.Resposerepo;
import com.cards.cardDtails.modal.CardDetail;
import com.cards.cardDtails.service.Cardinf;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Controllers {
	
	@Autowired
	Cardinf inf;
	
	
	
	
	
	@GetMapping("/demo")
	public Resposerepo add() {
		
	  log.info("data will save ");
	  log.warn("in process ");
	  log.error("Stop the program ");
	  
	  log.debug(" debug that error....");
	  
	  
	  List<CardDetail> save=inf.find();
	  Resposerepo res= new Resposerepo();
		res.setMessage("please card assign to  all user given below   ");
		res.setStatuscode(404);
		res.setResult(false);
		res.setList(save);
		System.out.println(res);
		return res ;
		
		
		
	}
	@GetMapping("/save")
	public List<CardDetail> save(){
		
		List<CardDetail> save= inf.save();
		log.info("created.."+"201");
		return save;
		
	}
	@GetMapping("/find")
	public Resposerepo find(){
		
		
		List<CardDetail> save= inf.find();
		
		if (save !=null&& !save.isEmpty()) {
			
			
			Resposerepo res= new Resposerepo();
			res.setMessage("Success");
			res.setStatuscode(200);
			res.setResult(true);
			
			res.setList(save);
			
			return res;
		}else {
			Resposerepo res= new Resposerepo();
			res.setMessage("data not found");
			res.setStatuscode(404);
			res.setResult(false);
			
			
			return res;}
		
		
		
	
		
	}
	
	
	
	
}
