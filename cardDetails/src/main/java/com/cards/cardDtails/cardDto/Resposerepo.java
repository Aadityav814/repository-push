package com.cards.cardDtails.cardDto;

import java.util.List;

import com.cards.cardDtails.modal.CardDetail;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Resposerepo {
	
	private String message;
	private Integer statuscode;
	private boolean result;
	private List<CardDetail>list;

}
