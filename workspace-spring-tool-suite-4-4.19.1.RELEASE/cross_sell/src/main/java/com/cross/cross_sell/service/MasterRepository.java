package com.cross.cross_sell.service;


import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cross.cross_sell.modal.CrossSell;
@Repository
@EnableScan
public interface MasterRepository extends CrudRepository<CrossSell,String> {

	Optional<CrossSell> findByCrsellValue(String crsellValue);

	void deleteAllByproductCode(String productCode);

	
}
