package com.revature.repository;

import com.revature.model.Building;
import com.revature.model.Room;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByType( RoomType roomType );

    List<Room> findByOccupation( RoomOccupation occupation );

    List<Room> findByTypeAndOccupation( RoomType type, RoomOccupation occupation );

    List<Room> findByBuildingAndFloorNumber( Building building, int floorNumber );

}
