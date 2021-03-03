package com.revature.service;

import java.util.List;

import com.revature.dto.BuildingDto;
import com.revature.dto.LocationDto;
import com.revature.model.Location;

public interface LocationService {
	
	public void createLocation(Location location);	
	public List<LocationDto> getAllLocations();	
	public List<LocationDto> getLocationsByState(String state);
	public List<LocationDto> getLocationsByCity(String city);
	public List<LocationDto> getLocationsByZipCode(String zipCode);
	public LocationDto getLocation( int index);	
	public void updateState(int index,String state);
	public void updateCity(int index, String city);	
	public void updateZipCode(int index, String zipCode);
	public void deleteLocation(int index);	
	public void addBuilding(int index, BuildingDto buildingDto);
	public void updateLocation(int index, Location location);
}
