package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.dto.BuildingDetailsDto;
import com.revature.dto.BuildingDto;
import com.revature.dto.BuildingRequestDto;
import com.revature.model.Building;
import com.revature.model.Room;
import com.revature.repository.BuildingRepository;

public class BuildingService {
	
	@Autowired
	BuildingRepository buildingRepository;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	LocationService locationService;
	
	public void createBuilding(Building building) {
		
	}
	
	public List<BuildingDto> getBuildings() {
		return null;
	}
	
	public List<BuildingDto> getBuildingsByCity(String city){
		return null;
	}
	public List<BuildingDto> getBuildingsByStreetAddress(String address) {
		return null;
	}
	public BuildingDetailsDto getBuilding(int index) {
		return null;
	}
	public void updateCity( int index, String city) {
		
	}
	public void updateZipCode( int index, String zipCode) {
		
	}
	public void updateNumberOfFloors( int index, int floorCount) {
		
	}
	public void deleteBuilding( int index ) {
		
	}
	public void addRoom( int index, Room room) {
		
	}
	public void updateBuilding(int index, BuildingRequestDto dto) {
		
	}
	

}
