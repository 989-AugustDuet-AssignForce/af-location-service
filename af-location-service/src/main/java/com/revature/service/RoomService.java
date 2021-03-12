package com.revature.service;

import com.revature.Exception.NotFoundException;
import com.revature.dto.RoomDetailsDto;
import com.revature.dto.RoomDto;
import com.revature.dto.RoomRequestDto;
import com.revature.model.Room;

import java.util.List;
public interface RoomService {


    RoomDetailsDto getRoom( int id ) throws NotFoundException;

    List<RoomDto> getPhysicalMeetingRooms();


    List<RoomDto> getPhysicalTrainingRooms();

    List<RoomDto> getRemoteRooms();

    List<RoomDto> getPhysicalRooms();

    List<RoomDto> getVirtualRooms();

    List<RoomDto> getMeetingRooms();

    List<RoomDto> getTrainingRooms();

    List<RoomDto> getAllRooms();

    List<RoomDto> getRemoteTrainingRooms();

    List<RoomDto> getRemoteMeetingRooms();

    RoomDetailsDto saveRoom( Room room);

    List<RoomDto> getRoomsByBuildingId(int id) throws NotFoundException;

    void deleteRoom(int id) throws NotFoundException;

    void updateRoom( int id, RoomRequestDto roomRequestDto ) throws NotFoundException;


}
