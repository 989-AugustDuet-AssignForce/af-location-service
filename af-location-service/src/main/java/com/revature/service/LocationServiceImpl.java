package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.BuildingDto;
import com.revature.dto.LocationDto;
import com.revature.model.Location;
import com.revature.repository.BuildingRepository;
import com.revature.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {
	
	private LocationRepository lr;
	
	@Autowired
	public LocationServiceImpl(LocationRepository lr) {
		this.lr=lr;
	}

	@Override
	public void createLocation(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LocationDto> getAllLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocationDto> getLocationsByState(String state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocationDto> getLocationsByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocationDto> getLocationsByZipCode(String zipCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocationDto getLocation(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(int index, String state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCity(int index, String city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateZipCode(int index, String zipCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLocation(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBuilding(int index, BuildingDto buildingDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLocation(int index, Location location) {
		// TODO Auto-generated method stub
		
	}
	
	
}
