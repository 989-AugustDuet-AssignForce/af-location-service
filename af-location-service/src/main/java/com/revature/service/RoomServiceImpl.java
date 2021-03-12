package com.revature.service;

import com.revature.Exception.NotFoundException;
import com.revature.dto.RoomDetailsDto;
import com.revature.dto.RoomDto;
import com.revature.dto.RoomRequestDto;
import com.revature.model.Building;
import com.revature.model.Room;
import com.revature.repository.BuildingRepository;
import com.revature.repository.RoomRepository;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final BuildingRepository buildingRepository;


    public RoomServiceImpl( RoomRepository roomRepository, BuildingRepository buildingRepository ) {
        this.roomRepository = roomRepository;
        this.buildingRepository = buildingRepository;
    }

    private static RoomDetailsDto detailsMapper( Room room ) {
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

    @Override
    public RoomDetailsDto getRoom( int id ) throws NotFoundException {
        RoomDetailsDto detailsDto;

        if ( roomRepository.existsById ( id ) ) {
            Room room = roomRepository.getOne ( id );
            detailsDto = detailsMapper ( room );
        } else {
            throw new NotFoundException ( "Room with id " + id + " not found." );
        }


        return detailsDto;
    }

    @Override
    public List<RoomDto> getPhysicalMeetingRooms() {
        List<RoomDto> physicalMeetingRooms = new ArrayList<> ();


        roomRepository.findByTypeAndOccupation ( RoomType.PHYSICAL, RoomOccupation.MEETING ).forEach ( room -> {
            RoomDto roomDto = mapDto ( room );
            physicalMeetingRooms.add ( roomDto );
        } );

        return physicalMeetingRooms;
    }

    private RoomDto mapDto( Room room ) {
        RoomDto roomDto = new RoomDto ();
        roomDto.setId ( room.getRoomId () );
        roomDto.setOccupation ( room.getOccupation ().name () );
        roomDto.setType ( room.getType ().name () );
        return roomDto;
    }

    @Override
    public List<RoomDto> getPhysicalTrainingRooms() {
        List<RoomDto> physicalTrainingRooms = new ArrayList<> ();
        roomRepository.findByTypeAndOccupation ( RoomType.PHYSICAL, RoomOccupation.TRAINING ).forEach ( room -> {
            RoomDto roomDto = mapDto ( room );
            physicalTrainingRooms.add ( roomDto );
        } );
        return physicalTrainingRooms;
    }

    @Override
    public List<RoomDto> getRemoteRooms() {
        List<RoomDto> remoteRooms = new ArrayList<> ();
        roomRepository.findByType ( RoomType.REMOTE ).forEach ( room -> {
            RoomDto roomDto = mapDto ( room );
            remoteRooms.add ( roomDto );
        } );

        return remoteRooms;
    }

    @Override
    public List<RoomDto> getPhysicalRooms() {
        List<RoomDto> physicalRooms = new ArrayList<> ();
        roomRepository.findByType ( RoomType.PHYSICAL ).forEach ( room -> {
            RoomDto roomDto = mapDto ( room );
            physicalRooms.add ( roomDto );
        } );
        return physicalRooms;
    }

    @Override
    public List<RoomDto> getVirtualRooms() {
        List<RoomDto> virtualRooms = new ArrayList<> ();
        roomRepository.findByType ( RoomType.VIRTUAL ).forEach ( room -> {
            RoomDto roomDto = mapDto ( room );
            virtualRooms.add ( roomDto );
        } );
        return virtualRooms;
    }

    @Override
    public List<RoomDto> getMeetingRooms() {
        List<RoomDto> meetingRooms = new ArrayList<> ();
        roomRepository.findByOccupation ( RoomOccupation.MEETING ).forEach ( room -> {
            RoomDto roomDto = mapDto ( room );
            meetingRooms.add ( roomDto );
        } );
        return meetingRooms;
    }

    @Override
    public List<RoomDto> getTrainingRooms() {
        List<RoomDto> trainingRooms = new ArrayList<> ();
        roomRepository.findByOccupation ( RoomOccupation.TRAINING ).forEach ( room -> {
            RoomDto roomDto = mapDto ( room );
            trainingRooms.add ( roomDto );
        } );
        return trainingRooms;
    }

    @Override
    public List<RoomDto> getAllRooms() {

        List<RoomDto> allRooms = new ArrayList<> ();
        roomRepository.findAll ().forEach ( room -> {
            RoomDto roomDto = mapDto ( room );
            allRooms.add ( roomDto );
        } );
        return allRooms;
    }

    @Override
    public List<RoomDto> getRemoteTrainingRooms() {
        List<RoomDto> remoteTrainingRooms = new ArrayList<> ();
        roomRepository.findByTypeAndOccupation ( RoomType.REMOTE, RoomOccupation.TRAINING ).forEach ( room -> {
            RoomDto roomDto = mapDto ( room );
            remoteTrainingRooms.add ( roomDto );
        } );
        return remoteTrainingRooms;
    }

    @Override
    public List<RoomDto> getRemoteMeetingRooms() {
        List<RoomDto> remoteMeetingRooms = new ArrayList<> ();
        roomRepository.findByTypeAndOccupation ( RoomType.REMOTE, RoomOccupation.MEETING ).forEach ( room -> {
            RoomDto roomDto = mapDto ( room );
            remoteMeetingRooms.add ( roomDto );
        } );

        return remoteMeetingRooms;
    }

    @Override
    public RoomDetailsDto saveRoom( Room room ) {
        room = roomRepository.save ( room );
        return detailsMapper ( room );
    }

    @Override
    public List<RoomDto> getRoomsByBuildingId( int id ) throws NotFoundException {


        if ( buildingRepository.existsById ( id ) ) {
            Building building = buildingRepository.getOne ( id );
            return building.getRooms ().stream ().map ( this::mapDto ).collect ( Collectors.toList () );
        } else {
            throw new NotFoundException ( "Building with id " + id + " not found. Requested rooms not deliverable." );
        }

    }

    // if doesn't exist throw not found exception.


    @Override
    public void deleteRoom( int id ) throws NotFoundException {
        if ( roomRepository.existsById ( id ) ) {
            roomRepository.deleteById ( id );
        } else
            throw new NotFoundException ( "Building with id " + id + " not found. Requested rooms not deliverable." );
    }


    @Override
    public void updateRoom( int id, RoomRequestDto roomRequestDto ) throws NotFoundException {
        //can't set new building using request DTO
        //could eventually null check fields for patch requests

        if ( roomRepository.existsById ( id ) ) {
            Room room = roomRepository.getOne ( id );
            room.setType ( RoomType.valueOf ( roomRequestDto.getType () ) );
            room.setOccupation ( RoomOccupation.valueOf ( roomRequestDto.getOccupation () ) );
            room.setCapacity ( roomRequestDto.getCapacity () );
            room.setFloorNumber ( roomRequestDto.getFloorNumber () );
            room.setRoomAmenities ( roomRequestDto.getAmenities () );
            room.setName ( roomRequestDto.getName () );
            roomRepository.save ( room );
        } else {
            throw new NotFoundException ( "Room with id " + id + " not found. Requested changes not made." );
        }


    }


}