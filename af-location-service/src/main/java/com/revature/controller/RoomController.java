package com.revature.controller;

import com.revature.Exception.NotFoundException;
import com.revature.dto.RoomRequestDto;
import com.revature.model.Room;
import com.revature.repository.BuildingRepository;
import com.revature.service.RoomService;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

@CrossOrigin
public class RoomController {

    private RoomService roomService;
    private BuildingRepository buildingRepository;

    @Autowired
    public RoomController( RoomService roomService, BuildingRepository buildingRepository ) {
        this.roomService = roomService;
        this.buildingRepository = buildingRepository;
    }

    @GetMapping(value = "/room/{id}", produces = "application/json")
    public ResponseEntity<Object> getRoom( @PathVariable int id ) {
        try {
            return new ResponseEntity<> ( roomService.getRoom ( id ), HttpStatus.OK );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<> ( e.getMessage (), HttpStatus.NOT_FOUND );

        }

    }


    @PostMapping(value = "/buildings/{buildingId}/room", produces = "application/json")
    public ResponseEntity<Object> createRoom( @RequestBody RoomRequestDto requestDto, @PathVariable int buildingId ) {
        Room room = new Room ();

        if ( buildingRepository.existsById ( buildingId ) ) {

            room.setName ( requestDto.getName () );
            room.setCapacity ( requestDto.getCapacity () );
            room.setFloorNumber ( requestDto.getFloorNumber () );
            room.setType ( RoomType.valueOf ( requestDto.getType () ) );
            room.setOccupation ( RoomOccupation.valueOf ( requestDto.getOccupation () ) );
            room.setBuilding ( buildingRepository.getOne ( buildingId ) );
            room.setRoomAmenities ( requestDto.getAmenities () );

            return new ResponseEntity<> ( roomService.saveRoom ( room ), HttpStatus.CREATED );

        } else {
            return new ResponseEntity<> ( "Building with id " + buildingId + " not found", HttpStatus.NOT_FOUND );
        }


    }

    @GetMapping(value = "/rooms", produces = "application/json")
    public ResponseEntity<Object> getAllRooms() {
        return new ResponseEntity<> ( roomService.getAllRooms (), HttpStatus.OK );
    }

    @GetMapping(value = "/rooms/remote/training", produces = "application/json")
    public ResponseEntity<Object> getRemoteTrainingRooms() {
        return new ResponseEntity<> ( roomService.getRemoteTrainingRooms (), HttpStatus.OK );
    }

    @GetMapping(value = "/rooms/remote/meeting", produces = "application/json")
    public ResponseEntity<Object> getRemoteMeetingRooms() {
        return new ResponseEntity<> ( roomService.getRemoteMeetingRooms (), HttpStatus.OK );
    }

    @GetMapping(value = "/rooms/physical/meeting", produces = "application/json")
    public ResponseEntity<Object> getPhysicalMeetingRooms() {
        return new ResponseEntity<> ( roomService.getPhysicalMeetingRooms (), HttpStatus.OK );
    }

    @GetMapping(value = "/rooms/physical/training", produces = "application/json")
    public ResponseEntity<Object> getPhysicalTrainingRooms() {
        return new ResponseEntity<> ( roomService.getPhysicalTrainingRooms (), HttpStatus.OK );
    }

    @GetMapping(value = "/rooms/remote", produces = "application/json")
    public ResponseEntity<Object> getRemoteRooms() {
        return new ResponseEntity<> ( roomService.getRemoteRooms (), HttpStatus.OK );
    }

    @GetMapping(value = "/rooms/physical", produces = "application/json")
    public ResponseEntity<Object> getPhysicalRooms() {
        return new ResponseEntity<> ( roomService.getPhysicalRooms (), HttpStatus.OK );
    }

    @GetMapping(value = "/rooms/{buildingId}", produces = "application/json")
    public ResponseEntity<Object> getRoomsByBuildingId( @PathVariable int buildingId ) {
        if ( buildingRepository.existsById ( buildingId ) ) {
            return new ResponseEntity<> ( roomService.getRoomsByBuildingId ( buildingId ), HttpStatus.OK );
        } else {
            return new ResponseEntity<> ( "Building with id " + buildingId + " not found", HttpStatus.NOT_FOUND );
        }
    }

    @GetMapping(value = "/rooms/meeting", produces = "application/json")
    public ResponseEntity<Object> getMeetingRooms() {
        return new ResponseEntity<> ( roomService.getMeetingRooms (), HttpStatus.OK );
    }

    @DeleteMapping(value = "/room/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteRoom( @PathVariable int id ) {

        try {
            roomService.deleteRoom ( id );
            return new ResponseEntity<> ( HttpStatus.NO_CONTENT );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<> ( e.getMessage (), HttpStatus.NOT_FOUND );

        }
    }

    @PutMapping(value = "/room/{id}", produces = "application/json")
    public ResponseEntity<Object> updateRoom( @PathVariable int id, @RequestBody RoomRequestDto roomRequestDto ) {

        try {
            roomService.updateRoom ( id, roomRequestDto );
            return new ResponseEntity<> ( HttpStatus.NO_CONTENT );
        } catch ( NotFoundException e ) {
            return new ResponseEntity<> ( e.getMessage (), HttpStatus.NOT_FOUND );

        }
    }


}
