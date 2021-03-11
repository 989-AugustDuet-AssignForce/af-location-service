package com.revature.repository;



import com.revature.model.Building;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Integer> {


    List<Building> getByLocationId(int locationId);

	@Query("select b from Building b where b.location.id= ?1")
	List<Building> findAllBuildingsByLocationId(Integer id);

}
