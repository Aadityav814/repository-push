package com.cards.cardDtails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cards.cardDtails.modal.CardDetail;
import com.cards.cardDtails.repository.CardDetailRepository;

@Service
public class services implements Cardinf  {

	
	
	@Autowired
	CardDetailRepository ex;
	@Override
	public List<CardDetail> save() {
		// TODO Auto-generated method stub
CardDetail card= new CardDetail();
		
		card.setCardHolderName("aaditya");
		card.setAmount(100.0);
		card.setCvv(123);
		card.setIsActive(false);
		ex.save(card);
		 List<CardDetail>card_detail=   ex.findAll();
		 
		 return card_detail;
		
	}
	@Override
	public List<CardDetail> find() {
		// TODO Auto-generated method stub
		return ex.findAll();
	}
	
	
	

}
