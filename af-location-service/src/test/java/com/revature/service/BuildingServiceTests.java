package com.revature.service;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.revature.repository.LocationRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.revature.dto.BuildingDetailsDto;
import com.revature.dto.BuildingDto;
import com.revature.dto.BuildingRequestDto;
import com.revature.dto.LocationDto;
import com.revature.dto.RoomDto;
import com.revature.model.Building;
import com.revature.model.Location;
import com.revature.model.Room;
import com.revature.repository.BuildingRepository;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class BuildingServiceTests {
  
	public static BuildingService buildingService;
	public static BuildingRepository buildingRepository;
	public static LocationRepository locationRepository;
	public static Location goodSampleLocation;
	public static Location badSampleLocation;
	public static Building goodBuilding;
	public static Building badBuilding;
	public static Building otherGoodBuilding;
	public static Room goodRoom;

	@BeforeClass
	public static void setup() {
		//instantiate service
		buildingRepository = Mockito.mock(BuildingRepository.class);
		locationRepository = Mockito.mock(LocationRepository.class);
		buildingService = new BuildingServiceImpl(buildingRepository,locationRepository);
		
		//instantiate location
		goodSampleLocation = new Location();
		badSampleLocation = new Location();
		goodSampleLocation.setCity( "Miami" );
		badSampleLocation.setCity( "Austin" );
		goodSampleLocation.setLocationId(3);
		badSampleLocation.setLocationId(2);
		goodSampleLocation.setState("FL");
		badSampleLocation.setState("CA");
		goodSampleLocation.setZipCode("33187");
		badSampleLocation.setZipCode("AAAA");
		//instantiate building list
		List<Building> goodBuildings = new ArrayList<Building>();
		List<Building> badBuildings = new ArrayList<Building>();
		goodSampleLocation.setBuildings(goodBuildings);
		badSampleLocation.setBuildings(badBuildings);
		// instantiate buildings
		goodBuilding = new Building();
		goodBuilding.setCity("Miami");
		goodBuilding.setBuildingId(23);
		goodBuilding.setLocation(goodSampleLocation);
		goodBuilding.setStreetAddress("Main Street");
		goodBuilding.setTotalFloors(1);
		otherGoodBuilding = new Building();
		otherGoodBuilding.setCity("Miami");
		otherGoodBuilding.setBuildingId(24);
		otherGoodBuilding.setLocation(goodSampleLocation);
		otherGoodBuilding.setStreetAddress("Second Street");
		otherGoodBuilding.setTotalFloors(1);
		goodSampleLocation.setBuildings(goodBuildings);
		badBuilding = new Building();
		badBuilding.setCity("Austin");
		badBuilding.setBuildingId(25);
		badBuilding.setLocation(badSampleLocation);
		badBuilding.setStreetAddress("Main Street");
		badBuilding.setTotalFloors(0);
		Building otherBadBuilding = new Building();
		otherBadBuilding.setCity("Austin");
		otherBadBuilding.setBuildingId(26);
		otherBadBuilding.setLocation(badSampleLocation);
		otherBadBuilding.setStreetAddress("Main Street");
		otherBadBuilding.setTotalFloors(0);
		goodBuildings.add(goodBuilding);
		goodBuildings.add(otherGoodBuilding);
		badBuildings.add(badBuilding);
		badBuildings.add(otherBadBuilding);
		//instantiate rooms

		List<Room> goodRooms = new ArrayList<Room>();
		List<Room> otherGoodRooms = new ArrayList<Room>();
		goodBuilding.setRooms(goodRooms);
		otherGoodBuilding.setRooms(otherGoodRooms);
		goodRoom = new Room();
		goodRoom.setBuilding(goodBuilding);
		goodRoom.setCapacity(3);
		goodRoom.setRoomId(7);
		goodRoom.setType(RoomType.PHYSICAL);
		goodRoom.setName("James");
		goodRoom.setOccupation(RoomOccupation.MEETING);
		Set<String> ammenities = new HashSet<String>();
		ammenities.add("gray");
		ammenities.add("pool");
		ammenities.add("surround sound");
		goodRoom.setRoomAmenities(ammenities);
		Room goodRoom2 = new Room();
		goodRoom2.setBuilding(goodBuilding);
		goodRoom2.setCapacity(4);
		goodRoom2.setRoomId(8);
		goodRoom2.setType(RoomType.PHYSICAL);
		goodRoom2.setName("Steven");
		goodRoom2.setOccupation(RoomOccupation.MEETING);
		goodRoom2.setRoomAmenities(ammenities);
		Room goodRoom3 = new Room();
		goodRoom3.setBuilding(otherGoodBuilding);
		goodRoom3.setCapacity(5);
		goodRoom3.setRoomId(9);
		goodRoom3.setType(RoomType.PHYSICAL);
		goodRoom3.setName("Pearl");
		goodRoom3.setOccupation(RoomOccupation.MEETING);
		goodRoom3.setRoomAmenities(ammenities);
		Room goodRoom4 = new Room();
		goodRoom4.setBuilding(otherGoodBuilding);
		goodRoom4.setCapacity(6);
		goodRoom4.setRoomId(10);
		goodRoom4.setType(RoomType.PHYSICAL);
		goodRoom4.setName("Walter");
		goodRoom4.setOccupation(RoomOccupation.MEETING);
		goodRoom4.setRoomAmenities(ammenities);
		goodRooms.add(goodRoom);
		goodRooms.add(goodRoom2);
		otherGoodRooms.add(goodRoom3);
		otherGoodRooms.add(goodRoom4);

		List<Room> badRooms = new ArrayList<Room>();
		List<Room> otherBadRooms = new ArrayList<Room>();
		badBuilding.setRooms(badRooms);
		otherBadBuilding.setRooms(otherBadRooms);
		Room badRoom = new Room();
		badRoom.setBuilding(badBuilding);
		badRoom.setCapacity(7);
		badRoom.setRoomId(11);
		badRoom.setType(RoomType.PHYSICAL);
		badRoom.setName("Blathers");
		badRoom.setOccupation(RoomOccupation.MEETING);
		badRoom.setRoomAmenities(ammenities);
		Room badRoom2 = new Room();
		badRoom2.setBuilding(badBuilding);
		badRoom2.setCapacity(8);
		badRoom2.setRoomId(12);
		badRoom2.setType(RoomType.PHYSICAL);
		badRoom2.setName("Garnet");
		badRoom2.setOccupation(RoomOccupation.MEETING);
		badRoom2.setRoomAmenities(ammenities);
		Room badRoom3 = new Room();
		badRoom3.setBuilding(otherBadBuilding);
		badRoom3.setCapacity(9);
		badRoom3.setRoomId(13);
		badRoom3.setType(RoomType.PHYSICAL);
		badRoom3.setName("Amethyst");
		badRoom3.setOccupation(RoomOccupation.MEETING);
		badRoom3.setRoomAmenities(ammenities);
		Room badRoom4 = new Room();
		badRoom4.setBuilding(otherBadBuilding);
		badRoom4.setCapacity(10);
		badRoom4.setRoomId(14);
		badRoom4.setType(RoomType.PHYSICAL);
		badRoom4.setName("Bulbasaur");
		badRoom4.setOccupation(RoomOccupation.MEETING);
		badRoom4.setRoomAmenities(ammenities);
		badRooms.add(badRoom);
		badRooms.add(badRoom2);
		otherBadRooms.add(badRoom3);
		otherBadRooms.add(badRoom4);
		}

	
	@Test
	public void updateBuildingGood() {
		final Building goodSampleCopy = cloneBuilding( goodBuilding);
		Building modifiedSampleCopy = cloneBuilding( goodSampleCopy);
		modifiedSampleCopy.setCity("wuzz");
		BuildingDetailsDto modifiedDto = getBuildingDetailsDtoFromEntity( modifiedSampleCopy);
		// TODO check validation
		Mockito.when( buildingRepository.save( Mockito.any( Building.class ) ) ).thenAnswer(new Answer<Building>() {
			@Override
			public Building answer(InvocationOnMock invocation) {
				Building building = invocation.getArgument(0, Building.class);
				if(building.getBuildingId() == goodSampleCopy.getBuildingId() ) {
					goodSampleCopy.setCity( building.getCity() );
					goodSampleCopy.setLocation( building.getLocation() );
					goodSampleCopy.setRooms( building.getRooms() );
					goodSampleCopy.setStreetAddress( building.getStreetAddress() );
					goodSampleCopy.setTotalFloors( building.getTotalFloors());
				}
				return building;
			}
		});
		Mockito.when( buildingRepository.findById( Mockito.anyInt() ) ).thenAnswer(new Answer<Optional<Building>>() {
			@Override
			public Optional<Building> answer(InvocationOnMock invocation) {
				int index = invocation.getArgument(0);
				if(index == goodSampleCopy.getBuildingId()) {
					return Optional.of(goodSampleCopy);
				}
				return Optional.empty();
			}
		});
		BuildingRequestDto request = new BuildingRequestDto();
		request.setCity( modifiedSampleCopy.getCity() );
		request.setStreet_address( modifiedSampleCopy.getStreetAddress() );
		request.setTotalFloors( modifiedSampleCopy.getTotalFloors() );
		request.setZipCode( modifiedSampleCopy.getLocation().getZipCode() );	
		try{
			buildingService.updateBuilding(modifiedSampleCopy.getBuildingId(), request);
			BuildingDetailsDto dto = buildingService.getBuilding(goodBuilding.getBuildingId());
			assertTrue( "data did not persist", buildingDetailsDtoEquals(modifiedDto, dto ) );
		}catch(Exception e) {
			Assert.fail("Exception thrown");
		}
	}
	
	@Test
	public void updateBuildingBad() {
		
		BuildingRequestDto request = new BuildingRequestDto();
		request.setCity( goodBuilding.getCity() );
		request.setStreet_address( goodBuilding.getStreetAddress() );
		request.setTotalFloors( goodBuilding.getTotalFloors() );
		// TODO check validation rules
		request.setZipCode( "junk");
		Mockito.when(buildingRepository.findById(0)).thenReturn(Optional.empty());
		assertThrows(Exception.class, ()->{
			buildingService.updateBuilding(0, request);
		});
	}
	
	@Test
	public void getBuildings() {
		try{
			List<Building> buildings = new ArrayList<Building>();
		
		buildings.add(goodBuilding);
		buildings.add(otherGoodBuilding);
		List<BuildingDto> buildingsDto = getBuidlingDtoListFromEntityList(buildings);
		Mockito.when(buildingRepository.findAll() ).thenReturn(buildings);
		List<BuildingDto> result = buildingService.getBuildings();
		if(buildingsDto.size() != result.size() ) {
			Assert.fail("dto's don't match");
		}
		Iterator<BuildingDto> iteratorA = result.iterator();
		Iterator<BuildingDto> iteratorB = buildingsDto.iterator();
		while( iteratorA.hasNext() ) {
			BuildingDto a = iteratorA.next();
			BuildingDto b = iteratorB.next();
			if( ! buildingDtoEquals(a, b) ) {
				Assert.fail("dto's don't match");
			}
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void getBuildingsByCity() {
		
		List<Building> buildings = new ArrayList<Building>();
		buildings.add(goodBuilding);
		buildings.add(otherGoodBuilding);
		List<BuildingDto> buildingsDto = getBuidlingDtoListFromEntityList(buildings);
		Mockito.when(buildingRepository.findByCity("Miami") ).thenReturn(buildings);
		List<BuildingDto> result = buildingService.getBuildingsByCity("Miami");
		if(buildingsDto.size() != result.size() ) {
			Assert.fail("dto's don't match");
		}
		Iterator<BuildingDto> iteratorA = result.iterator();
		Iterator<BuildingDto> iteratorB = buildingsDto.iterator();
		while( iteratorA.hasNext() ) {
			BuildingDto a = iteratorA.next();
			BuildingDto b = iteratorB.next();
			if( ! buildingDtoEquals(a, b) ) {
				Assert.fail("dto's don't match");
			}
		}
	}
	
	@Test
	public void getBuildlingsByStreetAddress() {
		List<Building> buildings = new ArrayList<Building>();
		buildings.add(goodBuilding);
		List<BuildingDto> buildingsDto = getBuidlingDtoListFromEntityList(buildings);
		Mockito.when(buildingRepository.findByStreetAddress("Main Street") ).thenReturn(buildings);
		List<BuildingDto> result = buildingService.getBuildingsByStreetAddress("Main Street");
		if(buildingsDto.size() != result.size() ) {
			Assert.fail("dto's don't match");
		}
		Iterator<BuildingDto> iteratorA = result.iterator();
		Iterator<BuildingDto> iteratorB = buildingsDto.iterator();
		while( iteratorA.hasNext() ) {
			BuildingDto a = iteratorA.next();
			BuildingDto b = iteratorB.next();
			if( ! buildingDtoEquals(a, b) ) {
				Assert.fail("dto's don't match");
			}
		}
		
	}
	
	@Test
	public void getBuildingById() {
		Mockito.when( buildingRepository.findById( goodBuilding.getBuildingId() ) ).thenReturn(Optional.of(goodBuilding ) );
		BuildingDetailsDto dto = getBuildingDetailsDtoFromEntity( goodBuilding );
		try{
			assertTrue("dto not equal", buildingDetailsDtoEquals(buildingService.getBuilding(goodBuilding.getBuildingId()), dto) );
		}catch(Exception e) {
			Assert.fail("exception thrown");
		}
		
	}
	
	@Test
	public void updateCity() {
		final Building goodSampleCopy = cloneBuilding( goodBuilding);
		Building modifiedSampleCopy = cloneBuilding( goodSampleCopy);
		modifiedSampleCopy.setCity("wuzz");
		BuildingDetailsDto modifiedDto = getBuildingDetailsDtoFromEntity( modifiedSampleCopy);
		// TODO check validation
		Mockito.when( buildingRepository.save( Mockito.any( Building.class ) ) ).thenAnswer(new Answer<Building>() {
			@Override
			public Building answer(InvocationOnMock invocation) {
				Building building = invocation.getArgument(0, Building.class);
				if(building.getBuildingId() == goodSampleCopy.getBuildingId() ) {
					goodSampleCopy.setCity( building.getCity() );
					goodSampleCopy.setLocation( building.getLocation() );
					goodSampleCopy.setRooms( building.getRooms() );
					goodSampleCopy.setStreetAddress( building.getStreetAddress() );
					goodSampleCopy.setTotalFloors( building.getTotalFloors());
				}
				return building;
			}
		});
		Mockito.when( buildingRepository.findById( goodSampleCopy.getBuildingId() ) ).thenReturn(Optional.of( goodSampleCopy ) );
			
		try{
			buildingService.updateCity(modifiedSampleCopy.getBuildingId(), "wuzz");
				assertTrue( "data did not persist", buildingDetailsDtoEquals(modifiedDto, buildingService.getBuilding( goodBuilding.getBuildingId() ) ) );
		} catch(Exception e) {
			Assert.fail("Exception thrown");
		};
	}
	@Test
	public void updateNumberOfFloors() {
		final Building goodSampleCopy = cloneBuilding( goodBuilding);
		Building modifiedSampleCopy = cloneBuilding( goodSampleCopy);
		modifiedSampleCopy.setTotalFloors(3);
		BuildingDetailsDto modifiedDto = getBuildingDetailsDtoFromEntity( modifiedSampleCopy);
		// TODO check validation
		Mockito.when( buildingRepository.save( Mockito.any( Building.class ) ) ).thenAnswer(new Answer<Building>() {
			@Override
			public Building answer(InvocationOnMock invocation) {
				Building building = invocation.getArgument(0, Building.class);
				if(building.getBuildingId() == goodSampleCopy.getBuildingId() ) {
					goodSampleCopy.setCity( building.getCity() );
					goodSampleCopy.setLocation( building.getLocation() );
					goodSampleCopy.setRooms( building.getRooms() );
					goodSampleCopy.setStreetAddress( building.getStreetAddress() );
					goodSampleCopy.setTotalFloors( building.getTotalFloors());
				}
				return building;
			}
		});
		Mockito.when( buildingRepository.findById( goodSampleCopy.getBuildingId() ) ).thenReturn(Optional.of( goodSampleCopy ) );
			
	try{
			buildingService.updateNumberOfFloors(goodBuilding.getBuildingId(), 3);
				assertTrue( "data did not persist", buildingDetailsDtoEquals(modifiedDto, buildingService.getBuilding( goodBuilding.getBuildingId() ) ) );
		} catch(Exception e) {
			Assert.fail("exception thrown");
		};
	}
	
//	@Test
//	public void addRoom() {
//		final Building sampleBuilding = cloneBuilding(goodBuilding);
//		final Room newRoom = cloneRoom(goodRoom, sampleBuilding);
//		newRoom.setRoomId(newRoom.getRoomId()+1000);
//		final boolean[] flag = {false};
//		newRoom.setBuilding(sampleBuilding);
//		Mockito.doAnswer(new Answer<Void>() {
//			@Override
//			public Void answer( InvocationOnMock invocation ) {
//				Building building = invocation.getArgument(0,Building.class);
//				Optional<Room> foundRoom =building.getRooms().stream().filter(room -> room == newRoom).findFirst();
//				if(foundRoom.isPresent()) {
//					flag[0]= true;
//				}
//				return null;
//			}
//		}).when(buildingRepository).save(Mockito.any(Building.class));
//		try {
//			buildingService.addRoom(goodBuilding.getBuildingId(), newRoom);
//		}catch(Exception e) {
//			e.printStackTrace();
//			Assert.fail("exception thrown");
//		}
//		assertTrue("room save not tripped", flag[0]);
//	}
//@Test
//public void deleteRoom() {
//	final Building sampleBuilding = cloneBuilding(goodBuilding);
//	final Room deleteRoom = sampleBuilding.getRooms().get(0);
//	final boolean[] flag = {true};
//	Mockito.doAnswer(new Answer<Void>() {
//		@Override
//		public Void answer( InvocationOnMock invocation ) {
//			Building building = invocation.getArgument(0,Building.class);
//			if(building.getBuildingId() == sampleBuilding.getBuildingId()) {
//				Iterator<Room> iterator = building.getRooms().iterator();
//				while(iterator.hasNext()) {
//					Room room = iterator.next();
//					if( room.getRoomId() == deleteRoom.getRoomId() ) {
//						flag[0] = false;
//						break;
//					}
//				}
//
//			}
//			return null;
//		}
//	}).when(buildingRepository).save(Mockito.any(Building.class));
//	try {
//		buildingService.deleteRoom(goodBuilding.getBuildingId(),deleteRoom.getRoomId());
//	}catch(Exception e) {
//		Assert.fail("exception thrown");
//	}
//	assertTrue("deleted room found", flag[0]);
//}
	
	//utility functions
	
	private Room cloneRoom( Room room, Building building) {
		Room result = new Room();
		result.setBuilding( building);
		result.setCapacity( room.getCapacity() );
		result.setFloorNumber( room.getFloorNumber() );
		result.setRoomId( room.getRoomId());
		result.setName( room.getName() );
		result.setOccupation( room.getOccupation() );
		result.setType( room.getType() );
		Set<String> set = new HashSet<String>();
		Iterator<String> iterator = room.getRoomAmenities().iterator();
		while(iterator.hasNext()) {
			set.add(iterator.next());
		}
		result.setRoomAmenities(set);
		return result;
				
	}
	private Building cloneBuilding (Building building ) {
		Building result = new Building();
		result.setBuildingId( building.getBuildingId());
		result.setCity( building.getCity());
		result.setLocation(  building.getLocation() );
		result.setStreetAddress(  building.getStreetAddress());
		List<Room> list = new ArrayList<Room>();
		result.setRooms(list);
		Iterator<Room> iterator = building.getRooms().iterator();
		while(iterator.hasNext()) {
			list.add( cloneRoom( iterator.next(), result ));
		}
		
		return result;
		
	}
	private Location cloneLocation( Location location ) {
		Location result = new Location();
		result.setCity( location.getCity() );
		result.setLocationId( location.getLocationId() +1000 );
		result.setState( location.getState() );
		result.setZipCode( location.getZipCode() );
		List<Building> list = new ArrayList<Building>();
		Iterator<Building> iterator = location.getBuildings().iterator();
		while( iterator.hasNext() ) {
			list.add( cloneBuilding(iterator.next() ) );
		}
		result.setBuildings( list );
		return result;
	}
	
	private boolean locationDtoEquals( LocationDto locationA, LocationDto locationB ) {
		if( locationA == locationB ) {
			return true;
		}
		if( locationA.getId() != locationB.getId() ) {
			return false;
		}
		if( !locationA.getCity().equals( locationB.getCity() ) ) {
			return false;
		}
		if( !locationA.getState().equals( locationB.getState() ) ) {
			return false;
		}
		if( !locationA.getZipCode().equals( locationB.getZipCode() ) ) {
			return false;
		}
		if( locationA.getNumBuildings() != locationB.getNumBuildings() ) {
			return false;
		}
		
		return true;
		
	}
	private boolean roomDtoEquals( RoomDto roomA, RoomDto roomB) {
		if( roomA == roomB ) {
			return true;
		}
		if( roomA.getId() != roomB.getId() ) {
			return false;
		}
		
		if( !roomA.getOccupation().equals( roomB.getOccupation() ) ) {
			return false;
		}
		if( !roomA.getType().equals( roomB.getType() ) ) {
			return false;
		}
		
		
		return true;
	}
	
	private boolean buildingDtoEquals( BuildingDto buildingA, BuildingDto buildingB ) {
		
		if( buildingA == buildingB ) {
			return true;
		}
		if(buildingA ==null || buildingB == null) {
			return false;
		}
		
		if(  buildingA.getNumRooms() != buildingB.getNumRooms() ) {
			return false;
		}
		if( !buildingA.getStreet_address().equals( buildingB.getStreet_address() ) ) {
			return false;
		}
		if( buildingA.getId() != buildingB.getId() ) {
			return false;
		}
		if( buildingA.getTotalFloors() != buildingB.getTotalFloors() ) {
			return false;
		}
		
		return true;
	}
	private boolean buildingDetailsDtoEquals( BuildingDetailsDto buildingA, BuildingDetailsDto buildingB ) {
		if( buildingA == buildingB) {
 			return true;
		}
 		if( !buildingA.getCity().equals( buildingB.getCity() ) ) {
 			return false;
		}
		if( buildingA.getId() != buildingB.getId() ) {
 			return false;
		}
		if( !buildingA.getStreet_address().equals( buildingB.getStreet_address() ) ) {
 			return false;
		}
		if( buildingA.getTotalFloors() != buildingB.getTotalFloors() ) {
 			return false;
		}
		if( !buildingA.getZipCode().equals( buildingB.getZipCode() ) ) {
 			return false;
		}
		if( buildingA.getRooms().size() != buildingB.getRooms().size() ) {
 			return false;
		}
		if( !roomDtoListEquals( buildingA.getRooms(), buildingB.getRooms() )) {
 			return false;
		}
		return true;
		
	}
	public boolean roomDtoListEquals( List<RoomDto> roomsA, List<RoomDto> roomsB) {
		Iterator<RoomDto> iteratorRoomA = roomsA.iterator();
		Iterator<RoomDto> iteratorRoomB = roomsB.iterator();
		while(iteratorRoomA.hasNext()) {
			RoomDto roomA = iteratorRoomA.next();
			RoomDto roomB = iteratorRoomB.next();
			if( roomA.getId() != roomB.getId() ) {
				return false;
			}
			if( !roomA.getOccupation().equals(roomB.getOccupation() ) ) {
				return false;
			}
			if( !roomA.getType().equals(roomB.getType() ) ) {
				return false;
			}
		}
		return true;
	}
	
	private LocationDto getLocationDtoFromEntity( Location location ) {
		LocationDto locationDto = new LocationDto();
		locationDto.setCity( location.getCity() );
		locationDto.setId( location.getLocationId() );
		locationDto.setState( location.getState() );
		locationDto.setZipCode( location.getZipCode() );
		locationDto.setNumBuildings( location.getBuildings().size() );
		return locationDto;
	}
	private BuildingDto getBuildingDtoFromEntity( Building building ) {
		BuildingDto resultBuilding = new BuildingDto();
		resultBuilding.setId( building.getBuildingId() );
		resultBuilding.setStreet_address( building.getStreetAddress() );
		// TODO implement totalFloors
		resultBuilding.setTotalFloors( 1 );
 		resultBuilding.setNumRooms( building.getRooms().size() );
		return resultBuilding;
		
	}
	private List<BuildingDto>   getBuidlingDtoListFromEntityList( List<Building> buildings ){
		List<BuildingDto> result = new ArrayList<BuildingDto>();
 		Iterator<Building> iterator = buildings.iterator();
		while( iterator.hasNext() ) {
			BuildingDto resultBuilding = new BuildingDto();
			Building building = iterator.next();
 			resultBuilding = getBuildingDtoFromEntity( building );
			result.add( resultBuilding );
		}
		return result;
	}
	private List<RoomDto> getRoomsDtoListFromEntityList( List<Room> rooms ){
		List<RoomDto> result = new ArrayList<RoomDto>();
		Iterator<Room> iterator = rooms.iterator();
		while( iterator.hasNext() ) {
			RoomDto resultRoom = new RoomDto();
			Room room = iterator.next();
			resultRoom.setId( room.getRoomId() );
			resultRoom.setOccupation( room.getOccupation().toString());
			resultRoom.setType( room.getType().toString() );
			result.add( resultRoom );
		}
		return result;
	}
	
	private BuildingDetailsDto getBuildingDetailsDtoFromEntity(Building building) {
		BuildingDetailsDto result = new BuildingDetailsDto();
		result.setCity( building.getCity() );
		result.setId( building.getBuildingId() );
		result.setStreet_address( building.getStreetAddress() );
		result.setTotalFloors( building.getTotalFloors() );
		result.setZipCode( building.getLocation().getZipCode() );
		result.setRooms( getRoomDtoListFromRoomList( building.getRooms() ) );
		return result;
		
	}
	private List<RoomDto> getRoomDtoListFromRoomList( List<Room> rooms ){
		List<RoomDto> result = new ArrayList<RoomDto>();
		Iterator<Room> iterator = rooms.iterator();
		while(iterator.hasNext()) {
			RoomDto roomDto = new RoomDto();
			Room room = iterator.next();
			roomDto.setId( room.getRoomId() );
			roomDto.setOccupation( room.getOccupation().toString() );
			roomDto.setType( room.getType().toString() );
			result.add(roomDto);
		}
		return result;
		
	}
	private boolean equalsBuildingRequestDtoAndBuildingEntity(BuildingRequestDto dto, Building entity) {
 		if(!entity.getCity().equals(dto.getCity())) {
			return false;
		}
		// TODO zip code assertion kind of borked;
		if(dto.getTotalFloors() != entity.getTotalFloors() ) {
			return false;
		}
		if(!dto.getStreet_address().equals(entity.getStreetAddress())) {
			return false;
		}
		return true;
	}
	private BuildingRequestDto getBuildingRequestDtoFromEntity(Building building) {
		BuildingRequestDto dto = new BuildingRequestDto();
		dto.setCity( building.getCity() );
		dto.setStreet_address( building.getStreetAddress() );
		dto.setTotalFloors( building.getTotalFloors() );
		dto.setZipCode( building.getLocation().getZipCode() );
		return dto;
	}
  
}
