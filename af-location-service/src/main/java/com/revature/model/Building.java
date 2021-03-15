package com.revature.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;



@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "building_id")
	private int buildingId;

	@NotNull
	private String city;

	@Column(name = "street_address")
	@NotNull
	private String streetAddress;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;

	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Room> rooms;

	@Column(name = "total_floors")
	@NotNull
	private int totalFloors;

	public Building(int buildingId, String city, String streetAddress,
					Location location, List<Room> rooms, int totalFloors) {

		this.buildingId = buildingId;
		this.city = city;
		this.streetAddress = streetAddress;
		this.location = location;
		this.rooms = rooms;
		this.totalFloors = totalFloors;
	}


	public Building() {

	}


	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + buildingId;
		result = prime * result + ( ( city == null ) ? 0 : city.hashCode() );
		result = prime * result + ( ( location == null ) ? 0 : location.hashCode() );
		result = prime * result + ( ( rooms == null ) ? 0 : rooms.hashCode() );
		result = prime * result + ( ( streetAddress == null ) ? 0 : streetAddress.hashCode() );
		result = prime * result + totalFloors;
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
		Building other = (Building) obj;
		if (buildingId != other.buildingId)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		}
		else if (!city.equals(other.city))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		}
		else if (!location.equals(other.location))
			return false;
		if (rooms == null) {
			if (other.rooms != null)
				return false;
		}
		else if (!rooms.equals(other.rooms))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		}
		else if (!streetAddress.equals(other.streetAddress))
			return false;
		if (this.totalFloors != other.totalFloors)
			return false;
		return true;
	}


	public int getBuildingId() {

		return buildingId;

	}

	public void setBuildingId(int buildingId) {

		this.buildingId = buildingId;
	}

	public String getCity() {

		return city;

	}

	public void setCity(String city) {

		this.city = city;

	}

	public String getStreetAddress() {

		return streetAddress;

	}

	public void setStreetAddress(String streetAddress) {

		this.streetAddress = streetAddress;

	}

	public Location getLocation() {

		return location;

	}

	public void setLocation(Location location) {

		this.location = location;

	}

	public List<Room> getRooms() {

		return rooms;

	}

	public void setRooms(List<Room> rooms) {

		this.rooms = rooms;

	}

	public int getTotalFloors() {

		return totalFloors;

	}

	public void setTotalFloors(int totalFloors) {

		this.totalFloors = totalFloors;

	}

	@Override
	public String toString() {

		return "Building [buildingId=" + buildingId + ", city=" + city + ", streetAddress=" + streetAddress
				+ ", location=" + location.getLocationId() + ", rooms=" + rooms.size() + "total floors=" + totalFloors + "]";

	}

}


