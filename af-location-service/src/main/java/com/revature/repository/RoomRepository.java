package com.revature.repository;

import com.revature.model.Room;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository <Room, Integer> {


    List<Room> findByOccupation(RoomOccupation training);

    List<Room> findByType(RoomType virtual);

    List<Room> findByTypeAndOccupation(RoomType physical, RoomOccupation training);
  
}
