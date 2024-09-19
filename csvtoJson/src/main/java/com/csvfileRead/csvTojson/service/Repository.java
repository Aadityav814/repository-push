package com.csvfileRead.csvTojson.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csvfileRead.csvTojson.model.State;

public interface Repository extends JpaRepository<State, Integer > {

}
