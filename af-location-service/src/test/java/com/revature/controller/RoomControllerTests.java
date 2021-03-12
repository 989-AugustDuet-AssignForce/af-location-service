package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.revature.Exception.NotFoundException;
import com.revature.dto.RoomDetailsDto;
import com.revature.dto.RoomDto;
import com.revature.dto.RoomRequestDto;
import com.revature.model.Building;
import com.revature.model.Location;
import com.revature.model.Room;
import com.revature.repository.BuildingRepository;
import com.revature.service.RoomService;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomController.class)
public class RoomControllerTests {


    private static RoomRequestDto unsavedRoom1 = new RoomRequestDto ();
    private final ObjectMapper mapper = Jackson2ObjectMapperBuilder.json ().build ();
    private final ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter ();
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private RoomService roomService;
    @MockBean
    private BuildingRepository buildingRepository;
    private Location testLocation1;
    private Building testBuilding1;
    private List<RoomDto> allRoomsDTOs;
    private List<RoomDto> remoteTrainingRoomDtos;
    private List<RoomDto> remoteMeetingRoomDtos;
    private List<RoomDto> physicalMeetingRoomDtos;
    private List<RoomDto> physicalTrainingRoomDtos;
    private List<RoomDto> meetingRoomDtos;
    private List<RoomDto> remoteRoomDtos;
    private List<RoomDto> physicalRoomDtos;
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

    private static RoomRequestDto roomRequestMapper( Room room ) {
        RoomRequestDto requestDto = new RoomRequestDto ();
        requestDto.setName ( room.getName () );
        requestDto.setCapacity ( room.getCapacity () );
        requestDto.setFloorNumber ( room.getFloorNumber () );
        requestDto.setOccupation ( room.getOccupation ().name () );
        requestDto.setType ( room.getType ().name () );
        requestDto.setAmenities ( room.getRoomAmenities () );

        return requestDto;
    }

    private static RoomDto roomMapper( Room room ) {
        RoomDto roomDto = new RoomDto ();
        roomDto.setId ( room.getRoomId () );
        roomDto.setOccupation ( room.getOccupation ().name () );
        roomDto.setType ( room.getType ().name () );
        return roomDto;
    }


    @Before
    public void setUp() throws Exception {

        testLocation1 = new Location ();
        testLocation1.setLocationId ( 1 );
        testLocation1.setCity ( "Test City" );
        testLocation1.setState ( "Test State" );

        testBuilding1 = new Building ();
        testBuilding1.setCity ( "Test City" );
        testBuilding1.setLocation ( testLocation1 );
        testBuilding1.setBuildingId ( 1 );

        Set<String> amenities = new HashSet<> ( 2 );
        amenities.addAll ( Collections.singletonList ( "AIR CONDITIONING" ) );
        physicalMeetingRoom1 = new Room ();
        physicalMeetingRoom1.setRoomId ( 1 );
        physicalMeetingRoom1.setBuilding ( testBuilding1 );
        physicalMeetingRoom1.setCapacity ( 20 );
        physicalMeetingRoom1.setName ( "PHYSICAL MEETING 1" );
        physicalMeetingRoom1.setOccupation ( RoomOccupation.MEETING );
        physicalMeetingRoom1.setRoomAmenities ( amenities );
        physicalMeetingRoom1.setFloorNumber ( 1 );
        physicalMeetingRoom1.setType ( RoomType.PHYSICAL );

        Room physicalMeetingRoom1NoId = new Room ();
        physicalMeetingRoom1NoId.setBuilding ( testBuilding1 );
        physicalMeetingRoom1NoId.setCapacity ( 20 );
        physicalMeetingRoom1NoId.setName ( "PHYSICAL MEETING 1" );
        physicalMeetingRoom1NoId.setOccupation ( RoomOccupation.MEETING );
        physicalMeetingRoom1NoId.setRoomAmenities ( amenities );
        physicalMeetingRoom1NoId.setFloorNumber ( 1 );
        physicalMeetingRoom1NoId.setType ( RoomType.PHYSICAL );

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
        allRoomsDTOs = allRooms.stream ().map ( RoomControllerTests::roomMapper ).collect ( Collectors.toList () );
        remoteTrainingRoomDtos = remoteTrainingRoomList.stream ().map ( RoomControllerTests::roomMapper ).collect ( Collectors.toList () );
        remoteMeetingRoomDtos = remoteMeetingRoomList.stream ().map ( RoomControllerTests::roomMapper ).collect ( Collectors.toList () );
        physicalMeetingRoomDtos = physicalMeetingRoomList.stream ().map ( RoomControllerTests::roomMapper ).collect ( Collectors.toList () );
        physicalTrainingRoomDtos = physicalTrainingRoomList.stream ().map ( RoomControllerTests::roomMapper ).collect ( Collectors.toList () );
        remoteRoomDtos = remoteRoomList.stream ().map ( RoomControllerTests::roomMapper ).collect ( Collectors.toList () );
        physicalRoomDtos = physicalRoomList.stream ().map ( RoomControllerTests::roomMapper ).collect ( Collectors.toList () );
        meetingRoomDtos = meetingRoomList.stream ().map ( RoomControllerTests::roomMapper ).collect ( Collectors.toList () );

        when ( roomService.getRoom ( 1 ) ).thenReturn ( roomDetailsMapper ( physicalMeetingRoom1WithId ) );
        when ( buildingRepository.existsById ( 1 ) ).thenReturn ( true );
        when ( buildingRepository.existsById ( 500 ) ).thenReturn ( false );
        when ( buildingRepository.getOne ( 1 ) ).thenReturn ( testBuilding1 );
        when ( roomService.saveRoom ( physicalMeetingRoom1 ) ).thenReturn ( roomDetailsMapper ( physicalMeetingRoom1WithId ) );
        when ( roomService.getAllRooms () ).thenReturn ( allRoomsDTOs );
        when ( roomService.getRemoteTrainingRooms () ).thenReturn ( remoteTrainingRoomDtos );
        when ( roomService.getRemoteMeetingRooms () ).thenReturn ( remoteMeetingRoomDtos );
        when ( roomService.getPhysicalMeetingRooms () ).thenReturn ( physicalMeetingRoomDtos );
        when ( roomService.getPhysicalTrainingRooms () ).thenReturn ( physicalTrainingRoomDtos );
        when ( roomService.getPhysicalRooms () ).thenReturn ( physicalRoomDtos );
        when ( roomService.getRemoteRooms () ).thenReturn ( remoteRoomDtos );
        when ( roomService.getRoomsByBuildingId ( 1 ) ).thenReturn ( allRoomsDTOs );
        when ( roomService.getRoom ( 500 ) ).thenThrow ( new NotFoundException ( "Room with id " + 500 + " not found." ) );
        doThrow ( new NotFoundException ( "Room with id " + 500 + " not found." ) ).when ( roomService ).deleteRoom ( 500 );
        when ( roomService.getMeetingRooms () ).thenReturn ( meetingRoomDtos );
        doNothing ().when ( roomService ).updateRoom ( 1, roomRequestMapper ( physicalMeetingRoom1WithId ) );
        doThrow ( new NotFoundException ( "Room with id " + 500 + " not found." ) ).when ( roomService ).updateRoom ( 500, roomRequestMapper ( physicalMeetingRoom1WithId ) );

    }


//    @Test
//    public void whenProvidingBadRoomModel_BadRequestIsReturnedWithCode400() throws Exception {
//
//
//        //Will get buildling id from path variable.
//
//    }

    @Test
    public void whenGettingRoomWithValidId_RequestedRoomDetailsDtoIsReturnedWithCode200() throws Exception {


        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/room/1" );
        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isOk () ).andReturn ();

        String mappedResult = result.getResponse ().getContentAsString ();

        RoomDetailsDto resultDto = mapper.readValue ( mappedResult, RoomDetailsDto.class );

        assertEquals ( roomDetailsMapper ( physicalMeetingRoom1WithId ), resultDto );

    }

    @Test
    public void whenGettingRoomWithInvalidId_404IsReturned() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/room/500" );
        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isNotFound () ).andReturn ();
        verify ( roomService, times ( 1 ) ).getRoom ( 500 );
        assertEquals ( 404, result.getResponse ().getStatus () );
    }


    @Test
    public void whenGettingPhysicalMeetingRooms_AllPhysicalMeetingRoomsAreReturnedWithCode200() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/rooms/physical/meeting" );


        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isOk () ).andReturn ();

        String mappedResult = result.getResponse ().getContentAsString ();

        RoomDto[] resultArray = mapper.readValue ( mappedResult, RoomDto[].class );

        assertTrue ( resultArray.length > 0 );
        assertEquals ( resultArray.length, physicalMeetingRoomDtos.size () );
        verify ( roomService, times ( 1 ) ).getPhysicalMeetingRooms ();
        for ( RoomDto dto : resultArray ) {
            assertTrue ( physicalMeetingRoomDtos.contains ( dto ) );
        }
    }

    @Test
    public void whenGettingPhysicalTrainingRooms_AllPhysicalTrainingRoomsAreReturnedWithCode200() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/rooms/physical/training" );


        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isOk () ).andReturn ();

        String mappedResult = result.getResponse ().getContentAsString ();

        RoomDto[] resultArray = mapper.readValue ( mappedResult, RoomDto[].class );

        assertTrue ( resultArray.length > 0 );
        assertEquals ( resultArray.length, physicalTrainingRoomDtos.size () );
        verify ( roomService, times ( 1 ) ).getPhysicalTrainingRooms ();
        for ( RoomDto dto : resultArray ) {
            assertTrue ( physicalTrainingRoomDtos.contains ( dto ) );
        }
    }

    @Test
    public void whenGettingRemoteRooms_AllRemoteRoomsAreReturnedWithCode200() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/rooms/remote" );


        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isOk () ).andReturn ();

        String mappedResult = result.getResponse ().getContentAsString ();

        RoomDto[] resultArray = mapper.readValue ( mappedResult, RoomDto[].class );

        assertTrue ( resultArray.length > 0 );
        assertEquals ( resultArray.length, remoteRoomDtos.size () );
        verify ( roomService, times ( 1 ) ).getRemoteRooms ();
        for ( RoomDto dto : resultArray ) {
            assertTrue ( remoteRoomDtos.contains ( dto ) );
        }
    }

    @Test
    public void whenGettingPhysicalRooms_AllPhysicalRoomsAreReturnedWithCode200() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/rooms/physical" );


        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isOk () ).andReturn ();

        String mappedResult = result.getResponse ().getContentAsString ();

        RoomDto[] resultArray = mapper.readValue ( mappedResult, RoomDto[].class );

        assertTrue ( resultArray.length > 0 );
        assertEquals ( resultArray.length, physicalRoomDtos.size () );
        verify ( roomService, times ( 1 ) ).getPhysicalRooms ();
        for ( RoomDto dto : resultArray ) {
            assertTrue ( physicalRoomDtos.contains ( dto ) );
        }
    }

    @Test
    public void whenGettingMeetingRooms_AllMeetingRoomsAreReturnedWithCode200() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/rooms/meeting" );


        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isOk () ).andReturn ();

        String mappedResult = result.getResponse ().getContentAsString ();

        RoomDto[] resultArray = mapper.readValue ( mappedResult, RoomDto[].class );

        assertTrue ( resultArray.length > 0 );
        assertEquals ( resultArray.length, meetingRoomDtos.size () );
        verify ( roomService, times ( 1 ) ).getMeetingRooms ();
        for ( RoomDto dto : resultArray ) {
            assertTrue ( meetingRoomDtos.contains ( dto ) );
        }
    }


    @Test
    public void whenGettingAllRooms_AllRoomsAreReturnedWithCode200() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/rooms" );
        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isOk () ).andReturn ();

        String mappedResult = result.getResponse ().getContentAsString ();

        RoomDto[] resultArray = mapper.readValue ( mappedResult, RoomDto[].class );


        assertEquals ( allRoomsDTOs.size (), resultArray.length );

        assertTrue ( resultArray.length > 0 );
        verify ( roomService, times ( 1 ) ).getAllRooms ();

        for ( RoomDto dto : resultArray ) {
            assertTrue ( allRoomsDTOs.contains ( dto ) );
        }

    }

    @Test
    public void whenGettingRemoteTrainingRooms_AllRemoteTrainingRoomsAreReturnedWithCode200() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/rooms/remote/training" );

        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isOk () ).andReturn ();

        String mappedResult = result.getResponse ().getContentAsString ();

        RoomDto[] resultArray = mapper.readValue ( mappedResult, RoomDto[].class );

        assertTrue ( resultArray.length > 0 );
        assertEquals ( resultArray.length, remoteTrainingRoomDtos.size () );
        verify ( roomService, times ( 1 ) ).getRemoteTrainingRooms ();

        for ( RoomDto dto : resultArray ) {
            assertTrue ( remoteTrainingRoomDtos.contains ( dto ) );
        }


    }

    @Test
    public void whenGettingRemoteMeetingRooms_AllRemoteMeetingRoomsAreReturnedWithCode200() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/rooms/remote/meeting" );

        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isOk () ).andReturn ();

        String mappedResult = result.getResponse ().getContentAsString ();

        RoomDto[] resultArray = mapper.readValue ( mappedResult, RoomDto[].class );

        assertTrue ( resultArray.length > 0 );
        verify ( roomService, times ( 1 ) ).getRemoteMeetingRooms ();
        for ( RoomDto dto : resultArray ) {
            assertTrue ( remoteMeetingRoomDtos.contains ( dto ) );
        }


    }

    @Test
    public void whenSavingANewRoom_NewRoomIsSavedAndResponseIsReturnedWithCode201() throws Exception {


        String mappedRequestObject = writer.writeValueAsString ( roomRequestMapper ( physicalMeetingRoom1 ) );

        RequestBuilder request = MockMvcRequestBuilders.post ( "/api/location/building/1/room" )
                .contentType ( APPLICATION_JSON )
                .content ( mappedRequestObject )
                .characterEncoding ( "utf-8" );

        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isCreated () ).andReturn ();

        String mappedResult = result.getResponse ().getContentAsString ();

        RoomDetailsDto resultObject = mapper.readValue ( mappedResult, RoomDetailsDto.class );
        verify ( roomService, times ( 1 ) ).saveRoom ( physicalMeetingRoom1 );
        assertEquals ( roomDetailsMapper ( physicalMeetingRoom1WithId ), resultObject );


    }

//    @Test
//    public void whenSavingABadRoom_ExceptionIsThrownAndResponseIsReturnedWithCode400() {
//        //save for next iteration
//    }

    @Test
    public void whenGettingRoomsByBuildingId_RoomsForThatBuildingAreReturnedWithCode200() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/rooms/1" );

        MvcResult result = mockMvc.perform ( request ).andExpect ( status ().isOk () ).andReturn ();

        String mappedResult = result.getResponse ().getContentAsString ();

        RoomDto[] resultArray = mapper.readValue ( mappedResult, RoomDto[].class );

        assertTrue ( resultArray.length > 0 );
        verify ( roomService, times ( 1 ) ).getRoomsByBuildingId ( 1 );
        for ( RoomDto dto : resultArray ) {
            assertTrue ( allRoomsDTOs.contains ( dto ) );
        }

    }

    @Test
    public void whenGettingRoomsByInvalidBuildingId_ExceptionIsThrowAndResponseIsReturnedWithCode404() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get ( "/api/location/rooms/500" );

        mockMvc.perform ( request ).andExpect ( status ().isNotFound () ).andReturn ();
        verify ( buildingRepository, times ( 1 ) ).existsById ( 500 );

    }


    @Test
    public void whenDeletingRoomWithValidId_RoomIsDeletedCode204IsReturned() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.delete ( "/api/location/room/1" );

        mockMvc.perform ( request ).andExpect ( status ().isNoContent () ).andReturn ();
        verify ( roomService, times ( 1 ) ).deleteRoom ( 1 );

    }

    @Test
    public void whenDeletingRoomWithInvalidId_NotFoundErrorIsThrownAndCode404IsReturned() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete ( "/api/location/room/500" );

        mockMvc.perform ( request ).andExpect ( status ().isNotFound () ).andReturn ();
        verify ( roomService, times ( 1 ) ).deleteRoom ( 500 );


    }

    @Test
    public void whenUpdatingRoomWithValidId_RoomIsUpdatedAndCode204IsReturned() throws Exception {
        String mappedRequestObject = writer.writeValueAsString ( roomRequestMapper ( physicalMeetingRoom1WithId ) );

        RequestBuilder request = MockMvcRequestBuilders.put ( "/api/location/room/1" )
                .contentType ( APPLICATION_JSON )
                .content ( mappedRequestObject )
                .characterEncoding ( "utf-8" );

        mockMvc.perform ( request ).andExpect ( status ().isNoContent () ).andReturn ();
        verify ( roomService, times ( 1 ) ).updateRoom ( 1, roomRequestMapper ( physicalMeetingRoom1WithId ) );


    }

    @Test
    public void whenUpdatingRoomWithInvalidId_NotFoundErrorIsThrownAndCode404IsReturned() throws Exception {

        String mappedRequestObject = writer.writeValueAsString ( roomRequestMapper ( physicalMeetingRoom1WithId ) );

        RequestBuilder request = MockMvcRequestBuilders.put ( "/api/location/room/500" )
                .contentType ( APPLICATION_JSON )
                .content ( mappedRequestObject )
                .characterEncoding ( "utf-8" );

        mockMvc.perform ( request ).andExpect ( status ().isNotFound () ).andReturn ();
        verify ( roomService, times ( 1 ) ).updateRoom ( 500, roomRequestMapper ( physicalMeetingRoom1WithId ) );


    }


}
