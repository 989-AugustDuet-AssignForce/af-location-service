package com.revature.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.dto.BuildingDto;
import com.revature.dto.BuildingDetailsDto;
import com.revature.dto.BuildingRequestDto;
import com.revature.service.BuildingService;


import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class BuildingController {

	@Autowired
	private BuildingService bs;


	
	@GetMapping(path="/buildings/{buildingId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuildingDetailsDto> getBuilding(@PathVariable int buildingId) throws Exception {

		BuildingDetailsDto dto = bs.getBuilding(buildingId);
		return new ResponseEntity<BuildingDetailsDto>(dto, HttpStatus.OK);

	}

	@GetMapping(path="/buildings", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuildingDto>> getBuildings() {

		List<BuildingDto> list = bs.getBuildings();
		return new ResponseEntity<List<BuildingDto>>(list, HttpStatus.OK);

	}

	@GetMapping(path="/buildings/city/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuildingDto>> getBuildingByCity(@PathVariable String cityName) {

		List<BuildingDto> list= bs.getBuildingsByCity(cityName);
		return new ResponseEntity<List<BuildingDto>>(list, HttpStatus.OK);

	}

	@GetMapping(path="/buildings/street/{streetAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuildingDto>> getBuildingByStreetAddress(@PathVariable String streetAddress) {

		List<BuildingDto> list= bs.getBuildingsByStreetAddress(streetAddress);
		return new ResponseEntity<List<BuildingDto>>(list, HttpStatus.OK);

	}

	@PatchMapping(path="/buildings/{buildingId}/city/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateBuildingCity(@PathVariable int buildingId, @PathVariable String cityName) throws Exception {

		bs.updateCity(buildingId, cityName);
		return new ResponseEntity(HttpStatus.OK);

	}

	@PatchMapping(path="/buildings/{buildingId}/floors/{floorCount}", produces=MediaType.APPLICATION_JSON_VALUE)
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

	@PatchMapping(path="/buildings/{buildingId}")
	public ResponseEntity updateBuilding(@PathVariable int buildingId, @RequestBody BuildingRequestDto dto) throws Exception {

		bs.updateBuilding(buildingId, dto);
		return new ResponseEntity(HttpStatus.OK);
    
  }
	@GetMapping("/buildings/locations/{id}/buildings")
	public List<BuildingDto> getBuildingsByLocationId(@PathVariable int id) {
    
		return bs.getBuildingsByLocation(id);

	}


	@PostMapping("/buildings/locations/{id}/buildings")
	public ResponseEntity<String> createBuildingForLocation(@PathVariable int id, @RequestBody BuildingRequestDto buildingRequestDto) {

		try {

			bs.createBuilding(buildingRequestDto, id);

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
	@DeleteMapping("/buildings/{buildingId}/{locationId}")
	public ResponseEntity deleteBuilding(@PathVariable int buildingId, @PathVariable int locationId) throws Exception{
		bs.deleteBuilding(buildingId, locationId);
		return new ResponseEntity(HttpStatus.OK);
	}

}
