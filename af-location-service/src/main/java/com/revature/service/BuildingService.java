package com.revature.service;




import java.util.List;

 
import com.revature.dto.BuildingDetailsDto;
import com.revature.dto.BuildingDto;
import com.revature.dto.BuildingRequestDto;


public interface BuildingService{
	
 	List<BuildingDto> getBuildings();
	List<BuildingDto> getBuildingsByCity(String city);
	List<BuildingDto> getBuildingsByStreetAddress(String city);
	BuildingDetailsDto getBuilding(int index) throws Exception;
	void updateCity(int index, String city) throws Exception;
	void updateNumberOfFloors(int index, int floors) throws Exception;
// 	void addRoom(int index, Room room) throws Exception;
// 	void deleteRoom(int indexBuilding, int indexRoom) throws Exception;
	void updateBuilding(int index, BuildingRequestDto dto) throws Exception;

    List<BuildingDto> getBuildingsByLocation(int index);
    void createBuilding(BuildingRequestDto buildingRequestDto, int index);
    void deleteBuilding(int buildingIndex, int locationIndex) throws Exception;

