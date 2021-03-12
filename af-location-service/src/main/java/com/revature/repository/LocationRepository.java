package com.revature.repository;

import com.revature.dto.BuildingDto;
import com.revature.model.Building;
import com.revature.model.Location;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findAllByState(String state);

    List<Location> findAllByCity(String city);

    List<Location> findAllByZipCode(String zipCode);
}
