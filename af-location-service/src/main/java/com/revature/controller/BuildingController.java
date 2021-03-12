package com.revature.controller;

import com.revature.dto.BuildingDto;
import com.revature.dto.LocationDetailsDto;
import com.revature.model.Location;
import com.revature.repository.LocationRepository;
import com.revature.repository.RoomRepository;
import com.revature.service.BuildingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.revature.dto.BuildingDetailsDto;
import com.revature.dto.BuildingDto;
import com.revature.dto.BuildingRequestDto;
import com.revature.dto.RoomRequestDto;
import com.revature.model.Room;
import com.revature.service.BuildingService;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;

import java.util.List;


@RestController
@RequestMapping("buildings")
@CrossOrigin
public class BuildingController {

	private BuildingService bs;
	private LocationRepository locationRepository;


	@Autowired
	public BuildingController(BuildingService bs, LocationRepository locationRepository, RoomRepository roomRepository) {

		this.locationRepository = locationRepository;
		this.bs=bs;

	}
	
	@GetMapping(path="/{buildingId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuildingDetailsDto> getBuilding(@PathVariable int buildingId) throws Exception {

		BuildingDetailsDto dto = bs.getBuilding(buildingId);
		return new ResponseEntity<BuildingDetailsDto>(dto, HttpStatus.OK);

	}

	@GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuildingDto>> getBuildings() {

		List<BuildingDto> list = bs.getBuildings();
		return new ResponseEntity<List<BuildingDto>>(list, HttpStatus.OK);

	}

	@GetMapping(path="/city/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuildingDto>> getBuildingByCity(@PathVariable String cityName) {

		List<BuildingDto> list= bs.getBuildingsByCity(cityName);
		return new ResponseEntity<List<BuildingDto>>(list, HttpStatus.OK);

	}

	@GetMapping(path="/street/{streetAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuildingDto>> getBuildingByStreetAddress(@PathVariable String streetAddress) {

		List<BuildingDto> list= bs.getBuildingsByStreetAddress(streetAddress);
		return new ResponseEntity<List<BuildingDto>>(list, HttpStatus.OK);

	}

	@PatchMapping(path="{buildingId}/city/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateBuildingCity(@PathVariable int buildingId, @PathVariable String cityName) throws Exception {

		bs.updateCity(buildingId, cityName);
		return new ResponseEntity(HttpStatus.OK);

	}

	@PatchMapping(path="/{buildingId}/floors/{floorCount}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateNumberOfFloors(@PathVariable int buildingId, @PathVariable int floorCount) throws Exception {

		bs.updateNumberOfFloors(buildingId, floorCount);
		return new ResponseEntity(HttpStatus.OK);

	}

//	@PatchMapping(path="/{buildingId}/room", produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity addRoom(@PathVariable int buildingId, @RequestBody RoomRequestDto dto) throws Exception {
//
//		Room room = new Room();
//		// room.setBuilding must be done in BuildingService
//		room.setCapacity( dto.getCapacity() );
//		room.setFloorNumber( dto.getFloorNumber() );
//		room.setName( dto.getName() );
//		room.setOccupation(dto.getOccupation().equals("Meeting") ? RoomOccupation.MEETING : RoomOccupation.TRAINING);
//		room.setRoomAmenities(dto.getAmenities());
//		room.setRoomId( 0 );
//		room.setType( dto.getType().equals("Physical") ? RoomType.PHYSICAL : (dto.getType().equals("Virtual") ? RoomType.VIRTUAL : RoomType.REMOTE) );
//		return new ResponseEntity(HttpStatus.OK);
//
//	}

//	@DeleteMapping(path="/{buildingId}/room/{roomId}", produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity deleteRoom(@PathVariable int buildingId, @PathVariable int roomId) throws Exception {
//
//		bs.deleteRoom(buildingId, roomId);
//		return new ResponseEntity(HttpStatus.OK);
//	}

	@PatchMapping(path="/{buildingId}")
	public ResponseEntity updateBuilding(@PathVariable int buildingId, @RequestBody BuildingRequestDto dto) throws Exception {

		bs.updateBuilding(buildingId, dto);
		return new ResponseEntity(HttpStatus.OK);
    
  }
	@GetMapping("/locations/{id}/buildings")
	public List<BuildingDto> getBuildingsByLocationId(@PathVariable int id) {
    
		return bs.getBuildingsByLocation(id);

	}

	@PostMapping("/locations/{id}/buildings")
	public ResponseEntity<String> createBuildingForLocation(@PathVariable int id, @RequestBody BuildingRequestDto buildingRequestDto) {

		System.out.println(buildingRequestDto);
		try {

			Location location = locationRepository.findById(id).get();
			bs.createBuilding(buildingRequestDto, location);

		}
		catch(Exception e) {

			return ResponseEntity.badRequest()
					.contentType(MediaType.APPLICATION_JSON)
					.body("{\"message\": \""+ e.getMessage()+"\"}");

		}
		return ResponseEntity.accepted()
				.contentType(MediaType.APPLICATION_JSON)
				.body("{\"message\": \"+Building has been created and added for this location+\"}");

	}
	@DeleteMapping("/{buildingId}/{locationId}")
	public ResponseEntity deleteBuilding(@PathVariable int buildingId, @PathVariable int locationId) throws Exception{
		bs.deleteBuilding(buildingId, locationId);
		return new ResponseEntity(HttpStatus.OK);
	}

}
