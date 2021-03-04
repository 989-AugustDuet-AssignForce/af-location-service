package com.revature.repository;

import com.revature.model.Building;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository <Building, Integer> {
	
	public List<Building> findByCity(String city);
	public List<Building> findByStreetAddress(String address);
}
