package com.revature.controller;

import com.revature.dto.*;
import com.revature.service.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.model.Building;
import com.revature.service.LocationService;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LocationController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private LocationServiceImpl locationServiceImpl;

	@PostMapping("/locations")
	public ResponseEntity<String> createLocation(@RequestBody LocationRequestDto locationRequestDto){

		try{
			locationService.createLocation(locationRequestDto);
		}
		catch(HttpClientErrorException.BadRequest badRequest){
			return ResponseEntity.badRequest()
					.contentType(MediaType.APPLICATION_JSON)
					.body("{\"message\": \""+badRequest.getMessage()+"\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.accepted()
				.contentType(MediaType.APPLICATION_JSON)
				.body("{\"message\": \"+Location created successfully+\"}");

	}

	@GetMapping("/locations")
	public ResponseEntity<List<LocationDto>> getLocations() {

		List<LocationDto> locations = locationService.getAllLocations();
		return ResponseEntity.ok(locations);

	}

	@PatchMapping("/locations/{id}/updateState")
	public ResponseEntity<String> updateState(@PathVariable int id, @RequestParam String state){

		try{
			locationService.updateState(id, state);
		}
		catch(Exception e){

			return ResponseEntity.badRequest()
					.contentType(MediaType.APPLICATION_JSON)
					.body("{\"message\": \""+ e.getMessage()+"\"}");

		}
		return ResponseEntity.accepted()
				.contentType(MediaType.APPLICATION_JSON)
				.body("{\"message\": \"+Location's State has been updated successfully+\"}");

	}

	@PatchMapping("/locations/{id}/updateCity")
	public ResponseEntity<String> updateCity(@PathVariable int id, @RequestParam String city){
		try {

			locationService.updateCity(id,city);

		}
		catch(Exception e){

			return ResponseEntity.badRequest()
					.contentType(MediaType.APPLICATION_JSON)
					.body("{\"message\": \""+ e.getMessage()+"\"}");

		}
		return ResponseEntity.accepted()
				.contentType(MediaType.APPLICATION_JSON)
				.body("{\"message\": \"+Location's City has been updated successfully+\"}");

	}

	@PatchMapping("/locations/{id}/updateZipCode")
	public ResponseEntity<String> updateZipCode(@PathVariable int id, @RequestParam String zipCode) {

		try{
			locationService.updateZipCode(id,zipCode);
		}
		catch(Exception e){

			return ResponseEntity.badRequest()
					.contentType(MediaType.APPLICATION_JSON)
					.body("{\"message\": \""+ e.getMessage()+"\"}");

		}
		return ResponseEntity.accepted()
				.contentType(MediaType.APPLICATION_JSON)
				.body("{\"message\": \"+Location's Zipcode has been updated successfully+\"}");

	}

	@DeleteMapping("/locations/{id}")
	public ResponseEntity<String> deleteLocation(@PathVariable int id){

		try {

			locationService.deleteLocation(id);

		}
		catch(Exception e){

			return ResponseEntity.badRequest()
					.contentType(MediaType.APPLICATION_JSON)
					.body("{\"message\": \""+ e.getMessage()+"\"}");

		}
		return ResponseEntity.accepted()
				.contentType(MediaType.APPLICATION_JSON)
				.body("{\"message\": \"+Location has been deleted successfully+\"}");

	}

	@PutMapping("/locations/{id}")
	public ResponseEntity<String> updateLocation(@PathVariable int id, @RequestBody LocationRequestDto locationRequestDto) {

		try {
			locationService.updateLocation(id, locationRequestDto);
		}
		catch(Exception e){

			return ResponseEntity.badRequest()
					.contentType(MediaType.APPLICATION_JSON)
					.body("{\"message\": \""+ e.getMessage()+"\"}");

		}
		return ResponseEntity.accepted()
				.contentType(MediaType.APPLICATION_JSON)
				.body("{\"message\": \"+Location has been updated successfully+\"}");

	}

	@GetMapping("/locations/state/{state}")
	public List<LocationDto> getLocationsByState(@PathVariable String state) {
		return locationServiceImpl.getLocationsByState(state);
	}

	@GetMapping("/locations/city/{city}")
	public List<LocationDto> getLocationsByCity(@PathVariable String city) {
		return locationServiceImpl.getLocationsByCity(city);
	}

	@GetMapping("/locations/zip/{zipCode}")
	public List<LocationDto> getLocationsByZipCode(@PathVariable String zipCode) {
		return locationServiceImpl.getLocationsByZipCode(zipCode);
	}

	@GetMapping("/locations/id/{index}")
	public LocationDetailsDto getLocationById(@PathVariable int index) {

		return locationServiceImpl.getLocation(index);

	}

}


