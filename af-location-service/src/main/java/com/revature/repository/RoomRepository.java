package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {

}
