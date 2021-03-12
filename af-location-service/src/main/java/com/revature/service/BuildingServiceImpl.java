package com.revature.service;

import com.revature.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.revature.dto.BuildingRequestDto;
import com.revature.model.Building;
import com.revature.model.Location;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.revature.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.BuildingDetailsDto;
import com.revature.dto.BuildingDto;
import com.revature.dto.BuildingRequestDto;
import com.revature.dto.RoomDto;
import com.revature.model.Building;
import com.revature.model.Room;

import com.revature.model.Location;

import com.revature.repository.BuildingRepository;
import com.revature.repository.LocationRepository;



@Service
public class BuildingServiceImpl implements BuildingService{

	private BuildingRepository buildingRepository;
	private LocationRepository locationRepository;
	
	@Autowired
	public BuildingServiceImpl(BuildingRepository repository, LocationRepository locationRepository) {

		this.locationRepository =locationRepository;
		

	@Autowired
	public BuildingServiceImpl( BuildingRepository bd) {
		this.buildingRepository = bd;
	}
	@Autowired
	private LocationRepository locationRepository;
	



	@Override
	public List<BuildingDto> getBuildingsByLocation(int index) {

		List<Building> buildings = buildingRepository.findAllByLocationId(index);
		return buildings.stream().map(building -> {
			BuildingDto buildingDto = new BuildingDto();
			buildingDto.setId(building.getBuildingId());
			buildingDto.setTotalFloors(building.getTotalFloors());
			buildingDto.setNumRooms(building.getRooms().size());
			buildingDto.setStreet_address(building.getStreetAddress());
			return buildingDto;
		}).collect(Collectors.toList());

	}

	@Override
	public void createBuilding(BuildingRequestDto buildingRequestDto, int index) {
		Location location = locationRepository.findById(index).get();
		Building building = new Building();
		building.setStreetAddress(buildingRequestDto.getStreet_address());
		building.setCity(buildingRequestDto.getCity());
		building.setTotalFloors(buildingRequestDto.getTotalFloors());
		building.setLocation(location);
		Building locationBuilding = buildingRepository.save(building);
		location.getBuildings().add(locationBuilding);

	}
	

	@Override
	public List<BuildingDto> getBuildings(){
		List<Building> buildings = buildingRepository.findAll();
		return buildings.stream().map( BuildingServiceImpl::getBuildingDtoFromEntity)
				.collect(Collectors.toList());
	}
	@Override
	public List<BuildingDto> getBuildingsByCity(String city) {
		List<Building> buildings = buildingRepository.findByCity(city);
		return buildings.stream().map( BuildingServiceImpl::getBuildingDtoFromEntity)
				.collect(Collectors.toList());
	}
	@Override
	public List<BuildingDto> getBuildingsByStreetAddress(String address){
		List<Building> buildings = buildingRepository.findByStreetAddress(address);
		return buildings.stream().map( BuildingServiceImpl::getBuildingDtoFromEntity)
				.collect(Collectors.toList());
	}
	@Override
	public BuildingDetailsDto getBuilding(int index) throws Exception{
		Optional<Building> building = buildingRepository.findById(index);
		if(!building.isPresent()) {
			throw new Exception("Building not found");
		}
		return getBuildingDetailsDtoFromEntity(building.get());
	}
	@Override
	public void updateCity(int index, String city) throws Exception{
		cityValidation(city);
		Optional<Building> building = buildingRepository.findById(index);
		if(!building.isPresent()) {
			throw new Exception("Building not found");
		}
		Building entity = building.get();
		entity.setCity(city);
		buildingRepository.save(entity);
		
	}

	
	@Override
	public void updateNumberOfFloors(int index, int floorCount) throws Exception{
		numberOfFloorsValidation(floorCount);
		Optional<Building> building = buildingRepository.findById(index);
		if(!building.isPresent()) {
			throw new Exception("building not found");
		}
		Building entity = building.get();
		entity.setTotalFloors(floorCount);
		buildingRepository.save(entity);
	}
	
//	@Override
//	public void addRoom(int index, Room room) throws Exception{
//		Optional<Building> building= buildingRepository.findById(index);
//		if(!building.isPresent()) {
//			throw new Exception("building not found");
//		}
//		Building entity = building.get();
//		entity.getRooms().add(room);
//		room.setBuilding(entity);
//		buildingRepository.save(entity);
//	}
//	@Override
//	public void deleteRoom(int indexBuilding, int indexRoom) throws Exception{
//		Optional<Building> building = buildingRepository.findById(indexBuilding);
//		if(!building.isPresent()) {
//			throw new Exception("Building not found");
//		}
//		boolean flag = false;
//		Building entity = building.get();
//		List<Room> rooms = entity.getRooms();
//		Optional<Room> roomOptional = rooms.stream().filter( zeRoom -> zeRoom.getRoomId() == indexRoom).findFirst();
//		if(roomOptional.isPresent()) {
//			rooms.remove(roomOptional.get());
//			buildingRepository.save(entity);
//		}else {
//			throw new Exception("room not found");
//		}
//	}
	@Override
	public void updateBuilding(int index, BuildingRequestDto requestDto) throws Exception{
		Optional<Building> building = buildingRepository.findById(index);
		if(!building.isPresent()) {
			throw new Exception("building not found");
		}
		Building entity = building.get();
		entity.setCity( requestDto.getCity() );
		entity.setStreetAddress( requestDto.getStreet_address() );
		entity.setTotalFloors( requestDto.getTotalFloors() );
		//TODO update zip code borked, doesn't propagate properly without location repository
		buildingRepository.save( entity );
	}
	@Override
	public void deleteBuilding(int buildingIndex, int locationIndex) throws Exception{
		Optional<Location> location = locationRepository.findById(locationIndex);
		System.out.println(location.isPresent());
		if(!location.isPresent()) {
			throw new Exception("Location not found");
		}
		final boolean[] flag = {false};
		final Location entity = location.get();
		final List<Building> buildings = entity.getBuildings();
		buildings.stream().filter( building->{
			if(building.getBuildingId() == buildingIndex) {
				buildings.remove(building);
				flag[0] = true;
				return true;
				
			}
			return false;
		}).findFirst();
		if(flag[0]) {
			locationRepository.save(entity);
		}else {
			throw new Exception("Building not found");
		}
	}
	//utility functions
	
	private static BuildingDto getBuildingDtoFromEntity(Building building) {
		BuildingDto dto = new BuildingDto();
		dto.setId(building.getBuildingId());
		dto.setNumRooms(building.getRooms().size());
		dto.setTotalFloors(building.getTotalFloors());
		dto.setStreet_address( building.getStreetAddress() );
		return dto;
	}
	private static BuildingDetailsDto getBuildingDetailsDtoFromEntity(Building building) {
		BuildingDetailsDto dto = new BuildingDetailsDto();
		dto.setCity( building.getCity() );
		dto.setId( building.getBuildingId() );
		dto.setStreet_address( building.getStreetAddress() );
		dto.setTotalFloors( building.getTotalFloors() );
		dto.setZipCode( building.getLocation().getZipCode());
		dto.setRooms( getRoomsDtoListFromEntityList(building.getRooms()));
		return dto;
	}
	private static BuildingRequestDto getBuildingRequestDtoFromEntity(Building building) {
		BuildingRequestDto dto = new BuildingRequestDto();
		dto.setCity( building.getCity() );
		dto.setStreet_address( building.getStreetAddress() );
		dto.setTotalFloors( building.getTotalFloors() );
		dto.setZipCode( building.getLocation().getZipCode() );
		return dto;
		
	}
	private static List<RoomDto> getRoomsDtoListFromEntityList(List<Room> rooms){
		return rooms.stream().map( room -> {
			RoomDto dto = new RoomDto();
			dto.setId( room.getRoomId() );
			dto.setOccupation( room.getOccupation().toString());
			dto.setType( room.getType().toString());
			return dto;
			}).collect(Collectors.toList());
	}
	private static void buildingValidation(Building building) throws Exception{
		if(building.getBuildingId() !=0) {
			throw new Exception("id prefilled");
		}
	}
	private static void zipCodeValidation(String zipCode) throws Exception{
		if(false) {
			throw new Exception("invalid zip code");
		}
	}
	private static void cityValidation(String city) throws Exception{
		if(false) {
			throw new Exception("city validation failed");
		}
	}
	private static void numberOfFloorsValidation(int count) throws Exception{
		if( false) {
			throw new Exception("floor count validation failed");
		}
	}
	
}
