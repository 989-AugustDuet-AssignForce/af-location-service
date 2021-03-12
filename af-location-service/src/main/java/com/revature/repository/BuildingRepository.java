package com.revature.repository;




import com.revature.model.Building;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface BuildingRepository extends JpaRepository<Building,Integer> {

	List<Building> findByCity(String city);
  
	List<Building> findByStreetAddress(String address);

	@Query("select b from Building b where location_id = ?1")
	List<Building> findAllByLocationId(@Param(value = "id") int id);

}
