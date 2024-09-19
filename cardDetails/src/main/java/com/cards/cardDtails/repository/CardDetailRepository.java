package com.cards.cardDtails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cards.cardDtails.modal.CardDetail;

@Repository
public interface CardDetailRepository extends JpaRepository<CardDetail, Integer> {
	
	


}
// query using JPQL
//@Query("select c from CardDetail c where c.cardHolderName = ?1")
//List<CardDetail> findUsingJpql(String name);
//