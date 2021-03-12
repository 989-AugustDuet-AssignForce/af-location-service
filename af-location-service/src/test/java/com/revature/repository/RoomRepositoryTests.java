package com.revature.repository;


import com.revature.model.Building;
import com.revature.model.Location;
import com.revature.model.Room;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


//@SpringBootTest
//@RunWith(SpringRunner.class)
public class RoomRepositoryTests {

//    @Autowired
//    private RoomRepository repository;
//
//    private Building testBuilding1;
//    private Location testLocation1;
//    private Room physicalMeetingRoom1;
//    private Room remoteMeetingRoom1;
//    private Room remoteMeetingRoom2;
//    private Room remoteTrainingRoom1;
//    private Room physicalMeetingRoom2;
//    private Room physicalTrainingRoom1;
//    private Room physicalTrainingRoom2;
//
//
//    @Before
//    public void setUp() {
//
////        List<Room> allRooms = repository.findAll ();
////        allRooms.forEach ( repository::delete );
//
//        //LISTS
//
//        testBuilding1 = new Building ();
//        testBuilding1.setCity ( "Test City" );
//        testBuilding1.setLocation ( testLocation1 );
//
//        //LOCATIONS
//
//
//        testLocation1 = new Location ();
//        testLocation1.setCity ( "Test City" );
//        testLocation1.setState ( "Test State" );
//        testLocation1.setBuildings ( Collections.singletonList ( testBuilding1 ) );
//
//
//        //PHYSICAL ROOMS
//
//        physicalMeetingRoom1 = new Room ();
//        Set<String> amenities = new HashSet<> ();
//        amenities.add ( "HD PROJECTORS" );
//
//        physicalMeetingRoom1.setBuilding ( testBuilding1 );
//        physicalMeetingRoom1.setCapacity ( 20 );
//        physicalMeetingRoom1.setName ( "PHYSICAL MEETING 1" );
//        physicalMeetingRoom1.setOccupation ( RoomOccupation.MEETING );
//        physicalMeetingRoom1.setRoomAmenities ( amenities );
//        physicalMeetingRoom1.setFloorNumber ( 1 );
//        physicalMeetingRoom1.setType ( RoomType.PHYSICAL );
//
//        physicalMeetingRoom2 = new Room ();
//        physicalMeetingRoom2.setBuilding ( testBuilding1 );
//        physicalMeetingRoom2.setCapacity ( 20 );
//        physicalMeetingRoom2.setName ( "PHYSICAL MEETING 2" );
//        physicalMeetingRoom2.setOccupation ( RoomOccupation.MEETING );
//        physicalMeetingRoom2.setRoomAmenities ( amenities );
//        physicalMeetingRoom2.setFloorNumber ( 1 );
//        physicalMeetingRoom2.setType ( RoomType.PHYSICAL );
//
//
//        physicalTrainingRoom1 = new Room ();
//        physicalTrainingRoom1.setBuilding ( testBuilding1 );
//        physicalTrainingRoom1.setCapacity ( 20 );
//        physicalTrainingRoom1.setName ( "PHYSICAL TRAINING 1" );
//        physicalTrainingRoom1.setOccupation ( RoomOccupation.TRAINING );
//        physicalTrainingRoom1.setRoomAmenities ( amenities );
//        physicalTrainingRoom1.setFloorNumber ( 2 );
//        physicalTrainingRoom1.setType ( RoomType.PHYSICAL );
//
//
//        physicalTrainingRoom2 = new Room ();
//        physicalTrainingRoom2.setBuilding ( testBuilding1 );
//        physicalTrainingRoom2.setCapacity ( 20 );
//        physicalTrainingRoom2.setName ( "PHYSICAL TRAINING 2" );
//        physicalTrainingRoom2.setOccupation ( RoomOccupation.TRAINING );
//        physicalTrainingRoom2.setRoomAmenities ( amenities );
//        physicalTrainingRoom2.setFloorNumber ( 1 );
//        physicalTrainingRoom2.setType ( RoomType.PHYSICAL );
//
//
//        ///REMOTE ROOMS
//
//        remoteMeetingRoom1 = new Room ();
//
//        remoteMeetingRoom1.setBuilding ( testBuilding1 );
//        remoteMeetingRoom1.setCapacity ( 20 );
//        remoteMeetingRoom1.setName ( "REMOTE MEETING 1" );
//        remoteMeetingRoom1.setOccupation ( RoomOccupation.MEETING );
//        remoteMeetingRoom1.setRoomAmenities ( amenities );
//        remoteMeetingRoom1.setFloorNumber ( 0 );
//        remoteMeetingRoom1.setType ( RoomType.REMOTE );
//
//
//        remoteMeetingRoom2 = new Room ();
//        remoteMeetingRoom2.setBuilding ( testBuilding1 );
//        remoteMeetingRoom2.setCapacity ( 20 );
//        remoteMeetingRoom2.setName ( "REMOTE MEETING 2" );
//        remoteMeetingRoom2.setOccupation ( RoomOccupation.MEETING );
//        remoteMeetingRoom2.setRoomAmenities ( amenities );
//        remoteMeetingRoom2.setFloorNumber ( 0 );
//        remoteMeetingRoom2.setType ( RoomType.REMOTE );
//
//
//        remoteTrainingRoom1 = new Room ();
//        remoteTrainingRoom1.setBuilding ( testBuilding1 );
//        remoteTrainingRoom1.setCapacity ( 20 );
//        remoteTrainingRoom1.setName ( "Remote Training 1" );
//        remoteTrainingRoom1.setOccupation ( RoomOccupation.TRAINING );
//        remoteTrainingRoom1.setRoomAmenities ( amenities );
//        remoteTrainingRoom1.setFloorNumber ( 0 );
//        remoteTrainingRoom1.setType ( RoomType.REMOTE );
//
//
//    }
//
//    @Test
//    public void whenSubmittingValidRoomWithoutIdToRepository_SavedRoomReturnedWithIdFromDb() {
//
//        Room fromDb = repository.save ( physicalMeetingRoom1 );
//
//        assertNotEquals ( fromDb, physicalMeetingRoom1 );
//
//
//    }
//
//    @Test
//    public void whenSearchingRoomsByType_CorrectNumberOfRoomsByTypeAreShown() {
//
//        repository.saveAll ( Arrays.asList ( physicalMeetingRoom1, remoteMeetingRoom1 ) );
//
//        List<Room> allPhysicalRooms = repository.findByType ( RoomType.PHYSICAL );
//
//        assertEquals ( 1, allPhysicalRooms.size () );
//
//    }
//
//
//    @Test
//    public void whenSearchingByBothRoomTypeAndOccupation_RoomsThatFitCriteriaAreShown() {
//        repository.saveAll ( Arrays.asList ( physicalMeetingRoom1,
//                physicalMeetingRoom2,
//                physicalTrainingRoom1,
//                physicalTrainingRoom2,
//                remoteMeetingRoom1,
//                remoteMeetingRoom2,
//                remoteTrainingRoom1 ) );
//
//        List<Room> physicalTrainingRooms = repository.findByTypeAndOccupation ( RoomType.PHYSICAL, RoomOccupation.TRAINING );
//        List<Room> remoteMeetingRooms = repository.findByTypeAndOccupation ( RoomType.REMOTE, RoomOccupation.MEETING );
//
//        assertEquals ( 2, physicalTrainingRooms.size () );
//        assertEquals ( 2, remoteMeetingRooms.size () );
//
//        physicalTrainingRooms.forEach ( room -> {
//            assertEquals ( RoomType.PHYSICAL, room.getType () );
//            assertEquals ( RoomOccupation.TRAINING, room.getOccupation () );
//        } );
//
//        remoteMeetingRooms.forEach ( room -> {
//            assertEquals ( RoomType.REMOTE, room.getType () );
//            assertEquals ( RoomOccupation.MEETING, room.getOccupation () );
//        } );
//
//    }
//
//    @Test
//    public void whenSearchingByBuildingNumberAndFloor_CorrectNumberOfRoomsAreShown() {
//
//        repository.saveAll ( Arrays.asList ( physicalMeetingRoom1,
//                physicalMeetingRoom2,
//                physicalTrainingRoom1,
//                physicalTrainingRoom2,
//                remoteMeetingRoom1,
//                remoteMeetingRoom2,
//                remoteTrainingRoom1 ) );
//
//
//        int size = repository.findByBuildingAndFloorNumber ( testBuilding1, 0 ).size ();
//
//        assertEquals ( 3, size );
//
//        size = repository.findByBuildingAndFloorNumber ( testBuilding1, 1 ).size ();
//
//        assertEquals ( 3, size );
//
//        size = repository.findByBuildingAndFloorNumber ( testBuilding1, 2 ).size ();
//
//        assertEquals ( 1, size );
//
//
//    }


}
