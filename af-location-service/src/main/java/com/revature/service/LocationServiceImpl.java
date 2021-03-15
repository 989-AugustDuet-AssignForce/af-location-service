package com.revature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import com.revature.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Building;
import com.revature.model.Location;
import com.revature.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public void createLocation( LocationRequestDto locationRequestDto ) {

		Location location = new Location();
		location.setCity( locationRequestDto.getCity() );
		location.setState(locationRequestDto.getState());
		location.setZipCode(locationRequestDto.getZipCode());
		locationRepository.save(location);
    
	}

	@Override
	public List<LocationDto> getAllLocations() {
		List<Location> locations = locationRepository.findAll();
		return locations.stream().map(location -> {
			LocationDto locationDto = new LocationDto();
			locationDto.setId(location.getLocationId());
			locationDto.setCity(location.getCity());
			locationDto.setState(location.getState());
			locationDto.setZipCode(location.getZipCode());
			locationDto.setNumBuildings(location.getBuildings().size());
			return locationDto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<LocationDto> getLocationsByState(String state) {
		String sanitizedState = this.sanitizeState(state);
		List<Location> locations = locationRepository.findAllByState(sanitizedState);
		return locations.stream().map(location -> {
			LocationDto locationDto = new LocationDto();
			locationDto.setId(location.getLocationId());
			locationDto.setCity(location.getCity());
			locationDto.setState(location.getState());
			locationDto.setZipCode(location.getZipCode());
			locationDto.setNumBuildings(location.getBuildings().size());
			return locationDto;
		}).collect(Collectors.toList());
//		List<LocationDto> locations = this.getAllLocations();
//		return locations.stream()
//				.filter(locationDto -> locationDto.getState().equals(sanitizedState)).collect(Collectors.toList());
	}

	@Override
	public List<LocationDto> getLocationsByCity(String city) {
		String sanitizedCity = this.sanitizeCity(city);
		List<Location> locations = locationRepository.findAllByCity(sanitizedCity);
		return locations.stream().map(location -> {
			LocationDto locationDto = new LocationDto();
			locationDto.setId(location.getLocationId());
			locationDto.setCity(location.getCity());
			locationDto.setState(location.getState());
			locationDto.setZipCode(location.getZipCode());
			locationDto.setNumBuildings(location.getBuildings().size());
			return locationDto;
		}).collect(Collectors.toList());
//		List<LocationDto> locations = this.getAllLocations();
//		return locations.stream()
//				.filter(locationDto -> locationDto.getCity().equals(sanitizedCity)).collect(Collectors.toList());
	}

	@Override
	public List<LocationDto> getLocationsByZipCode(String zipCode) {
		if( zipCode.length() > 5 ) {
			zipCode = zipCode.substring(0,4);
		}
		List<Location> locations = locationRepository.findAllByZipCode(zipCode);
		return locations.stream().map(location -> {
			LocationDto locationDto = new LocationDto();
			locationDto.setId(location.getLocationId());
			locationDto.setCity(location.getCity());
			locationDto.setState(location.getState());
			locationDto.setZipCode(location.getZipCode());
			locationDto.setNumBuildings(location.getBuildings().size());
			return locationDto;
		}).collect(Collectors.toList());
//		String sanitizedZipCode = zipCode;
//		List<LocationDto> locations = this.getAllLocations();
//		return locations.stream()
//				.filter(locationDto -> locationDto.getZipCode().equals(sanitizedZipCode)).collect(Collectors.toList());
    
	}

	@Override
	public LocationDetailsDto getLocation(int index) {
    
		Location location = locationRepository.findById(index).get();
		LocationDetailsDto locationDetailsDto = new LocationDetailsDto();

		locationDetailsDto.setId(location.getLocationId());
		locationDetailsDto.setCity(location.getCity());
		locationDetailsDto.setState(location.getState());
		locationDetailsDto.setZipCode(location.getZipCode());

		List<BuildingDto> buildingDtos = location.getBuildings().stream().map(building -> {
			BuildingDto buildingDto = new BuildingDto();
			buildingDto.setId(building.getBuildingId());
			buildingDto.setStreet_address(building.getStreetAddress());
			buildingDto.setTotalFloors(building.getTotalFloors());
			buildingDto.setNumRooms(building.getRooms().size());
			return buildingDto;
		}).collect(Collectors.toList());

		locationDetailsDto.setBuildings(buildingDtos);


		return locationDetailsDto;
    
	}

	@Override
	public void updateState(int index, String state) {

		Location location = locationRepository.findById(index).get();
		location.setState(state);
		locationRepository.save(location);

	}

	@Override
	public void updateCity(int index, String city) {

		Location location = locationRepository.findById(index).get();
		location.setCity(city);
		locationRepository.save(location);
    
	}

	@Override
	public void updateZipCode(int index, String zipCode) {

		Location location = locationRepository.findById(index).get();
		location.setZipCode(zipCode);
		locationRepository.save(location);
    
	}

	@Override
	public void deleteLocation(int index) {

		locationRepository.deleteById(index);

	}

	@Override
	public void updateLocation(int index, LocationRequestDto locationRequestDto) {

		Location location = locationRepository.findById(index).get();
		location.setState(locationRequestDto.getState());
		location.setCity(locationRequestDto.getCity());
		location.setZipCode(locationRequestDto.getZipCode());
		locationRepository.save(location);

	}

//	@Override
	public List<BuildingDto> findBuildingsByLocation(int id) {
		return null;
	}

	// helper methods
	private String sanitizeState(String state) {
		if( state.length() > 2 ) {
			switch (state) {
				case "Virginia":
				case "virginia":
				case "VIRGINIA":
					state = "VA";
					break;
				case "Texas":
				case "texas":
				case "TEXAS":
					state = "TX";
					break;
				case "Florida":
				case "florida":
				case "FLORIDA":
					state = "FL";
					break;
			}
		}

		return state.toUpperCase(Locale.ROOT);
	}

	private String sanitizeCity(String city) {
		city = city.toLowerCase(Locale.ROOT);

		switch (city) {
			case "reston":
				city = "Reston";
				break;
			case "arlington":
				city = "Arlington";
				break;
			case "tampa":
				city = "Tampa";
				break;
		}

		String sanitizedCity = city;
		return sanitizedCity;
	}
	
}

