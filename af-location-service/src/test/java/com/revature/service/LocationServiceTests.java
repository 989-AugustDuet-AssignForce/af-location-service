package com.revature.service;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.dto.BuildingDto;
import com.revature.dto.LocationDto;
import com.revature.dto.RoomDto;
import com.revature.model.Building;
import com.revature.model.Location;
import com.revature.repository.LocationRepository;


@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTests {
	
	
	LocationService locationService = Mockito.mock( LocationService.class );
	LocationRepository locationRepository = Mockito.mock( LocationRepository.class );
	BuildingService buildingService = Mockito.mock( BuildingService.class );
	public static Location goodSampleLocation;
	public static Location badSampleLocation;
	@BeforeClass
	public static void setup() {
		goodSampleLocation = new Location();
		badSampleLocation = new Location();
		
		
	
	}
	
	@Test
	public void createGoodLocation() {
		LocationDto goodSampleLocationDto = new LocationDto();
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
		assertionEquals(loc.getBuildings().getClass(), Building.class,"what is received from the list of buildings is not a building object list");
	}


}
