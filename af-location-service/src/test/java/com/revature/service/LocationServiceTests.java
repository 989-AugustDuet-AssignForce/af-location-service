package com.revature.service;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.dto.BuildingDto;
import com.revature.dto.LocationDto;
import com.revature.dto.RoomDto;
import com.revature.model.Building;
import com.revature.model.Location;
import com.revature.model.Room;
import com.revature.repository.LocationRepository;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTests {

	@Autowired
	LocationService locationService;
	LocationRepository locationRepository = Mockito.mock( LocationRepository.class );
	BuildingService buildingService = Mockito.mock( BuildingService.class );
	public static Location goodSampleLocation;
	public static Location badSampleLocation;

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
		List<Building> goodBuildings = new ArrayList();
		List<Building> badBuildings = new ArrayList();
		goodSampleLocation.setBuildings(goodBuildings);
		badSampleLocation.setBuildings(badBuildings);
		// instantiate buildings
		Building goodBuilding = new Building();
		goodBuilding.setCity("Miami");
		goodBuilding.setId(23);
		goodBuilding.setLocation(goodSampleLocation);
		goodBuilding.setStreetAddress("Main Street");
		Building otherGoodBuilding = new Building();
		otherGoodBuilding.setCity("Miami");
		otherGoodBuilding.setId(24);
		otherGoodBuilding.setLocation(goodSampleLocation);
		otherGoodBuilding.setStreetAddress("Main Street");
		goodSampleLocation.setBuildings(goodBuildings);
		Building badBuilding = new Building();
		badBuilding.setCity("Austin");
		badBuilding.setId(25);
		badBuilding.setLocation(badSampleLocation);
		badBuilding.setStreetAddress("Main Street");
		Building otherBadBuilding = new Building();
		otherBadBuilding.setCity("Austin");
		otherBadBuilding.setId(26);
		otherBadBuilding.setLocation(badSampleLocation);
		otherBadBuilding.setStreetAddress("Main Street");
		goodBuildings.add(goodBuilding);
		goodBuildings.add(otherGoodBuilding);
		badBuildings.add(badBuilding);
		badBuildings.add(otherBadBuilding);
		//instantiate rooms
		
		List<Room> goodRooms = new ArrayList<Room>();
		List<Room> otherGoodRooms = new ArrayList<Room>();
		goodBuilding.setRooms(goodRooms);
		goodBuilding.setRooms(otherGoodRooms);
		Room goodRoom = new Room();
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
	public void createBadLocation() {
		LocationDto badSampleLocationDto = new LocationDto();
		// TODO instantiation
		Mockito.when(locationRepository.findById(badSampleLocationDto.id)).thenReturn(Optional.empty());
		Mockito.when(locationRepository.save(badSampleLocation)).thenAnswer(new Answer<Location>() {
			@Override
			public Location answer(InvocationOnMock invocation) throws Throwable {
				Location location = invocation.getArgument(0, Location.class);
				if(location.getId() == badSampleLocation.getId()) {
					throw new Exception("bad entity");
				}
				return null;
			}
		});
		Exception exception = assertThrows(Exception.class, () ->{
			locationService.createLocation(badSampleLocationDto);
		});
		assertTrue("bad entity".contains(exception.getMessage()));
	}
	
	@Test
	public void createGoodLocation() {
		LocationDto goodSampleLocationDto = new LocationDto();
		// TODO instantiation 
		Mockito.when(locationRepository.findById( goodSampleLocationDto.id)).thenReturn(Optional.of(goodSampleLocation));
		locationService.createLocation( goodSampleLocationDto );
		LocationDto result = locationService.getLocation( goodSampleLocationDto.id );
		assertFalse( "Didn't find location in repository", result == null );
		assertEquals( result.city, goodSampleLocationDto.city, "city didn't match" );
		assertEquals( result.state,goodSampleLocationDto.state, "state didn't match" );
		assertEquals( result.zipCode,goodSampleLocationDto.zipCode,"zip code didn't match" );
		assertTrue( "Building Lists not the same size", result.buildings.size() == goodSampleLocationDto.buildings.size() );
		Iterator<BuildingDto> iteratorSample = goodSampleLocationDto.buildings.iterator();
		Iterator<BuildingDto> iteratorResult = result.buildings.iterator();
		while( iteratorSample.hasNext () ) {
			BuildingDto buildingSample = iteratorSample.next();
			BuildingDto buildingResult = iteratorResult.next();
			assertEquals( buildingSample.city,buildingResult.city, "city doesn't match" );
			assertTrue( "id doesn't match", buildingSample.id == buildingResult.id);
			assertEquals( buildingSample.streetAddress,buildingResult.streetAddress, "address doesn't match" );
			assertTrue( "Room list not the same size", buildingSample.rooms.size() == buildingResult.rooms.size() );
			Iterator<RoomDto> iteratorSampleRoom = buildingSample.rooms.iterator();
			Iterator<RoomDto> iteratorResultRoom = buildingResult.rooms.iterator();
			while( iteratorSampleRoom.hasNext() ) {
				RoomDto sampleRoom = iteratorSampleRoom.next();
				RoomDto resultRoom = iteratorResultRoom.next();
				assertTrue( "capacity doesn't match", sampleRoom.capacity == resultRoom.capacity );
				assertEquals(sampleRoom.name, resultRoom.name, "name doesn't match for room");
				assertTrue("id doesn't match", sampleRoom.id == resultRoom.id);
				assertEquals(sampleRoom.type, resultRoom.type, "type doesn't match for room");
				assertEquals(sampleRoom.occupation, resultRoom.occupation, "occupation doesn't match for room");
			}
		}
	}
	
	@Test
	public void checkToSeeIfWeGetBuildingsAtLocation() {
		Location loc = new Location();
		assertNotNull(loc);
		assertNotNull(loc.getBuildings());
		assertionEquals(loc.getBuildings(), Building.class,"what is received from the list of buildings is not a building object list");
	}

	@Test
	public void checkWeCanGetAllLocations() {
		List<LocationDto> locations = locationService.getAllLocations();
		assertNotNull(locations);
		assertTrue(locations.size() == 3);
	}

	@Test
	public void checkWeCanGetLocationsByState() {
		boolean result = false;
		String[] stateInputs = {
				"VA", "TX", "FL", "VIRGINIA", "TEXAS", "FLORIDA",
				"Va", "Tx", "Fl", "Virginia", "Texas", "Florida",
				"va", "tx", "fl", "virginia", "texas", "florida",
		};

		for( String s : stateInputs ) {
			List<LocationDto> l = locationService.getLocationsByState(s);
			if( l.get(0).state.equals(s) ) {
				result = true;
			} else if( l.get(1).state.equals(s) ) {
				result = true;
			} else if ( l.get(2).state.equals(s) ) {
				result = true;
			}
		}

		assertTrue(result);
	}

	@Test
	public void checkWeCanGetLocationsByCity() {
		boolean result = false;
		String[] cityInputs = {
				"Reston", "RESTON", "reston",
				"Arlington", "ARLINGTON", "arlington",
				"Tampa", "TAMPA", "tampa"
		};

		for( String c : cityInputs ) {
			List<LocationDto> l = locationService.getLocationsByCity(c);
			if( l.get(0).city.equals(c) ) {
				result = true;
			} else if( l.get(1).city.equals(c) ) {
				result = true;
			} else if ( l.get(2).city.equals(c) ) {
				result = true;
			}
		}

		assertTrue(result);
	}

	@Test
	public void checkWeCanGetLocationsByZipcode() {
		boolean result = false;
		String[] zipCodeInputs = {
				"20190", "33620", "76019"
		};

		for( String z : zipCodeInputs ) {
			List<LocationDto> l = locationService.getLocationsByZipCode(z);
			if( l.get(0).zipCode.equals(z) ) {
				result = true;
			} else if( l.get(1).zipCode.equals(z) ) {
				result = true;
			} else if ( l.get(2).zipCode.equals(z) ) {
				result = true;
			}
		}

		assertTrue(result);
	}

}
