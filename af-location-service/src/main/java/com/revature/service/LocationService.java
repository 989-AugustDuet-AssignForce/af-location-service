package com.revature.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.revature.dto.*;
import com.revature.model.Building;
import com.revature.model.Location;

public interface LocationService {

	public void createLocation(LocationRequestDto locationRequestDto);
	public List<LocationDto> getAllLocations();
	public List<LocationDto> getLocationsByState(String state);
	public List<LocationDto> getLocationsByCity(String city);
	public List<LocationDto> getLocationsByZipCode(String zipCode);
	public LocationDetailsDto getLocation(int index);
	public void updateState(int index,String state);
	public void updateCity(int index, String city);
	public void updateZipCode(int index, String zipCode);
	public void deleteLocation(int index);
	public void updateLocation(int index, LocationRequestDto locationRequestDto);
}
