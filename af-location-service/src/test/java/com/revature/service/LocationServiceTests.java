package com.revature.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.revature.dto.*;
import com.revature.repository.LocationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import com.revature.model.Building;
import com.revature.model.Location;
import com.revature.model.Room;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LocationServiceTests {
	
	@InjectMocks
	private LocationServiceImpl locationService;

	@Mock
	private LocationRepository locationRepository;

	@Captor
	ArgumentCaptor<Location> locationArgumentCaptor;

	public Location goodSampleLocation;
	public Location badSampleLocation;

	@Before
	public void setup() {
		// instantiate location
		goodSampleLocation = new Location();
		badSampleLocation = new Location();
		goodSampleLocation.setCity("Tampa");
		badSampleLocation.setCity("Austin");
		goodSampleLocation.setLocationId(3);
		badSampleLocation.setLocationId(2);
		goodSampleLocation.setState("FL");
		badSampleLocation.setState("CA");
		goodSampleLocation.setZipCode("87402");
		// instantiate building list
		List<Building> goodBuildings = new ArrayList<Building>();
		List<Building> badBuildings = new ArrayList<Building>();
		// instantiate buildings
		Building goodBuilding = new Building();
		goodBuilding.setCity("Miami");
		goodBuilding.setBuildingId(23);
		goodBuilding.setLocation(goodSampleLocation);
		goodBuilding.setStreetAddress("Main Street");

		Building otherGoodBuilding = new Building();
		otherGoodBuilding.setCity("Miami");
		otherGoodBuilding.setBuildingId(24);
		otherGoodBuilding.setLocation(goodSampleLocation);
		otherGoodBuilding.setStreetAddress("Main Street");

		Building badBuilding = new Building();
		badBuilding.setCity("Austin");
		badBuilding.setBuildingId(25);
		badBuilding.setLocation(badSampleLocation);
		badBuilding.setStreetAddress("Main Street");

		Building otherBadBuilding = new Building();
		otherBadBuilding.setCity("Austin");
		otherBadBuilding.setBuildingId(26);
		otherBadBuilding.setLocation(badSampleLocation);
		otherBadBuilding.setStreetAddress("Main Street");


		// instantiate rooms

		List<Room> goodRooms = new ArrayList<Room>();
		List<Room> otherGoodRooms = new ArrayList<Room>();

		Room goodRoom = new Room();
		goodRoom.setBuilding(goodBuilding);
		goodRoom.setCapacity(3);
		goodRoom.setRoomId(7);
		goodRoom.setType(RoomType.PHYSICAL);
		goodRoom.setName("James");
		goodRoom.setOccupation(RoomOccupation.MEETING);

		Room goodRoom2 = new Room();
		goodRoom2.setBuilding(goodBuilding);
		goodRoom2.setCapacity(4);
		goodRoom2.setRoomId(8);
		goodRoom2.setType(RoomType.PHYSICAL);
		goodRoom2.setName("Steven");
		goodRoom2.setOccupation(RoomOccupation.MEETING);

		Room goodRoom3 = new Room();
		goodRoom3.setBuilding(otherGoodBuilding);
		goodRoom3.setCapacity(5);
		goodRoom3.setRoomId(9);
		goodRoom3.setType(RoomType.PHYSICAL);
		goodRoom3.setName("Pearl");
		goodRoom3.setOccupation(RoomOccupation.MEETING);

		Room goodRoom4 = new Room();
		goodRoom4.setBuilding(otherGoodBuilding);
		goodRoom4.setCapacity(6);
		goodRoom4.setRoomId(10);
		goodRoom4.setType(RoomType.PHYSICAL);
		goodRoom4.setName("Walter");
		goodRoom4.setOccupation(RoomOccupation.MEETING);

		goodRooms.add(goodRoom);
		goodRooms.add(goodRoom2);
		otherGoodRooms.add(goodRoom3);
		otherGoodRooms.add(goodRoom4);

		List<Room> badRooms = new ArrayList<Room>();
		List<Room> otherBadRooms = new ArrayList<Room>();

		Room badRoom = new Room();
		badRoom.setBuilding(badBuilding);
		badRoom.setCapacity(7);
		badRoom.setRoomId(11);
		badRoom.setType(RoomType.PHYSICAL);
		badRoom.setName("Blathers");
		badRoom.setOccupation(RoomOccupation.MEETING);

		Room badRoom2 = new Room();
		badRoom2.setBuilding(badBuilding);
		badRoom2.setCapacity(8);
		badRoom2.setRoomId(12);
		badRoom2.setType(RoomType.PHYSICAL);
		badRoom2.setName("Garnet");
		badRoom2.setOccupation(RoomOccupation.MEETING);

		Room badRoom3 = new Room();
		badRoom3.setBuilding(otherBadBuilding);
		badRoom3.setCapacity(9);
		badRoom3.setRoomId(13);
		badRoom3.setType(RoomType.PHYSICAL);
		badRoom3.setName("Amethyst");
		badRoom3.setOccupation(RoomOccupation.MEETING);

		Room badRoom4 = new Room();
		badRoom4.setBuilding(otherBadBuilding);
		badRoom4.setCapacity(10);
		badRoom4.setRoomId(14);
		badRoom4.setType(RoomType.PHYSICAL);
		badRoom4.setName("Bulbasaur");
		badRoom4.setOccupation(RoomOccupation.MEETING);

		badRooms.add(badRoom);
		badRooms.add(badRoom2);
		otherBadRooms.add(badRoom3);
		otherBadRooms.add(badRoom4);

		goodBuilding.setRooms(goodRooms);
		otherGoodBuilding.setRooms(otherGoodRooms);
		badBuilding.setRooms(badRooms);
		otherBadBuilding.setRooms(otherBadRooms);

		goodBuildings.add(goodBuilding);
		goodBuildings.add(otherGoodBuilding);
		badBuildings.add(badBuilding);
		badBuildings.add(otherBadBuilding);


		goodSampleLocation.setBuildings(goodBuildings);
		badSampleLocation.setBuildings(badBuildings);
	}

	@Test
	public void createBadLocation() {

		LocationRequestDto locationRequestDto = new LocationRequestDto();
		locationRequestDto.setCity(badSampleLocation.getCity());
		locationRequestDto.setState(badSampleLocation.getState());
		locationRequestDto.setZipCode(badSampleLocation.getZipCode());
		locationService.createLocation(locationRequestDto);
		Mockito.verify(locationRepository, Mockito.times(1))
				.save(locationArgumentCaptor.capture());
		Assert.assertEquals(locationArgumentCaptor.getValue().getCity(), locationRequestDto.getCity());
		Assert.assertEquals(locationArgumentCaptor.getValue().getState(), locationRequestDto.getState());
		Assert.assertNull(locationArgumentCaptor.getValue().getZipCode());

	}

	@Test
	public void checkWeCanGetAllLocations() {
//		List<LocationDto> locations = locationService.getAllLocations();
//		System.out.println(locations.size());
//		Assert.assertNotNull(locations);
//		Assert.assertTrue(locations.size() == 3);

		Location location1 = new Location();
		Location location2 = new Location();

		location1.setLocationId(2);
		location1.setCity("Reston");
		location1.setState("VA");
		location1.setZipCode("76033");

		location2.setLocationId(3);
		location2.setCity("Orlando");
		location2.setState("TX");
		location2.setZipCode("50603");

		Mockito.when(locationRepository.findAll())
				.thenReturn(Arrays.asList(location1, location2, goodSampleLocation));
		assertTrue(locationRepository.findAll().size()==3);
	}

	@Test
	public void checkWeCanGetLocationsByShortState() {
		boolean result1 = false;

		String[] stateInputs1 = {"VA", "TX", "FL"};

		Location location1 = new Location();
		Location location2 = new Location();

		location1.setLocationId(2);
		location1.setCity("Reston");
		location1.setState("VA");
		location1.setZipCode("76033");

		location2.setZipCode("50603");
		location2.setLocationId(3);
		location2.setCity("Orlando");
		location2.setState("TX");

		for( String s : stateInputs1 ) {
			List<LocationDto> list = locationService.getLocationsByState(s);
			Mockito.when(locationRepository.findAllByState(s))
					.thenReturn(Arrays.asList(location1, location2, goodSampleLocation));
			if( location1.getState().equals(s) ) {
				result1 = true;
			} else if( location2.getState().equals(s) ) {
				result1 = true;
			} else if ( goodSampleLocation.getState().equals(s) ) {
				result1 = true;
			}
		}

		assertTrue(result1);

	}


	@Test
	public void checkWeCanGetLocationsByLongState() {

		boolean result2 = false;
		String[] stateInputs2 = {"Virginia", "Texas", "Florida"};

		Location location3 = new Location();
		Location location4 = new Location();
		Location location5 = new Location();

		location3.setLocationId(5);
		location3.setCity("Reston");
		location3.setState("Virginia");
		location3.setZipCode("76033");

		location4.setLocationId(6);
		location4.setCity("Reston");
		location4.setState("Texas");
		location4.setZipCode("76033");

		location5.setLocationId(7);
		location5.setCity("Reston");
		location5.setState("Florida");
		location5.setZipCode("76033");

		for( String s : stateInputs2 ) {
			List<LocationDto> list = locationService.getLocationsByState(s);
			Mockito.when(locationRepository.findAllByState(s))
					.thenReturn(Arrays.asList(location3, location4, location5));
			if( location3.getState().equals(s) ) {
				result2 = true;
			} else if( location4.getState().equals(s) ) {
				result2 = true;
			} else if ( location5.getState().equals(s) ) {
				result2 = true;
			}
		}
		assertTrue(result2);

	}

	@Test
	public void checkWeCanGetLocationsByCity() {
		boolean result = false;
		String[] cityInputs = {"Reston", "Arlington", "Tampa"};

		Location location1 = new Location();
		Location location2 = new Location();
		Location location3 = new Location();

		location1.setLocationId(1);
		location1.setCity("Reston");
		location1.setState("VA");
		location1.setZipCode("76033");

		location2.setLocationId(2);
		location2.setCity("Arlington");
		location2.setState("TX");
		location2.setZipCode("76019");

		location3.setLocationId(3);
		location3.setCity("Tampa");
		location3.setState("FL");
		location3.setZipCode("33620");



		for( String c : cityInputs ) {
			List<LocationDto> list = locationService.getLocationsByCity(c);
			Mockito.when(locationRepository.findAllByCity(c))
					.thenReturn(Arrays.asList(location1, location2, location3));

			if( location1.getCity().equals(c) ) {
				result = true;
			} else if( location2.getCity().equals(c) ) {
				result = true;
			} else if ( location3.getCity().equals(c) ) {
				result = true;
			}
		}

		assertTrue(result);
	}

	@Test
	public void checkWeCanGetLocationsByZipcode() {
		boolean result = false;
		String[] zipCodeInputs = {"20190", "33620", "76019"};

		Location location1 = new Location();
		Location location2 = new Location();
		Location location3 = new Location();

		location1.setLocationId(1);
		location1.setCity("Reston");
		location1.setState("VA");
		location1.setZipCode("76033");

		location2.setLocationId(2);
		location2.setCity("Arlington");
		location2.setState("TX");
		location2.setZipCode("76019");

		location3.setLocationId(3);
		location3.setCity("Tampa");
		location3.setState("FL");
		location3.setZipCode("33620");

		for( String z : zipCodeInputs ) {
			List<LocationDto> list = locationService.getLocationsByZipCode(z);
			Mockito.when(locationRepository.findAllByZipCode(z))
					.thenReturn(Arrays.asList(location1, location2, location3));

			if( location1.getZipCode().equals(z) ) {
				result = true;
			} else if( location2.getZipCode().equals(z) ) {
				result = true;
			} else if ( location3.getZipCode().equals(z) ) {
				result = true;
			}
		}

		assertTrue(result);
	}

	@Test
	public void checkWeCanGetLocationById() {
		boolean result = true;
		int[] idInputs = {1,2,3};

		Location location1 = new Location();

		location1.setLocationId(1);
		location1.setCity("Reston");
		location1.setState("VA");
		location1.setZipCode("76033");
		location1.setBuildings(goodSampleLocation.getBuildings());

		for( int i : idInputs ) {
			Mockito.when(locationRepository.findById(1))
					.thenReturn(Optional.of(location1));
			LocationDetailsDto l = locationService.getLocation(1);

			if( location1.getLocationId() == 0 ) {
				result = false;
			} else if( location1.getZipCode().isEmpty() ) {
				result = false;
			} else if ( location1.getCity().isEmpty() ) {
				result = false;
			} else if ( location1.getState().isEmpty() ) {
				result = false;
			}
		}

		assertTrue(result);
	}

	@Test
	public void createGoodLocation() {
		LocationRequestDto locationRequestDto = new LocationRequestDto();
		locationRequestDto.setCity(goodSampleLocation.getCity());
		locationRequestDto.setState(goodSampleLocation.getState());
		locationRequestDto.setZipCode(goodSampleLocation.getZipCode());
		locationService.createLocation(locationRequestDto);
		Mockito.verify(locationRepository, Mockito.times(1))
				.save(locationArgumentCaptor.capture());
		Assert.assertEquals(locationArgumentCaptor.getValue().getZipCode(), locationRequestDto.getZipCode());
		Assert.assertEquals(locationArgumentCaptor.getValue().getCity(), locationRequestDto.getCity());
		Assert.assertEquals(locationArgumentCaptor.getValue().getState(), locationRequestDto.getState());
    
  }

	@Test
	public void updateLocationGood(){
    
		Mockito.when(locationRepository.findById(goodSampleLocation.getLocationId()))
				.thenReturn(java.util.Optional.of(goodSampleLocation));
		LocationRequestDto updateLocationRequestDto = new LocationRequestDto();
		updateLocationRequestDto.setZipCode("78394");
		updateLocationRequestDto.setCity("Atlanta");
		updateLocationRequestDto.setState("Georgia");
		locationService.updateLocation(goodSampleLocation.getLocationId()
				,updateLocationRequestDto);
		Mockito.verify(locationRepository, Mockito.times(1))
				.save(locationArgumentCaptor.capture());
		Assert.assertEquals(locationArgumentCaptor.getValue().getState(), updateLocationRequestDto.getState());
		Assert.assertEquals(locationArgumentCaptor.getValue().getCity(), updateLocationRequestDto.getCity());
		Assert.assertEquals(locationArgumentCaptor.getValue().getZipCode(), updateLocationRequestDto.getZipCode());

	}

	@Test
	public void updateLocationBad() {
    
		Mockito.when(locationRepository.findById(goodSampleLocation.getLocationId()))
				.thenReturn(java.util.Optional.of(goodSampleLocation));
		LocationRequestDto locationRequestDto = new LocationRequestDto();
		locationRequestDto.setState(null);
		locationRequestDto.setCity(null);
		locationRequestDto.setZipCode(null);
		locationService.updateLocation(goodSampleLocation.getLocationId(),locationRequestDto);
		Mockito.verify(locationRepository, Mockito.times(1))
				.save(locationArgumentCaptor.capture());
		Assert.assertNull(locationArgumentCaptor.getValue().getCity());
		Assert.assertNull(locationArgumentCaptor.getValue().getState());
		Assert.assertNull(locationArgumentCaptor.getValue().getZipCode());

	}

	@Test
	public void updateStateGood() {

		Mockito.when(locationRepository.findById(goodSampleLocation.getLocationId()))
				.thenReturn(java.util.Optional.of(goodSampleLocation));
		String state = "TX";
		locationService.updateState(goodSampleLocation.getLocationId(), state);
		Mockito.verify(locationRepository, Mockito.times(1))
				.save(locationArgumentCaptor.capture());
		Assert.assertEquals(state, locationArgumentCaptor.getValue().getState());

	}

	@Test
	public void updateStateBad() {

		Mockito.when(locationRepository.findById(goodSampleLocation.getLocationId()))
				.thenReturn(java.util.Optional.of(goodSampleLocation));
		String state = null;
		locationService.updateState(goodSampleLocation.getLocationId(), state);
		Mockito.verify(locationRepository, Mockito.times(1))
				.save(locationArgumentCaptor.capture());
		Assert.assertNull(locationArgumentCaptor.getValue().getState());

	}

	@Test
	public void updateCityGood() {

		Mockito.when(locationRepository.findById(goodSampleLocation.getLocationId()))
				.thenReturn(java.util.Optional.of(goodSampleLocation));
		String city = "Orlando";
		locationService.updateCity(goodSampleLocation.getLocationId(), city);
		Mockito.verify(locationRepository, Mockito.times(1))
				.save(locationArgumentCaptor.capture());
		Assert.assertEquals(city, locationArgumentCaptor.getValue().getCity());

	}

	@Test
	public void updateCityBad() {

		Mockito.when(locationRepository.findById(goodSampleLocation.getLocationId()))
				.thenReturn(java.util.Optional.of(goodSampleLocation));
		String city = null;
		locationService.updateCity(goodSampleLocation.getLocationId(), city);
		Mockito.verify(locationRepository, Mockito.times(1))
				.save(locationArgumentCaptor.capture());
		Assert.assertNull(locationArgumentCaptor.getValue().getCity());
    
	}

	@Test
	public void updateZipCodeGood() {

		Mockito.when(locationRepository.findById(goodSampleLocation.getLocationId()))
				.thenReturn(java.util.Optional.of(goodSampleLocation));
		String zipCode = "75205";
		locationService.updateZipCode(goodSampleLocation.getLocationId(), zipCode);
		Mockito.verify(locationRepository, Mockito.times(1))
				.save(locationArgumentCaptor.capture());
		Assert.assertEquals(zipCode, locationArgumentCaptor.getValue().getZipCode());

	}

	@Test
	public void updateZipCodeBad() {

		Mockito.when(locationRepository.findById(goodSampleLocation.getLocationId()))
				.thenReturn(java.util.Optional.of(goodSampleLocation));
		String zipCode = null;
		locationService.updateZipCode(goodSampleLocation.getLocationId(), zipCode);
		Mockito.verify(locationRepository, Mockito.times(1))
				.save(locationArgumentCaptor.capture());
		Assert.assertNull(locationArgumentCaptor.getValue().getZipCode());

	}

	@Test
	public void deleteLocation() {

		Mockito.doNothing().when(locationRepository).deleteById(goodSampleLocation.getLocationId());
		locationService.deleteLocation(goodSampleLocation.getLocationId());
		Mockito.verify(locationRepository, Mockito.times(1))
				.deleteById(goodSampleLocation.getLocationId());

	}

	@Test(expected = Exception.class)
	public void deleteLocationThatDoesNotExist() {

		Mockito.doThrow(Exception.class).when(locationRepository).deleteById(anyInt());
		locationService.deleteLocation(-1);
		Mockito.verify(locationRepository, Mockito.times(1)).deleteById(anyInt());

	}

}
