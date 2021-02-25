package com.revature.service;

import com.revature.dto.RoomDto;
import com.revature.model.Building;
import com.revature.model.Location;
import com.revature.model.Room;
import com.revature.repository.RoomRepository;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@SpringBootTest
public class RoomServiceTests {

    @Test
    public void W
    @MockBean
    private RoomRepository roomRepository;
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

    private static RoomDto mapRoomModelToDto( Room room ) {
        RoomDto roomDto = new RoomDto ();
        roomDto.id = room.getId ();
        roomDto.capacity = room.getCapacity ();
        roomDto.name = room.getName ();
        roomDto.occupation = room.getOccupation ().name ();
        roomDto.type = room.getType ().name ();
        return roomDto;
    }

    private static List<RoomDto> listMapper( List<Room> list ) {

        return list.stream ().map ( RoomServiceTests::mapRoomModelToDto ).collect ( Collectors.toList () );
    }

    @Before
    //Setup mocks
    public void setUp() {


        //Buildings

        testBuilding1 = new Building ();
        testBuilding1.setCity ( "Test City" );
        testBuilding1.setLocation ( testLocation1 );
        testBuilding1.setId ( 1 );

        //LOCATIONS


        testLocation1 = new Location ();
        testLocation1.setId ( 1 );
        testLocation1.setCity ( "Test City" );
        testLocation1.setState ( "Test State" );
        testLocation1.setBuildings ( Collections.singletonList ( testBuilding1 ) );


        //PHYSICAL ROOMS

        physicalMeetingRoom1 = new Room ();
        Map<String, Integer> amenities = new HashMap<> ();
        amenities.put ( "HD PROJECTORS", 1 );

        physicalMeetingRoom1.setBuilding ( testBuilding1 );
        physicalMeetingRoom1.setCapacity ( 20 );
        physicalMeetingRoom1.setName ( "PHYSICAL MEETING 1" );
        physicalMeetingRoom1.setOccupation ( RoomOccupation.MEETING );
        physicalMeetingRoom1.setRoomAmenities ( amenities );
        physicalMeetingRoom1.setFloorNumber ( 1 );
        physicalMeetingRoom1.setType ( RoomType.PHYSICAL );

        physicalMeetingRoom1WithId = new Room ();
        physicalMeetingRoom1WithId.setId ( 1 );
        physicalMeetingRoom1WithId.setBuilding ( testBuilding1 );
        physicalMeetingRoom1WithId.setCapacity ( 20 );
        physicalMeetingRoom1WithId.setName ( "PHYSICAL MEETING 1" );
        physicalMeetingRoom1WithId.setOccupation ( RoomOccupation.MEETING );
        physicalMeetingRoom1WithId.setRoomAmenities ( amenities );
        physicalMeetingRoom1WithId.setFloorNumber ( 1 );
        physicalMeetingRoom1WithId.setType ( RoomType.PHYSICAL );

        physicalMeetingRoom2 = new Room ();
        physicalMeetingRoom2.setBuilding ( testBuilding1 );
        physicalMeetingRoom2.setCapacity ( 20 );
        physicalMeetingRoom2.setName ( "PHYSICAL MEETING 2" );
        physicalMeetingRoom2.setOccupation ( RoomOccupation.MEETING );
        physicalMeetingRoom2.setRoomAmenities ( amenities );
        physicalMeetingRoom2.setFloorNumber ( 1 );
        physicalMeetingRoom2.setType ( RoomType.PHYSICAL );

        physicalMeetingRoom2WithId = new Room ();
        physicalMeetingRoom2WithId.setId ( 2 );
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
        physicalTrainingRoom1WithId.setId ( 3 );
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
        physicalTrainingRoom2WithId.setId ( 4 );
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
        remoteMeetingRoom1WithId.setId ( 5 );
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
        remoteMeetingRoom2WithId.setId ( 6 );
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


        allRooms = Arrays.asList ( physicalMeetingRoom2WithId,
                physicalMeetingRoom1WithId,
                physicalTrainingRoom1WithId,
                physicalTrainingRoom2WithId,
                remoteMeetingRoom1WithId,
                remoteMeetingRoom2WithId,
                remoteTrainingRoom1WithId

        );

        trainingRoomList = Arrays.asList ( physicalTrainingRoom1WithId, physicalTrainingRoom2WithId, remoteTrainingRoom1WithId );
        meetingRoomList = Arrays.asList ( physicalMeetingRoom1WithId, physicalMeetingRoom2WithId, remoteMeetingRoom1WithId, remoteMeetingRoom2WithId );
        physicalRoomList = Arrays.asList ( physicalMeetingRoom1WithId, physicalMeetingRoom2WithId, physicalTrainingRoom1WithId, physicalTrainingRoom2WithId );
        virtualRoomList = Collections.singletonList ( virtualRoomWithId );
        remoteRoomList = Arrays.asList ( remoteMeetingRoom1WithId, remoteMeetingRoom2WithId, remoteTrainingRoom1WithId );
        physicalTrainingRoomList = Arrays.asList ( physicalTrainingRoom1WithId, physicalTrainingRoom2WithId );
        physicalMeetingRoomList = Arrays.asList ( physicalMeetingRoom1WithId, physicalMeetingRoom2WithId );

        //Stage Mocks
        when ( roomRepository.findAll () ).thenReturn ( allRooms );
        when ( roomRepository.findByOccupation ( RoomOccupation.TRAINING ) ).thenReturn ( trainingRoomList );
        when ( roomRepository.findByOccupation ( RoomOccupation.MEETING ) ).thenReturn ( meetingRoomList );
        when ( roomRepository.findByType ( RoomType.VIRTUAL ) ).thenReturn ( virtualRoomList );
        when ( roomRepository.findByType ( RoomType.PHYSICAL ) ).thenReturn ( physicalRoomList );
        when ( roomRepository.findByType ( RoomType.REMOTE ) ).thenReturn ( remoteRoomList );
        when ( roomRepository.findByTypeAndOccupation ( RoomType.PHYSICAL, RoomOccupation.TRAINING ) ).thenReturn ( physicalTrainingRoomList );
        when ( roomRepository.findByTypeAndOccupation ( RoomType.PHYSICAL, RoomOccupation.MEETING )).thenReturn (physicalMeetingRoomList );
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


    }

    @Test
    public void whenSavingDtoWithoutId_SavedDtoWithIdIsReturned() {
        //TODO: CONFIRM THE ID IS CREATED AND THE ENTITY IS PROPERLY SAVED. CURRENTLY SIGNATURE IS RETURNING VOID

    }

    @Test
    public void whenRequestingAllRooms_ALlRoomsReturned() {

        assertEquals ( listMapper ( allRooms ), roomService.getAllRooms () );
    }

    @Test
    public void whenRequestingAllTrainingRooms_AllTrainingRoomsAreReturned() {


        assertEquals ( listMapper ( trainingRoomList ), roomService.getTrainingRooms () );

    }

    @Test
    public void whenRequestingALlMeetingRooms_AllMeetingRoomsAreReturned() {

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

    @Test
    public void whenRequestingVirtualTrainingRooms_AllVirtualTrainingRoomsAreReturned() {

        assertEquals (listMapper())
    }

}
