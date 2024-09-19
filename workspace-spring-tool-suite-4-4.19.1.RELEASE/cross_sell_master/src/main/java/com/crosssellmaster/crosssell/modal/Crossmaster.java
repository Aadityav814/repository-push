package com.crosssellmaster.crosssell.modal;






import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "CROSS_SELL_MASTER")
@Setter
@Getter
public class Crossmaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String crsellValue;
	private String isAactive;
	private String productCode;
	
	

}
