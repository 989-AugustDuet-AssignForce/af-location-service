package com.revature.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;
import com.sun.istack.NotNull;

import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
	private int roomId;


	@NotNull
	private String name;


	@NotNull
	private RoomType type;


	@NotNull
	private RoomOccupation occupation;


	@NotNull
	private int capacity;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "building_id", nullable = false)
	private Building building;

	@ElementCollection
	private Set<String> roomAmenities;

	@Column(name = "floor_number")
	@NotNull
	private int floorNumber;

	public int getRoomId() {

		return roomId;
	}

	public void setRoomId(int roomId) {

		this.roomId = roomId;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public RoomType getType() {

		return type;
	}

	public void setType(RoomType type) {

		this.type = type;
	}

	public RoomOccupation getOccupation() {

		return occupation;
	}

	public void setOccupation(RoomOccupation occupation) {

		this.occupation = occupation;
	}

	public int getCapacity() {

		return capacity;
	}

	public void setCapacity(int capacity) {

		this.capacity = capacity;
	}

	public Building getBuilding() {

		return building;
	}

	public void setBuilding(Building building) {

		this.building = building;
	}

	public Set<String> getRoomAmenities() {

		return roomAmenities;
	}

	public void setRoomAmenities(Set<String> roomAmenities) {

		this.roomAmenities = roomAmenities;
	}

	public int getFloorNumber() {

		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {

		this.floorNumber = floorNumber;
	}

	public Room() {

	}


	public Room(int roomId, String name, RoomType type, RoomOccupation occupation,
				int capacity, Building building, Set<String> roomAmenities,
				int floorNumber) {

		this.roomId = roomId;
		this.name = name;
		this.type = type;
		this.occupation = occupation;
		this.capacity = capacity;
		this.building = building;
		this.roomAmenities = roomAmenities;
		this.floorNumber = floorNumber;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((building == null) ? 0 : building.hashCode());
		result = prime * result + capacity;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((occupation == null) ? 0 : occupation.hashCode());
		result = prime * result + roomId;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + (( roomAmenities == null) ? 0: roomAmenities.hashCode());
		result = prime * result + floorNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (building == null) {
			if (other.building != null)
				return false;
		} else if (!building.equals(other.building))
			return false;
		if (capacity != other.capacity)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (occupation != other.occupation)
			return false;
		if (roomId != other.roomId)
			return false;
		if (type != other.type)
			return false;
		if(!roomAmenities.equals(other.roomAmenities))
			return false;
		if(floorNumber != other.floorNumber)
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "Room [roomId=" + roomId + ", name=" + name + ", type=" + type + ", occupation=" + occupation
//				+ ", capacity=" + capacity + ", building=" + building.getBuildingId() +  "roomAmenities=" + roomAmenities.toString()
//				+ "floorNumber=" + floorNumber + "]";
//	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", name=" + name + ", type=" + type + ", occupation=" + occupation
				+ ", capacity=" + capacity + ", building=" + building.getBuildingId() +  "roomAmenities=" + roomAmenities.toString()
				+ "floorNumber=" + floorNumber + "]";
	}

}

