package com.revature.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.statics.RoomOccupation;
import com.revature.statics.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
	private int id;


	private String name;


	private RoomType type;


	private RoomOccupation occupation;


	private int capacity;

	@ManyToOne(fetch = FetchType.EAGER)
	private Building building;

	@ElementCollection
	private Set<String> amenities;

	private int floorNumber;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((building == null) ? 0 : building.hashCode());
		result = prime * result + capacity;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((occupation == null) ? 0 : occupation.hashCode());
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((amenities == null) ? 0: amenities.hashCode());
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
		if (id != other.id)
			return false;
		if (type != other.type)
			return false;
		if(!amenities.equals(other.amenities))
			return false;
		if(floorNumber != other.floorNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + id + ", name=" + name + ", type=" + type + ", occupation=" + occupation
				+ ", capacity=" + capacity + ", building=" + building +  "amenities=" + amenities.toString()
				+ "floorNumber=" + floorNumber + "]";
	}

	
	
}
