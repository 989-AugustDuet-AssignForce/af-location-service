package com.revature.service;


public interface LocationService {

<<<<<<< HEAD
=======
import com.revature.dto.BuildingDto;
import com.revature.dto.LocationDto;
import com.revature.repository.BuildingRepository;
import com.revature.repository.LocationRepository;

public class LocationService {
	
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	BuildingRepository buildingRepository;
	
	public void createLocation(LocationDto dto) {
		
	}
	
	public List<LocationDto> getAllLocations(){
		return null;
	}
	
	public List<LocationDto> getLocationsByState(String state){
		return null;
	}
	
	public List<LocationDto> getLocationsByCity(String city){
		return null;
	}
	
	public List<LocationDto> getLocationsByZipCode(String zipCode){
		return null;
	}
	
	public LocationDto getLocation( int index) {
		return null;
	}
	
	public void updateState(int index,String state) {
		
	}
	
	public void updateCity(int index, String city) {
		
	}
	
	public void updateZipCode(int index, String zipCode) {
		
	}
	
	public void deleteLocation(int index) {
		
	}
	
	public void addBuilding(int index, BuildingDto buildingDto) {
		
	}
	
	public void updateLocation(int index, LocationDto locationDto) {
		
	}
>>>>>>> 0c4d1e3732fbfea9c5e714a01953b7b38d28bc40
}
