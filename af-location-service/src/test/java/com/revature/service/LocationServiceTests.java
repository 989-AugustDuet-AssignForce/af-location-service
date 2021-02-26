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
		goodSampleLocation.setId( 3 );
		badSampleLocation.setId( 2 );
		goodSampleLocation.setState( "FL" );
		badSampleLocation.setState( "CA" );
		// TODO get validation rules to make a bad location
		//instantiate building list
		List<Building> goodBuildings = new ArrayList();
		List<Building> badBuildings = new ArrayList();
		goodSampleLocation.setBuildings( goodBuildings );
		badSampleLocation.setBuildings( badBuildings );
		// instantiate buildings
		Building goodBuilding = new Building();
		goodBuilding.setCity( "Miami" );
		goodBuilding.setId( 23 );
		goodBuilding.setLocation( goodSampleLocation );
		goodBuilding.setStreetAddress( "Main Street" );
		Building otherGoodBuilding = new Building();
		otherGoodBuilding.setCity( "Miami" );
		otherGoodBuilding.setId( 24 );
		otherGoodBuilding.setLocation( goodSampleLocation );
		otherGoodBuilding.setStreetAddress( "Main Street" );
		goodSampleLocation.setBuildings( goodBuildings );
		Building badBuilding = new Building();
		badBuilding.setCity( "Austin" );
		badBuilding.setId( 25 );
		badBuilding.setLocation( badSampleLocation );
		badBuilding.setStreetAddress( "Main Street" );
		Building otherBadBuilding = new Building();
		otherBadBuilding.setCity( "Austin" );
		otherBadBuilding.setId( 26 );
		otherBadBuilding.setLocation( badSampleLocation );
		otherBadBuilding.setStreetAddress( "Main Street" );
		goodBuildings.add( goodBuilding );
		goodBuildings.add( otherGoodBuilding );
		badBuildings.add( badBuilding );
		badBuildings.add( otherBadBuilding );
		//instantiate rooms
		
		List<Room> goodRooms = new ArrayList<Room>();
		List<Room> otherGoodRooms = new ArrayList<Room>();
		goodBuilding.setRooms( goodRooms );
		goodBuilding.setRooms( otherGoodRooms );
		Room goodRoom = new Room();
		goodRoom.setBuilding( goodBuilding );
		goodRoom.setCapacity( 3 );
		goodRoom.setId( 7 );
		goodRoom.setType( RoomType.PHYSICAL );
		goodRoom.setName( "James" );
		goodRoom.setOccupation( RoomOccupation.MEETING );
		Room goodRoom2 = new Room();
		goodRoom2.setBuilding( goodBuilding );
		goodRoom2.setCapacity( 4 );
		goodRoom2.setId( 8 );
		goodRoom2.setType( RoomType.PHYSICAL );
		goodRoom2.setName( "Steven" );
		goodRoom2.setOccupation( RoomOccupation.MEETING );
		Room goodRoom3 = new Room();
		goodRoom3.setBuilding( otherGoodBuilding );
		goodRoom3.setCapacity( 5 );
		goodRoom3.setId( 9 );
		goodRoom3.setType( RoomType.PHYSICAL );
		goodRoom3.setName( "Pearl" );
		goodRoom3.setOccupation( RoomOccupation.MEETING );
		Room goodRoom4 = new Room();
		goodRoom4.setBuilding( otherGoodBuilding );
		goodRoom4.setCapacity( 6 );
		goodRoom4.setId( 10 );
		goodRoom4.setType( RoomType.PHYSICAL );
		goodRoom4.setName( "Walter" );
		goodRoom4.setOccupation( RoomOccupation.MEETING );
		goodRooms.add( goodRoom );
		goodRooms.add( goodRoom2 );
		otherGoodRooms.add( goodRoom3 );
		otherGoodRooms.add( goodRoom4 );
		
		List<Room> badRooms = new ArrayList<Room>();
		List<Room> otherBadRooms = new ArrayList();
		badBuilding.setRooms( badRooms );
		otherBadBuilding.setRooms( otherBadRooms );
		Room badRoom = new Room();
		badRoom.setBuilding( badBuilding );
		badRoom.setCapacity( 7 );
		badRoom.setId( 11 );
		badRoom.setType( RoomType.PHYSICAL );
		badRoom.setName( "Blathers" );
		badRoom.setOccupation( RoomOccupation.MEETING );
		Room badRoom2 = new Room();
		badRoom2.setBuilding( badBuilding );
		badRoom2.setCapacity( 8 );
		badRoom2.setId( 12 );
		badRoom2.setType( RoomType.PHYSICAL );
		badRoom2.setName( "Garnet" );
		badRoom2.setOccupation( RoomOccupation.MEETING );
		Room badRoom3 = new Room();
		badRoom3.setBuilding( otherBadBuilding );
		badRoom3.setCapacity( 9 );
		badRoom3.setId( 13 );
		badRoom3.setType( RoomType.PHYSICAL );
		badRoom3.setName( "Amethyst" );
		badRoom3.setOccupation( RoomOccupation.MEETING );
		Room badRoom4 = new Room();
		badRoom4.setBuilding( otherBadBuilding );
		badRoom4.setCapacity( 10 );
		badRoom4.setId( 14 );
		badRoom4.setType( RoomType.PHYSICAL );
		badRoom4.setName( "Bulbasaur" );
		badRoom4.setOccupation( RoomOccupation.MEETING );
		badRooms.add( badRoom );
		badRooms.add( badRoom2 );
		otherBadRooms.add( badRoom3 );
		otherBadRooms.add( badRoom4 );
		
		
		
		
		
	
	}
	
	@Test
	public void createBadLocation() {
		
		Mockito.when( locationRepository.save( badSampleLocation ) ).thenAnswer (new Answer<Location>() {
			@Override
			public Location answer( InvocationOnMock invocation ) throws Throwable {
				Location location = invocation.getArgument(0, Location.class);
				if( location.getId() == badSampleLocation.getId() ) {
					throw new Exception( "bad entity" );
				}
				return null;
			}
		} );
		Exception exception = assertThrows( Exception.class, () ->{
			locationService.createLocation( badSampleLocation );
		} );
		assertTrue( "didn't throw exception", "bad entity".contains( exception.getMessage() ) );
	}
	
	@Test
	public void createGoodLocation() {
		LocationDto goodSampleLocationDto = getLocationDtoFromEntity( goodSampleLocation ); 
		Mockito.when(locationRepository.findById( goodSampleLocationDto.id ) ).thenReturn(Optional.of( goodSampleLocation ) );
		locationService.createLocation( goodSampleLocation );
		LocationDto result = locationService.getLocation( goodSampleLocationDto.id );
		assertTrue( "locationDto's not equal", locationDtoEquals( goodSampleLocationDto, result ) );
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

	@Test
	public void checkWeCanGetLocationById() {
		boolean result = true;
		int[] idInputs = {1,2,3};

		for( int i : idInputs ) {
			LocationDto l = locationService.getLocation(i);
			if( l.buildings.isEmpty() ) {
				result = false;
			} else if( l.zipCode.isEmpty() ) {
				result = false;
			} else if ( l.city.isEmpty() ) {
				result = false;
			} else if ( l.state.isEmpty() ) {
				result = false;
			}
		}

		assertTrue(result);
	}

	@Test 
	public void updateLocationGood(){
		final Location goodSampleCopy = cloneLocation( goodSampleLocation );
		Location modifiedSampleCopy = cloneLocation( goodSampleCopy);
		modifiedSampleCopy.setCity( "wuzz" );
		//TODO check validation
		Mockito.when( locationRepository.save( modifiedSampleCopy )).thenAnswer( new Answer() {
			@Override
			public Location answer (InvocationOnMock invocation )  {
				Location location = invocation.getArgument( 0, Location.class );
				if( location.getId() == goodSampleCopy.getId() ) {
					goodSampleCopy.setBuildings( location.getBuildings() );
					goodSampleCopy.setCity( location.getCity() );
					goodSampleCopy.setState( location.getState() );
					goodSampleCopy.setZipCode( location.getZipCode() );
				}
				return goodSampleCopy;
			}
		} );
		locationService.updateLocation( goodSampleCopy.getId(), modifiedSampleCopy );
		Mockito.when( locationRepository.findById( goodSampleCopy.getId()) ).thenReturn( ( Optional.of( goodSampleCopy ) ) );
		LocationDto result = locationService.getLocation( goodSampleCopy.getId() );
		assertTrue( "Location not persisted", locationDtoEquals( result, getLocationDtoFromEntity(modifiedSampleCopy) ) );
	}
	@Test 
	public void updateLocationBad() {
		Location badSampleCopy = cloneLocation( goodSampleLocation );
		// TODO update for validation rules
		badSampleCopy.setCity( "badValue" );
		Mockito.when( locationRepository.save( badSampleCopy ) ).thenAnswer( new Answer<Location>() {
			@Override
			public Location answer( InvocationOnMock invocation ) throws Throwable {
				Location location = invocation.getArgument( 0, Location.class );
				if( location.getId() == badSampleLocation.getId() ) {
					throw new Exception( "bad entity" );
				}
				return null;
			}
		} );
		Exception exception = assertThrows( Exception.class, () ->{
			locationService.updateLocation( badSampleCopy.getId(), badSampleCopy );
		} );
		assertTrue( "didn't throw exception", "bad entity".contains( exception.getMessage() ) );
	}
	@Test
	public void updateStateGood() {
		Location goodSampleCopy = cloneLocation( goodSampleLocation );	
		//TODO check validation
		Mockito.when( locationRepository.save( goodSampleCopy )).thenReturn( goodSampleCopy );
		Mockito.when( locationRepository.findById( goodSampleCopy.getId() ) ).thenReturn( ( Optional.of( goodSampleCopy ) ) );
		locationService.updateState( goodSampleCopy.getId(), "wuzz" );
		LocationDto result = locationService.getLocation(goodSampleCopy.getId());
		assertTrue( "state not persisted", "wuzz".equals( result.state ) );
	}
	@Test
	public void updateStateBad() {
		{
			Location goodSampleCopy = cloneLocation( goodSampleLocation );	
			//TODO check validation
			Mockito.when( locationRepository.save( goodSampleCopy )).thenReturn( goodSampleCopy );
			Mockito.when( locationRepository.findById( goodSampleCopy.getId() ) ).thenReturn( ( Optional.of( goodSampleCopy ) ) );
			locationService.updateState( goodSampleCopy.getId(), "wuzz" );
			LocationDto result = locationService.getLocation( goodSampleCopy.getId() );
			assertFalse( "bad state changed", "wuzz".equals( result.state ) );
		}
		
	}
	@Test
	public void updateCityGood() {
		Location goodSampleCopy = cloneLocation( goodSampleLocation );	
		//TODO check validation
		Mockito.when( locationRepository.save( goodSampleCopy )).thenReturn( goodSampleCopy );
		Mockito.when( locationRepository.findById( goodSampleCopy.getId() ) ).thenReturn( ( Optional.of( goodSampleCopy ) ) );
		locationService.updateCity( goodSampleCopy.getId(), "wuzz" );
		LocationDto result = locationService.getLocation( goodSampleCopy.getId() );
		assertTrue( "city not persisted", "wuzz".equals( result.city ) );
	}
	@Test
	public void updateCityBad() {
		Location goodSampleCopy = cloneLocation( goodSampleLocation );	
		//TODO check validation
		Mockito.when( locationRepository.save( goodSampleCopy )).thenReturn( goodSampleCopy );
		Mockito.when( locationRepository.findById( goodSampleCopy.getId() ) ).thenReturn( ( Optional.of( goodSampleCopy ) ) );
		locationService.updateCity( goodSampleCopy.getId(), "wuzz" );
		LocationDto result = locationService.getLocation( goodSampleCopy.getId() );
		assertFalse( "city should not persist", "wuzz".equals( result.city ) );
	}
	@Test
	public void updateZipCodeGood() {
		Location goodSampleCopy = cloneLocation( goodSampleLocation );	
		//TODO check validation
		Mockito.when( locationRepository.save( goodSampleCopy )).thenReturn( goodSampleCopy );
		Mockito.when( locationRepository.findById( goodSampleCopy.getId() ) ).thenReturn( ( Optional.of( goodSampleCopy ) ) );
		locationService.updateZipCode(goodSampleCopy.getId(), "wuzz" );
		LocationDto result = locationService.getLocation( goodSampleCopy.getId() );
		assertTrue( "zip code not persisted", "wuzz".equals( result.zipCode ) );
	}
	@Test
	public void updateZipCodeBad() {
		Location goodSampleCopy = cloneLocation( goodSampleLocation );	
		//TODO check validation
		Mockito.when( locationRepository.save( goodSampleCopy )).thenReturn( goodSampleCopy );
		Mockito.when( locationRepository.findById( goodSampleCopy.getId() ) ).thenReturn( ( Optional.of( goodSampleCopy ) ) );
		locationService.updateZipCode( goodSampleCopy.getId(), "wuzz" );
		LocationDto result = locationService.getLocation( goodSampleCopy.getId() );
		assertFalse( "zip code should not persist", "wuzz".equals( result.zipCode ) );
	}
	@Test
	public void addBuildingGood() {
		Location goodSampleCopy = cloneLocation( goodSampleLocation );
		Building newBuilding = new Building();
		newBuilding.setCity( "Riverdale" );
		newBuilding.setId( 123456 );
		newBuilding.setLocation( goodSampleCopy );
		newBuilding.setRooms( goodSampleCopy.getBuildings().get(0).getRooms() );
		newBuilding.setStreetAddress( "WEEEEEEEE" );
		//TODO check validation
		
		Mockito.when( locationRepository.save( goodSampleCopy )).thenReturn( goodSampleCopy );
		Mockito.when( locationRepository.findById( goodSampleCopy.getId() ) ).thenReturn( ( Optional.of( goodSampleCopy ) ) );
		locationService.addBuilding( goodSampleCopy.getId(), getBuildingDtoFromEntity( newBuilding ) );
		LocationDto result = locationService.getLocation( goodSampleCopy.getId() );
		boolean flag = false;
		Iterator<BuildingDto> iterator = result.buildings.iterator();
		BuildingDto newBuildingDto = getBuildingDtoFromEntity( newBuilding );
		while( iterator.hasNext() ) {
			if( BuildingDtoEquals( iterator.next(), newBuildingDto ) ) {
				flag = true;
				break;
			}
		}
		assertTrue( "building not added", flag );
	}
	
	@Test
	public void addBuildingBad() {
		Location goodSampleCopy = cloneLocation( goodSampleLocation );
		Building newBuilding = new Building();
		newBuilding.setCity( "Riverdale" );
		newBuilding.setId( 123456 );
		newBuilding.setLocation( goodSampleCopy );
		newBuilding.setRooms( goodSampleCopy.getBuildings().get(0).getRooms() );
		newBuilding.setStreetAddress( "WEEEEEEEE" );
		//TODO check validation
		
		Mockito.when( locationRepository.save( goodSampleCopy )).thenReturn( goodSampleCopy );
		Mockito.when( locationRepository.findById( goodSampleCopy.getId() ) ).thenReturn( ( Optional.of( goodSampleCopy ) ) );
		locationService.addBuilding( goodSampleCopy.getId(), getBuildingDtoFromEntity( newBuilding ) );
		LocationDto result = locationService.getLocation( goodSampleCopy.getId() );
		boolean flag = false;
		Iterator<BuildingDto> iterator = result.buildings.iterator();
		BuildingDto newBuildingDto = getBuildingDtoFromEntity(newBuilding);
		while(iterator.hasNext()) {
			if( BuildingDtoEquals( iterator.next(), newBuildingDto ) ) {
				flag = true;
				break;
			}
		}
		assertFalse("building added", flag);
	}
	@Test
	public void deleteLocation() {
		final Location goodCopy = cloneLocation(goodSampleLocation);
		final boolean[] flag = {false};
		
		Mockito.doAnswer( new Answer<Object>() {
			@Override
			public Object answer( InvocationOnMock invocation ) throws Throwable {
				Location location = invocation.getArgument(0);
				if( location.getId() == goodCopy.getId() ) {
					flag[0] = true;
				}
				return null;
			}
			
		}).when( locationRepository ).delete( goodCopy );;

		Mockito.when( locationRepository.findById( goodCopy.getId() ) ).thenAnswer( new Answer<Location>() {
			@Override
			public Location answer( InvocationOnMock invocation ) throws Throwable {
				Location location = invocation.getArgument( 0, Location.class );
				if( location.getId() == goodCopy.getId() && flag[0] ) {
					throw new Exception( "entity not found" );
				}
				return null;
			}
		});
		Exception exception = assertThrows( Exception.class, () ->{
			locationService.deleteLocation( goodCopy.getId() );
		} );
		assertTrue( "didn't throw exception", "entity not found".contains( exception.getMessage() ) );
	}
	
	
	//utility functions
	
	private Location cloneLocation( Location location ) {
		Location result = new Location();
		result.setCity( location.getCity() );
		result.setId( location.getId() );
		result.setState( location.getState() );
		result.setZipCode( location.getZipCode() );
		List<Building> list = new ArrayList<Building>();
		Iterator<Building> iterator = location.getBuildings().iterator();
		while( iterator.hasNext() ) {
			list.add( iterator.next() );
		}
		result.setBuildings( list );
		return result;
	}
	
	private boolean locationDtoEquals( LocationDto locationA, LocationDto locationB ) {
		if( locationA == locationB ) {
			return true;
		}
		if( locationA.id != locationB.id ) {
			return false;
		}
		if( !locationA.city.equals( locationB.city ) ) {
			return false;
		}
		if( !locationA.state.equals( locationB.state ) ) {
			return false;
		}
		if( !locationA.zipCode.equals( locationB.zipCode ) ) {
			return false;
		}
		if( locationA.buildings.size() != locationB.buildings.size() ) {
			return false;
		}
		Iterator<BuildingDto> iteratorA = locationA.buildings.iterator();
		Iterator<BuildingDto> iteratorB = locationB.buildings.iterator();
		while( iteratorA.hasNext() ) {
			if( !BuildingDtoEquals( iteratorA.next(), iteratorB.next() ) ) {
				return false;
			}
		}
		return true;
		
	}
	private boolean roomDtoEquals( RoomDto roomA, RoomDto roomB) {
		if( roomA == roomB ) {
			return true;
		}
		if( roomA.id != roomB.id ) {
			return false;
		}
		if( roomA.capacity != roomB.capacity ) {
			return false;
		}
		if( !roomA.name.equals( roomB.name ) ) {
			return false;
		}
		if( !roomA.occupation.equals( roomB.occupation ) ) {
			return false;
		}
		if( !roomA.type.equals( roomB.type ) ) {
			return false;
		}
		
		return true;
	}
	
	private boolean BuildingDtoEquals( BuildingDto buildingA, BuildingDto buildingB ) {
		if( buildingA == buildingB ) {
			return true;
		}
		if( !buildingA.city.equals( buildingB.city ) ) {
			return false;
		}
		if( !buildingA.streetAddress.equals( buildingB.streetAddress ) ) {
			return false;
		}
		if( buildingA.id != buildingB.id ) {
			return false;
		}
		if( buildingA.rooms.size() != buildingB.rooms.size() ) {
			return false;
		}
		Iterator<RoomDto> iteratorRoomsA = buildingA.rooms.iterator();
		Iterator<RoomDto> iteratorRoomsB = buildingB.rooms.iterator();
		while(iteratorRoomsA.hasNext()) {
			if( !roomDtoEquals( iteratorRoomsA.next(),iteratorRoomsB.next() ) ) {
				return false;
			}
		}
		return true;
	}
	
	private LocationDto getLocationDtoFromEntity( Location location ) {
		LocationDto locationDto = new LocationDto();
		locationDto.city = location.getCity();
		locationDto.id = location.getId();
		locationDto.state = location.getState();
		locationDto.zipCode = location.getZipCode();
		locationDto.buildings = this.getBuidlingDtoListFromEntityList( location.getBuildings() );
		return locationDto;
	}
	private BuildingDto getBuildingDtoFromEntity( Building building ) {
		BuildingDto resultBuilding = new BuildingDto();
		resultBuilding.city = building.getCity();
		resultBuilding.id = building.getId();
		resultBuilding.streetAddress = building.getStreetAddress();
		resultBuilding.rooms = getRoomsDtoListFromEntityList( building.getRooms() );
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
			resultRoom.capacity = room.getCapacity();
			resultRoom.id = room.getId();
			resultRoom.name = room.getName();
			// TODO implement conversion of occupation and type string to and from enum
//			resultRoom.occupation = room.getOccupation();
//			resultRoom.type = room.getType()
			result.add( resultRoom );
		}
		return result;
	}
}
