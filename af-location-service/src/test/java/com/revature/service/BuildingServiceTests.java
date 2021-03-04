package com.revature.service;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.dto.BuildingDetailsDto;
import com.revature.dto.BuildingDto;
import com.revature.dto.BuildingRequestDto;
import com.revature.dto.LocationDto;
import com.revature.dto.RoomDto;
import com.revature.model.Building;
import com.revature.model.Location;
import com.revature.model.Room;
import com.revature.repository.BuildingRepository;
import com.revature.repository.RoomRepository;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class BuildingServiceTests {
	
	
	@Autowired
	BuildingService buildingService;
	LocationService locationRepository = Mockito.mock( LocationService.class );
	RoomService roomService = Mockito.mock( RoomService.class );
	BuildingRepository buildingRepository = Mockito.mock( BuildingRepository.class );
	RoomRepository roomRepository = Mockito.mock( RoomRepository.class);
	public static Location goodSampleLocation;
	public static Location badSampleLocation;
	public static Building goodBuilding;
	public static Building badBuilding;
	public static Building otherGoodBuilding;
	public static Room goodRoom;

	@BeforeClass
	public static void setup() {
		//instantiate location
		goodSampleLocation = new Location();
		badSampleLocation = new Location();
		goodSampleLocation.setCity( "Miami" );
		badSampleLocation.setCity( "Austin" );
		goodSampleLocation.setId(3);
		badSampleLocation.setId(2);
		goodSampleLocation.setState("FL");
		badSampleLocation.setState("CA");
		//instantiate building list
		List<Building> goodBuildings = new ArrayList<Building>();
		List<Building> badBuildings = new ArrayList<Building>();
		goodSampleLocation.setBuildings(goodBuildings);
		badSampleLocation.setBuildings(badBuildings);
		// instantiate buildings
		goodBuilding = new Building();
		goodBuilding.setCity("Miami");
		goodBuilding.setId(23);
		goodBuilding.setLocation(goodSampleLocation);
		goodBuilding.setStreetAddress("Main Street");
		goodBuilding.setTotalFloors(1);
		otherGoodBuilding = new Building();
		otherGoodBuilding.setCity("Miami");
		otherGoodBuilding.setId(24);
		otherGoodBuilding.setLocation(goodSampleLocation);
		otherGoodBuilding.setStreetAddress("Second Street");
		otherGoodBuilding.setTotalFloors(1);
		goodSampleLocation.setBuildings(goodBuildings);
		badBuilding = new Building();
		badBuilding.setCity("Austin");
		badBuilding.setId(25);
		badBuilding.setLocation(badSampleLocation);
		badBuilding.setStreetAddress("Main Street");
		badBuilding.setTotalFloors(0);
		Building otherBadBuilding = new Building();
		otherBadBuilding.setCity("Austin");
		otherBadBuilding.setId(26);
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
		goodBuilding.setRooms(otherGoodRooms);
		goodRoom = new Room();
		goodRoom.setBuilding(goodBuilding);
		goodRoom.setCapacity(3);
		goodRoom.setId(7);
		goodRoom.setType(RoomType.PHYSICAL);
		goodRoom.setName("James");
		goodRoom.setOccupation(RoomOccupation.MEETING);
		Room goodRoom2 = new Room();
		goodRoom2.setBuilding(goodBuilding);
		goodRoom2.setCapacity(4);
		goodRoom2.setId(8);
		goodRoom2.setType(RoomType.PHYSICAL);
		goodRoom2.setName("Steven");
		goodRoom2.setOccupation(RoomOccupation.MEETING);
		Room goodRoom3 = new Room();
		goodRoom3.setBuilding(otherGoodBuilding);
		goodRoom3.setCapacity(5);
		goodRoom3.setId(9);
		goodRoom3.setType(RoomType.PHYSICAL);
		goodRoom3.setName("Pearl");
		goodRoom3.setOccupation(RoomOccupation.MEETING);
		Room goodRoom4 = new Room();
		goodRoom4.setBuilding(otherGoodBuilding);
		goodRoom4.setCapacity(6);
		goodRoom4.setId(10);
		goodRoom4.setType(RoomType.PHYSICAL);
		goodRoom4.setName("Walter");
		goodRoom4.setOccupation(RoomOccupation.MEETING);
		goodRooms.add(goodRoom);
		goodRooms.add(goodRoom2);
		otherGoodRooms.add(goodRoom3);
		otherGoodRooms.add(goodRoom4);

		List<Room> badRooms = new ArrayList<Room>();
		List<Room> otherBadRooms = new ArrayList();
		badBuilding.setRooms(badRooms);
		otherBadBuilding.setRooms(otherBadRooms);
		Room badRoom = new Room();
		badRoom.setBuilding(badBuilding);
		badRoom.setCapacity(7);
		badRoom.setId(11);
		badRoom.setType(RoomType.PHYSICAL);
		badRoom.setName("Blathers");
		badRoom.setOccupation(RoomOccupation.MEETING);
		Room badRoom2 = new Room();
		badRoom2.setBuilding(badBuilding);
		badRoom2.setCapacity(8);
		badRoom2.setId(12);
		badRoom2.setType(RoomType.PHYSICAL);
		badRoom2.setName("Garnet");
		badRoom2.setOccupation(RoomOccupation.MEETING);
		Room badRoom3 = new Room();
		badRoom3.setBuilding(otherBadBuilding);
		badRoom3.setCapacity(9);
		badRoom3.setId(13);
		badRoom3.setType(RoomType.PHYSICAL);
		badRoom3.setName("Amethyst");
		badRoom3.setOccupation(RoomOccupation.MEETING);
		Room badRoom4 = new Room();
		badRoom4.setBuilding(otherBadBuilding);
		badRoom4.setCapacity(10);
		badRoom4.setId(14);
		badRoom4.setType(RoomType.PHYSICAL);
		badRoom4.setName("Bulbasaur");
		badRoom4.setOccupation(RoomOccupation.MEETING);
		badRooms.add(badRoom);
		badRooms.add(badRoom2);
		otherBadRooms.add(badRoom3);
		otherBadRooms.add(badRoom4);


		




	}
	@Test
	public void createGoodBuilding() {
		BuildingDetailsDto goodSampleDto = getBuildingDetailsDtoFromEntity( goodBuilding); 
		
		Mockito.when(buildingRepository.save( Mockito.any(Building.class) ) ).thenAnswer( new Answer<Building>() {
			@Override
			public Building answer(InvocationOnMock invocation ) throws Throwable {
				Building building = invocation.getArgument(0, Building.class);
				if(building.getId() == goodSampleDto.getId()) {
					Mockito.when(buildingRepository.findById( goodSampleDto.getId() ) ).thenReturn(Optional.of( building ) );
				}
				return building;
			}
		});
		buildingService.createBuilding( goodBuilding );
		BuildingDetailsDto result = buildingService.getBuilding(goodBuilding.getId());
		assertTrue(" dto's not equal", buildingDetailsDtoEquals(result, goodSampleDto));
		
	}
	@Test
	public void createBadLocation() {
		assertThrows( Exception.class, ()->{
			buildingService.createBuilding(badBuilding);
		});
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
				if(building.getId() == goodSampleCopy.getId() ) {
					goodSampleCopy.setCity( building.getCity() );
					goodSampleCopy.setLocation( building.getLocation() );
					goodSampleCopy.setRooms( building.getRooms() );
					goodSampleCopy.setStreetAddress( building.getStreetAddress() );
					goodSampleCopy.setTotalFloors( building.getTotalFloors());
				}
				return building;
			}
		});
		Mockito.when( buildingRepository.findById( modifiedSampleCopy.getId() ) ).thenReturn(Optional.of( modifiedSampleCopy ) );
		BuildingRequestDto request = new BuildingRequestDto();
		request.setCity( modifiedSampleCopy.getCity() );
		request.setStreet_address( modifiedSampleCopy.getStreetAddress() );
		request.setTotalFloors( modifiedSampleCopy.getTotalFloors() );
		request.setZipCode( modifiedSampleCopy.getLocation().getZipcode() );	
		buildingService.updateBuilding(modifiedSampleCopy.getId(), request);
		assertTrue( "data did not persist", buildingDetailsDtoEquals(modifiedDto, buildingService.getBuilding( goodBuilding.getId() ) ) );
	}
	
	@Test
	public void updateBuildingBad() {
		
		BuildingRequestDto request = new BuildingRequestDto();
		request.setCity( goodBuilding.getCity() );
		request.setStreet_address( goodBuilding.getStreetAddress() );
		request.setTotalFloors( goodBuilding.getTotalFloors() );
		// TODO check validation rules
		request.setZipCode( "junk");
		assertThrows(Exception.class, ()->{
			buildingService.updateBuilding(goodBuilding.getId(), request);
		});
	}
	
	@Test
	public void getBuildings() {
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
	}
	
	@Test 
	public void getBuildingsByCity() {
		
		List<Building> buildings = new ArrayList<Building>();
		buildings.add(goodBuilding);
		buildings.add(otherGoodBuilding);
		List<BuildingDto> buildingsDto = getBuidlingDtoListFromEntityList(buildings);
		Mockito.when(buildingRepository.findByCity("Miami") ).thenReturn(buildings);
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
	}
	
	@Test
	public void getBuildlingsByStreetAddress() {
		List<Building> buildings = new ArrayList<Building>();
		buildings.add(goodBuilding);
		List<BuildingDto> buildingsDto = getBuidlingDtoListFromEntityList(buildings);
		Mockito.when(buildingRepository.findByStreetAddress("Main Street") ).thenReturn(buildings);
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
		
	}
	
	@Test
	public void getBuildingById() {
		Mockito.when( buildingRepository.findById( goodBuilding.getId() ) ).thenReturn(Optional.of(goodBuilding ) );
		BuildingDetailsDto dto = getBuildingDetailsDtoFromEntity( goodBuilding );
		assertTrue("dto not equal", buildingDetailsDtoEquals(buildingService.getBuilding(goodBuilding.getId()), dto) );
		
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
				if(building.getId() == goodSampleCopy.getId() ) {
					goodSampleCopy.setCity( building.getCity() );
					goodSampleCopy.setLocation( building.getLocation() );
					goodSampleCopy.setRooms( building.getRooms() );
					goodSampleCopy.setStreetAddress( building.getStreetAddress() );
					goodSampleCopy.setTotalFloors( building.getTotalFloors());
				}
				return building;
			}
		});
		Mockito.when( buildingRepository.findById( goodSampleCopy.getId() ) ).thenReturn(Optional.of( goodSampleCopy ) );
			
		buildingService.updateCity(modifiedSampleCopy.getId(), "wuzz");
		assertTrue( "data did not persist", buildingDetailsDtoEquals(modifiedDto, buildingService.getBuilding( goodBuilding.getId() ) ) );
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
				if(building.getId() == goodSampleCopy.getId() ) {
					goodSampleCopy.setCity( building.getCity() );
					goodSampleCopy.setLocation( building.getLocation() );
					goodSampleCopy.setRooms( building.getRooms() );
					goodSampleCopy.setStreetAddress( building.getStreetAddress() );
					goodSampleCopy.setTotalFloors( building.getTotalFloors());
				}
				return building;
			}
		});
		Mockito.when( buildingRepository.findById( goodSampleCopy.getId() ) ).thenReturn(Optional.of( goodSampleCopy ) );
			
		buildingService.updateNumberOfFloors(goodBuilding.getId(), 3);
		assertTrue( "data did not persist", buildingDetailsDtoEquals(modifiedDto, buildingService.getBuilding( goodBuilding.getId() ) ) );
	}
	
	@Test
	public void deleteBuilding() {
		final boolean[] flag = {false};
		final int index = goodBuilding.getId();
		Mockito.doAnswer( new Answer<Void>() {
			@Override
			public Void answer( InvocationOnMock invocation ) {
				Building building = invocation.getArgument( 0, Building.class );
				if(building.getId() == index) {
					flag[0] = true;
				}
				return null;
				
			}
		}).when( buildingRepository ).delete(Mockito.any( Building.class) );
		buildingService.deleteBuilding( goodBuilding.getId() );
		assertTrue( "delete didn't occur", flag[0] );
	}
	@Test
	public void addRoom() {
		final Building sampleBuilding = cloneBuilding(goodBuilding);
		final Room newRoom = cloneRoom(goodRoom, goodBuilding);
		final boolean[] flag = {false};
		newRoom.setId(newRoom.getId()+10000);
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer( InvocationOnMock invocation ) {
				Room room = invocation.getArgument(0,Room.class);
				if(room.getId() == newRoom.getId()) {
					flag[0] = true;
				}
				return null;
			}
		}).when(roomRepository).save(Mockito.any(Room.class));
		buildingService.addRoom(goodBuilding.getId(), newRoom);
		assertTrue("room save not tripped", flag[0]);
	}
	
	//utility functions
	
	private Room cloneRoom( Room room, Building building) {
		Room result = new Room();
		result.setBuilding( building);
		result.setCapacity( room.getCapacity() );
		result.setFloorNumber( room.getFloorNumber() );
		result.setId( room.getId() );
		result.setName( room.getName() );
		result.setOccupation( room.getOccupation() );
		result.setType( room.getType() );
		Set<String> set = new HashSet<String>();
		Iterator<String> iterator = room.getAmenities().iterator();
		while(iterator.hasNext()) {
			set.add(iterator.next());
		}
		result.setAmenities(set);
		return result;
				
	}
	private Building cloneBuilding (Building building ) {
		Building result = new Building();
		result.setId( building.getId() );
		result.setCity( building.getCity());
		result.setLocation(  building.getLocation() );
		result.setStreetAddress(  building.getStreetAddress());
		List<Room> list = new ArrayList<Room>();
		Iterator<Room> iterator = building.getRooms().iterator();
		while(iterator.hasNext()) {
			list.add( cloneRoom( iterator.next(), result ));
		}
		return result;
	}
	private Location cloneLocation( Location location ) {
		Location result = new Location();
		result.setCity( location.getCity() );
		result.setId( location.getId() );
		result.setState( location.getState() );
		result.setZipcode( location.getZipcode() );
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
		locationDto.setId( location.getId() );
		locationDto.setState( location.getState() );
		locationDto.setZipCode( location.getZipcode() );
		locationDto.setNumBuildings( location.getBuildings().size() );
		return locationDto;
	}
	private BuildingDto getBuildingDtoFromEntity( Building building ) {
		BuildingDto resultBuilding = new BuildingDto();
		resultBuilding.setId( building.getId() );
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
			resultRoom.setId( room.getId() );
			resultRoom.setOccupation( room.getOccupation().toString());
			resultRoom.setType( room.getType().toString() );
			result.add( resultRoom );
		}
		return result;
	}
	
	private BuildingDetailsDto getBuildingDetailsDtoFromEntity(Building building) {
		BuildingDetailsDto result = new BuildingDetailsDto();
		result.setCity( building.getCity() );
		result.setId( building.getId() );
		result.setStreet_address( building.getStreetAddress() );
		result.setTotalFloors( building.getTotalFloors() );
		result.setZipCode( building.getLocation().getZipcode() );
		result.setRooms( getRoomDtoListFromRoomList( building.getRooms() ) );
		return result;
		
	}
	private List<RoomDto> getRoomDtoListFromRoomList( List<Room> rooms ){
		List<RoomDto> result = new ArrayList<RoomDto>();
		Iterator<Room> iterator = rooms.iterator();
		while(iterator.hasNext()) {
			RoomDto roomDto = new RoomDto();
			Room room = iterator.next();
			roomDto.setId( room.getId() );
			roomDto.setOccupation( room.getOccupation().toString() );
			roomDto.setType( room.getType().toString() );
			result.add(roomDto);
		}
		return result;
		
	}

}
