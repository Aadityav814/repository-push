package com.cards.cardDtails;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cards.cardDtails.modal.CardDetail;
import com.cards.cardDtails.repository.CardDetailRepository;

@SpringBootTest
class CardDetailsApplicationTests {

	
	@Autowired
	CardDetailRepository ex;
	@Test
	void contextLoads() {
	}

	
//	@Test
//	void insrtdata() {
//		
//		CardDetail card= new CardDetail();
//		
//		card.setCardHolderName("aaditya");
//		card.setAmount(100.0);
//		card.setCvv(123);
//		card.setIsActive(false);
//		ex.save(card);
//		
//	}
	
	@Test
	void finddata() {
		
		//CardDetail card= new CardDetail();
		
//		card.setCardHolderName("aaditya");
//		card.setAmount(100.0);
//		card.setCvv(123);
//		card.setIsActive(false);
//		ex.save(card);
//		
		CardDetail card = ex.findById(1).get();
		
		System.out.println(card);
	}
	
}
