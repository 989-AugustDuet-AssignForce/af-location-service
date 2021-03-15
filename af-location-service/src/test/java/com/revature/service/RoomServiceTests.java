package com.revature.service;

import com.revature.Exception.NotFoundException;
import com.revature.dto.RoomDetailsDto;
import com.revature.dto.RoomDto;
import com.revature.dto.RoomRequestDto;
import com.revature.model.Building;
import com.revature.model.Location;
import com.revature.model.Room;
import com.revature.repository.BuildingRepository;
import com.revature.repository.RoomRepository;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoomServiceTests {

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private BuildingRepository buildingRepository;


    @Autowired
    private RoomService roomService;
    private Location testLocation1;
    private Building testBuilding1;
    private List<Room> allRooms;
    private List<Room> trainingRoomList;
    private List<Room> meetingRoomList;
    private List<Room> physicalRoomList;
    private List<Room> virtualRoomList;
    private List<Room> remoteRoomList;
    private List<Room> physicalTrainingRoomList;
    private List<Room> physicalMeetingRoomList;
    private List<Room> remoteTrainingRoomList;
    private List<Room> remoteMeetingRoomList;
    private Room physicalMeetingRoom1;
    private Room physicalMeetingRoom1WithId;
    private Room physicalMeetingRoom2;
    private Room physicalMeetingRoom2WithId;
    private Room physicalTrainingRoom1;
    private Room physicalTrainingRoom1WithId;
    private Room physicalTrainingRoom2;
    private Room physicalTrainingRoom2WithId;
    private Room remoteMeetingRoom1;
    private Room remoteMeetingRoom1WithId;
    private Room remoteMeetingRoom2;
    private Room remoteMeetingRoom2WithId;
    private Room remoteTrainingRoom1;
    private Room remoteTrainingRoom1WithId;
    private Room virtualRoomWithId;
    private Room physicalMeetingRoom1Updated;


    private static RoomDetailsDto roomDetailsMapper( Room room ) {
        RoomDetailsDto detailsDto = new RoomDetailsDto ();
        detailsDto.setId ( room.getRoomId () );
        detailsDto.setOccupation ( room.getOccupation ().name () );
        detailsDto.setCapacity ( room.getCapacity () );
        detailsDto.setFloorNumber ( room.getFloorNumber () );
        detailsDto.setName ( room.getName () );
        detailsDto.setType ( room.getType ().toString () );
        Set<String> roomAmenities = room.getRoomAmenities ();
        detailsDto.setAmenities ( roomAmenities );
        return detailsDto;
    }

    private static RoomDto roomMapper( Room room ) {
        RoomDto roomDto = new RoomDto ();
        roomDto.setId ( room.getRoomId () );
        roomDto.setOccupation ( room.getOccupation ().name () );
        roomDto.setType ( room.getType ().name () );
        return roomDto;
    }

    private static List<RoomDto> listMapper( List<Room> list ) {

        return list.stream ().map ( RoomServiceTests::roomMapper ).collect ( Collectors.toList () );
    }

    @Before
    //Setup mocks
    public void setUp() {


        //Buildings

        testBuilding1 = new Building ();
        testBuilding1.setCity ( "Test City" );
        testBuilding1.setLocation ( testLocation1 );
        testBuilding1.setBuildingId ( 1 );
        testBuilding1.setRooms ( allRooms );

        //LOCATIONS


        testLocation1 = new Location ();
        testLocation1.setLocationId ( 1 );
        testLocation1.setCity ( "Test City" );
        testLocation1.setState ( "Test State" );
        testLocation1.setBuildings ( Collections.singletonList ( testBuilding1 ) );


        //PHYSICAL ROOMS

        physicalMeetingRoom1 = new Room ();
        Set<String> amenities = new HashSet<> ( 2 );
        amenities.addAll ( Collections.singletonList ( "AIR CONDITIONING" ) );

        physicalMeetingRoom1.setBuilding ( testBuilding1 );
        physicalMeetingRoom1.setCapacity ( 20 );
        physicalMeetingRoom1.setName ( "PHYSICAL MEETING 1" );
        physicalMeetingRoom1.setOccupation ( RoomOccupation.MEETING );
        physicalMeetingRoom1.setRoomAmenities ( amenities );
        physicalMeetingRoom1.setFloorNumber ( 1 );
        physicalMeetingRoom1.setType ( RoomType.PHYSICAL );

        physicalMeetingRoom1WithId = new Room ();
        physicalMeetingRoom1WithId.setRoomId ( 1 );
        physicalMeetingRoom1WithId.setBuilding ( testBuilding1 );
        physicalMeetingRoom1WithId.setCapacity ( 20 );
        physicalMeetingRoom1WithId.setName ( "PHYSICAL MEETING 1" );
        physicalMeetingRoom1WithId.setOccupation ( RoomOccupation.MEETING );
        physicalMeetingRoom1WithId.setRoomAmenities ( amenities );
        physicalMeetingRoom1WithId.setFloorNumber ( 1 );
        physicalMeetingRoom1WithId.setType ( RoomType.PHYSICAL );


        physicalMeetingRoom1Updated = new Room ();
        physicalMeetingRoom1Updated.setRoomId ( 1 );
        physicalMeetingRoom1Updated.setBuilding ( testBuilding1 );
        physicalMeetingRoom1Updated.setCapacity ( 20 );
        physicalMeetingRoom1Updated.setName ( "PHYSICAL MEETING 1" );
        physicalMeetingRoom1Updated.setOccupation ( RoomOccupation.MEETING );
        physicalMeetingRoom1Updated.setRoomAmenities ( amenities );
        physicalMeetingRoom1Updated.setFloorNumber ( 1 );
        physicalMeetingRoom1Updated.setType ( RoomType.PHYSICAL );

        physicalMeetingRoom2 = new Room ();
        physicalMeetingRoom2.setBuilding ( testBuilding1 );
        physicalMeetingRoom2.setCapacity ( 20 );
        physicalMeetingRoom2.setName ( "PHYSICAL MEETING 2" );
        physicalMeetingRoom2.setOccupation ( RoomOccupation.MEETING );
        physicalMeetingRoom2.setRoomAmenities ( amenities );
        physicalMeetingRoom2.setFloorNumber ( 1 );
        physicalMeetingRoom2.setType ( RoomType.PHYSICAL );

        physicalMeetingRoom2WithId = new Room ();
        physicalMeetingRoom2WithId.setRoomId ( 2 );
        physicalMeetingRoom2WithId.setBuilding ( testBuilding1 );
        physicalMeetingRoom2WithId.setCapacity ( 20 );
        physicalMeetingRoom2WithId.setName ( "PHYSICAL MEETING 2" );
        physicalMeetingRoom2WithId.setOccupation ( RoomOccupation.MEETING );
        physicalMeetingRoom2WithId.setRoomAmenities ( amenities );
        physicalMeetingRoom2WithId.setFloorNumber ( 1 );
        physicalMeetingRoom2WithId.setType ( RoomType.PHYSICAL );


        physicalTrainingRoom1 = new Room ();
        physicalTrainingRoom1.setBuilding ( testBuilding1 );
        physicalTrainingRoom1.setCapacity ( 20 );
        physicalTrainingRoom1.setName ( "PHYSICAL TRAINING 1" );
        physicalTrainingRoom1.setOccupation ( RoomOccupation.TRAINING );
        physicalTrainingRoom1.setRoomAmenities ( amenities );
        physicalTrainingRoom1.setFloorNumber ( 2 );
        physicalTrainingRoom1.setType ( RoomType.PHYSICAL );


        physicalTrainingRoom1WithId = new Room ();
        physicalTrainingRoom1WithId.setRoomId ( 3 );
        physicalTrainingRoom1WithId.setBuilding ( testBuilding1 );
        physicalTrainingRoom1WithId.setCapacity ( 20 );
        physicalTrainingRoom1WithId.setName ( "PHYSICAL TRAINING 1" );
        physicalTrainingRoom1WithId.setOccupation ( RoomOccupation.TRAINING );
        physicalTrainingRoom1WithId.setRoomAmenities ( amenities );
        physicalTrainingRoom1WithId.setFloorNumber ( 2 );
        physicalTrainingRoom1WithId.setType ( RoomType.PHYSICAL );


        physicalTrainingRoom2 = new Room ();
        physicalTrainingRoom2.setBuilding ( testBuilding1 );
        physicalTrainingRoom2.setCapacity ( 20 );
        physicalTrainingRoom2.setName ( "PHYSICAL TRAINING 2" );
        physicalTrainingRoom2.setOccupation ( RoomOccupation.TRAINING );
        physicalTrainingRoom2.setRoomAmenities ( amenities );
        physicalTrainingRoom2.setFloorNumber ( 1 );
        physicalTrainingRoom2.setType ( RoomType.PHYSICAL );


        physicalTrainingRoom2WithId = new Room ();
        physicalTrainingRoom2WithId.setRoomId ( 4 );
        physicalTrainingRoom2WithId.setBuilding ( testBuilding1 );
        physicalTrainingRoom2WithId.setCapacity ( 20 );
        physicalTrainingRoom2WithId.setName ( "PHYSICAL TRAINING 2" );
        physicalTrainingRoom2WithId.setOccupation ( RoomOccupation.TRAINING );
        physicalTrainingRoom2WithId.setRoomAmenities ( amenities );
        physicalTrainingRoom2WithId.setFloorNumber ( 1 );
        physicalTrainingRoom2WithId.setType ( RoomType.PHYSICAL );


        ///REMOTE ROOMS

        remoteMeetingRoom1 = new Room ();
        remoteMeetingRoom1.setBuilding ( testBuilding1 );
        remoteMeetingRoom1.setCapacity ( 20 );
        remoteMeetingRoom1.setName ( "REMOTE MEETING 1" );
        remoteMeetingRoom1.setOccupation ( RoomOccupation.MEETING );
        remoteMeetingRoom1.setRoomAmenities ( amenities );
        remoteMeetingRoom1.setFloorNumber ( 0 );
        remoteMeetingRoom1.setType ( RoomType.REMOTE );


        remoteMeetingRoom1WithId = new Room ();
        remoteMeetingRoom1WithId.setRoomId ( 5 );
        remoteMeetingRoom1WithId.setBuilding ( testBuilding1 );
        remoteMeetingRoom1WithId.setCapacity ( 20 );
        remoteMeetingRoom1WithId.setName ( "REMOTE MEETING 1" );
        remoteMeetingRoom1WithId.setOccupation ( RoomOccupation.MEETING );
        remoteMeetingRoom1WithId.setRoomAmenities ( amenities );
        remoteMeetingRoom1WithId.setFloorNumber ( 0 );
        remoteMeetingRoom1WithId.setType ( RoomType.REMOTE );


        remoteMeetingRoom2 = new Room ();
        remoteMeetingRoom2.setBuilding ( testBuilding1 );
        remoteMeetingRoom2.setCapacity ( 20 );
        remoteMeetingRoom2.setName ( "REMOTE MEETING 2" );
        remoteMeetingRoom2.setOccupation ( RoomOccupation.MEETING );
        remoteMeetingRoom2.setRoomAmenities ( amenities );
        remoteMeetingRoom2.setFloorNumber ( 0 );
        remoteMeetingRoom2.setType ( RoomType.REMOTE );

        remoteMeetingRoom2WithId = new Room ();
        remoteMeetingRoom2WithId.setRoomId ( 6 );
        remoteMeetingRoom2WithId.setBuilding ( testBuilding1 );
        remoteMeetingRoom2WithId.setCapacity ( 20 );
        remoteMeetingRoom2WithId.setName ( "REMOTE MEETING 2" );
        remoteMeetingRoom2WithId.setOccupation ( RoomOccupation.MEETING );
        remoteMeetingRoom2WithId.setRoomAmenities ( amenities );
        remoteMeetingRoom2WithId.setFloorNumber ( 0 );
        remoteMeetingRoom2WithId.setType ( RoomType.REMOTE );


        remoteTrainingRoom1 = new Room ();
        remoteTrainingRoom1.setBuilding ( testBuilding1 );
        remoteTrainingRoom1.setCapacity ( 20 );
        remoteTrainingRoom1.setName ( "Remote Training 1" );
        remoteTrainingRoom1.setOccupation ( RoomOccupation.TRAINING );
        remoteTrainingRoom1.setRoomAmenities ( amenities );
        remoteTrainingRoom1.setFloorNumber ( 0 );
        remoteTrainingRoom1.setType ( RoomType.REMOTE );

        remoteTrainingRoom1WithId = new Room ();
        remoteTrainingRoom1WithId.setBuilding ( testBuilding1 );
        remoteTrainingRoom1WithId.setCapacity ( 20 );
        remoteTrainingRoom1WithId.setName ( "Remote Training 1" );
        remoteTrainingRoom1WithId.setOccupation ( RoomOccupation.TRAINING );
        remoteTrainingRoom1WithId.setRoomAmenities ( amenities );
        remoteTrainingRoom1WithId.setFloorNumber ( 0 );
        remoteTrainingRoom1WithId.setType ( RoomType.REMOTE );

        virtualRoomWithId = new Room ();
        virtualRoomWithId.setBuilding ( null );
        virtualRoomWithId.setCapacity ( 20 );
        virtualRoomWithId.setName ( "Virtual Training" );
        virtualRoomWithId.setOccupation ( RoomOccupation.TRAINING );
        virtualRoomWithId.setRoomAmenities ( amenities );
        virtualRoomWithId.setFloorNumber ( 0 );
        virtualRoomWithId.setType ( RoomType.VIRTUAL );


        //Room service
        allRooms = Arrays.asList ( physicalMeetingRoom2WithId,
                physicalMeetingRoom1WithId,
                physicalTrainingRoom1WithId,
                physicalTrainingRoom2WithId,
                remoteMeetingRoom1WithId,
                remoteMeetingRoom2WithId,
                remoteTrainingRoom1WithId

        );

        //Lists
        trainingRoomList = Arrays.asList ( physicalTrainingRoom1WithId, physicalTrainingRoom2WithId, remoteTrainingRoom1WithId );
        meetingRoomList = Arrays.asList ( physicalMeetingRoom1WithId, physicalMeetingRoom2WithId, remoteMeetingRoom1WithId, remoteMeetingRoom2WithId );
        physicalRoomList = Arrays.asList ( physicalMeetingRoom1WithId, physicalMeetingRoom2WithId, physicalTrainingRoom1WithId, physicalTrainingRoom2WithId );
        virtualRoomList = Collections.singletonList ( virtualRoomWithId );
        remoteRoomList = Arrays.asList ( remoteMeetingRoom1WithId, remoteMeetingRoom2WithId, remoteTrainingRoom1WithId );
        physicalTrainingRoomList = Arrays.asList ( physicalTrainingRoom1WithId, physicalTrainingRoom2WithId );
        physicalMeetingRoomList = Arrays.asList ( physicalMeetingRoom1WithId, physicalMeetingRoom2WithId );
        remoteTrainingRoomList = Collections.singletonList ( remoteTrainingRoom1WithId );
        remoteMeetingRoomList = Arrays.asList ( remoteMeetingRoom1WithId, remoteMeetingRoom2WithId );

        testBuilding1.setRooms ( allRooms );
        //TODO: Figure out virtual vs remote rooms
        //virtualTrainingRoomList = Arrays.asList ( v)
//        roomRepository = mock(RoomRepository.class);
        //Stage Mocks
        when ( roomRepository.findAll () ).thenReturn ( allRooms );
        when ( roomRepository.findByOccupation ( RoomOccupation.TRAINING ) ).thenReturn ( trainingRoomList );
        when ( roomRepository.findByOccupation ( RoomOccupation.MEETING ) ).thenReturn ( meetingRoomList );
        when ( roomRepository.findByType ( RoomType.VIRTUAL ) ).thenReturn ( virtualRoomList );
        when ( roomRepository.findByType ( RoomType.PHYSICAL ) ).thenReturn ( physicalRoomList );
        when ( roomRepository.findByType ( RoomType.REMOTE ) ).thenReturn ( remoteRoomList );
        when ( roomRepository.findByTypeAndOccupation ( RoomType.PHYSICAL, RoomOccupation.TRAINING ) ).thenReturn ( physicalTrainingRoomList );
        when ( roomRepository.findByTypeAndOccupation ( RoomType.PHYSICAL, RoomOccupation.MEETING ) ).thenReturn ( physicalMeetingRoomList );
        when ( roomRepository.findByTypeAndOccupation ( RoomType.REMOTE, RoomOccupation.TRAINING ) ).thenReturn ( remoteTrainingRoomList );
        when ( roomRepository.findByTypeAndOccupation ( RoomType.REMOTE, RoomOccupation.MEETING ) ).thenReturn ( remoteMeetingRoomList );
        when ( roomRepository.findById ( 1 ) ).thenReturn ( Optional.of ( physicalMeetingRoom1WithId ) );
        when ( roomRepository.save ( physicalMeetingRoom1 ) ).thenReturn ( physicalMeetingRoom1WithId );
        when ( roomRepository.findById ( 2 ) ).thenReturn ( Optional.of ( physicalMeetingRoom2WithId ) );
        when ( roomRepository.save ( physicalMeetingRoom2 ) ).thenReturn ( physicalMeetingRoom2WithId );
        when ( roomRepository.findById ( 3 ) ).thenReturn ( Optional.of ( physicalTrainingRoom1WithId ) );
        when ( roomRepository.save ( physicalTrainingRoom1 ) ).thenReturn ( physicalTrainingRoom1WithId );
        when ( roomRepository.findById ( 4 ) ).thenReturn ( Optional.of ( physicalTrainingRoom2WithId ) );
        when ( roomRepository.save ( physicalTrainingRoom2 ) ).thenReturn ( physicalTrainingRoom2WithId );
        when ( roomRepository.findById ( 5 ) ).thenReturn ( Optional.of ( remoteMeetingRoom1WithId ) );
        when ( roomRepository.save ( remoteMeetingRoom1 ) ).thenReturn ( remoteMeetingRoom1WithId );
        when ( roomRepository.findById ( 6 ) ).thenReturn ( Optional.of ( remoteMeetingRoom2WithId ) );
        when ( roomRepository.save ( remoteMeetingRoom2 ) ).thenReturn ( remoteMeetingRoom2WithId );
        when ( roomRepository.findById ( 7 ) ).thenReturn ( Optional.of ( remoteTrainingRoom1WithId ) );
        when ( roomRepository.save ( remoteTrainingRoom1 ) ).thenReturn ( remoteTrainingRoom1WithId );
        when ( roomRepository.existsById ( 1 ) ).thenReturn ( true );
        when ( roomRepository.existsById ( 2 ) ).thenReturn ( true );
        when ( roomRepository.getOne ( 1 ) ).thenReturn ( physicalMeetingRoom1WithId );
        when ( roomRepository.getOne ( 2 ) ).thenReturn ( physicalMeetingRoom2WithId );
        when ( roomRepository.existsById ( 500 ) ).thenReturn ( false );
        when ( buildingRepository.getOne ( 1 ) ).thenReturn ( testBuilding1 );
        when ( buildingRepository.existsById ( 1 ) ).thenReturn ( true );
        when ( buildingRepository.existsById ( 500 ) ).thenReturn ( false );


    }

    @Test
    public void whenSavingDtoWithoutId_SavedDtoWithIdIsReturned() {

        assertEquals ( roomDetailsMapper ( physicalMeetingRoom1WithId ), roomService.saveRoom ( physicalMeetingRoom1 ) );

    }

    @Test
    public void whenRequestingAllRooms_AllRoomsReturned() {

        assertEquals ( listMapper ( allRooms ), roomService.getAllRooms () );
    }

    @Test
    public void whenRequestingAllTrainingRooms_AllTrainingRoomsAreReturned() {


        assertEquals ( listMapper ( trainingRoomList ), roomService.getTrainingRooms () );

    }

    @Test
    public void whenRequestingAllMeetingRooms_AllMeetingRoomsAreReturned() {

        assertEquals ( listMapper ( meetingRoomList ), roomService.getMeetingRooms () );

    }

    @Test
    public void whenRequestingVirtualRooms_CorrectNumberOfVirtualRoomsAreReturned() {

        assertEquals ( listMapper ( virtualRoomList ), roomService.getVirtualRooms () );

    }

    @Test
    public void whenRequestingPhysicalRooms_PhysicalRoomsAreReturned() {

        assertEquals ( listMapper ( physicalRoomList ), roomService.getPhysicalRooms () );

    }

    @Test
    public void whenRequestingRemoteRooms_RemoteRoomsAreReturned() {
        assertEquals ( listMapper ( remoteRoomList ), roomService.getRemoteRooms () );
    }

    @Test
    public void whenRequestingPhysicalTrainingRooms_AllPhysicalTrainingRoomsAreReturned() {
        assertEquals ( listMapper ( physicalTrainingRoomList ), roomService.getPhysicalTrainingRooms () );
    }

    @Test
    public void whenRequestingPhysicalMeetingRooms_AllPhysicalMeetingRoomsAreReturned() {
        assertEquals ( listMapper ( physicalMeetingRoomList ), roomService.getPhysicalMeetingRooms () );
    }

//    @Test
//    public void whenRequestingVirtualTrainingRooms_AllVirtualTrainingRoomsAreReturned() {
//
//        assertEquals ( listMapper ( virtualTrainingRoomList ), roomService.getVirtualTrainingRooms () );
//    }

    @Test
    public void whenRequestingRoomById_RoomDetailsDtoCorrespondingToThatIdIsReturned() {

        assertEquals ( roomDetailsMapper ( physicalMeetingRoom1WithId ), roomService.getRoom ( 1 ) );

        assertEquals ( roomDetailsMapper ( physicalMeetingRoom2WithId ), roomService.getRoom ( 2 ) );
    }

    @Test(expected = NotFoundException.class)
    public void whenRequestingRoomByIdThatDoesntExist_notFoundExceptionIsThrown() {

        roomService.getRoom ( 500 );

    }

    @Test(expected = NotFoundException.class)
    public void whenRequestingToDeleteRoomThatDoesntExist_notFoundExceptionIsThrown() {

        roomService.deleteRoom ( 500 );

    }

    @Test(expected = NotFoundException.class)
    public void whenRequestingToUpdateRoomThatDoesntExist_NotFoundExceptionIsThrown() {
        RoomRequestDto request = new RoomRequestDto ();

        roomService.updateRoom ( 500, request );
    }

    @Test(expected = NotFoundException.class)
    public void whenRequestingRoomsFromBuildingThatDoesntExist_NotFoundExceptionIsThrown() {
        roomService.getRoomsByBuildingId ( 500 );
    }

    @Test
    public void whenRequestingRoomsByBuildingId_CorrespondingBuildingsAreReturned() {
        assertEquals ( listMapper ( allRooms ), roomService.getRoomsByBuildingId ( 1 ) );
    }


    @Test
    public void whenRequestingListOfRemoteTrainingRooms_ListOfAllRemoteTrainingRoomsIsReturned() {

        assertEquals ( listMapper ( remoteTrainingRoomList ), roomService.getRemoteTrainingRooms () );
    }

    @Test
    public void whenRequestingListOfRemoteMeetingRooms_ListOfAllRemoteMeetingRoomsIsReturned() {
        assertEquals ( listMapper ( remoteMeetingRoomList ), roomService.getRemoteMeetingRooms () );
    }

    @Test
    public void whenRequestingToUpdateRoom_RequestedRoomIsUpdated() {
        RoomRequestDto request = new RoomRequestDto ();
        request.setName ( physicalMeetingRoom1WithId.getName () );
        request.setAmenities ( physicalMeetingRoom1WithId.getRoomAmenities () );
        request.setCapacity ( physicalMeetingRoom1WithId.getCapacity () );
        request.setOccupation ( physicalMeetingRoom1WithId.getOccupation ().name () );
        request.setFloorNumber ( physicalMeetingRoom1WithId.getFloorNumber () );
        request.setType ( physicalMeetingRoom1.getType ().name () );


        roomService.updateRoom ( 1, request );
        verify ( roomRepository, times ( 1 ) ).existsById ( 1 );
        verify ( roomRepository, times ( 1 ) ).save ( physicalMeetingRoom1WithId );

    }

    @Test
    public void whenRequestingToDeleteValidRoom_RequestedRoomIsDeleted() {
        roomService.deleteRoom ( 1 );
        verify ( roomRepository, times ( 1 ) ).existsById ( 1 );
        verify ( roomRepository, times ( 1 ) ).deleteById ( 1 );

    }


}


