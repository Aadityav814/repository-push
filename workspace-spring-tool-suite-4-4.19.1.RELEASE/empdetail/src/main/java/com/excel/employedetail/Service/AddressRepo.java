package com.excel.employedetail.Service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excel.employedetail.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
	  
	boolean existsById(Long id);
	//Optional<Address> findByEmpid(Long id);

	Optional<Address> findById(Long id);


}
