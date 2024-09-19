package com.crosssellmaster.crosssell.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crosssellmaster.crosssell.modal.Crossmaster;

public interface CrSellRepository extends JpaRepository<Crossmaster, Long> {

	Optional<Crossmaster> findByCrsellValue(String crsellValue);

	

	List<Crossmaster> findAllByisAactive(String isAactive);

}
