package com.revature.dto;


import java.util.Objects;

public class BuildingDto {

	private int id;
	private String street_address;
	private int totalFloors;
	private int numRooms;

	public BuildingDto() {

	}

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getStreet_address() {

		return street_address;
	}

	public void setStreet_address(String street_address) {

		this.street_address = street_address;
	}

	public int getTotalFloors() {

		return totalFloors;
	}

	public void setTotalFloors(int totalFloors) {

		this.totalFloors = totalFloors;
	}

	public int getNumRooms() {

		return numRooms;
	}

	public void setNumRooms(int numRooms) {

		this.numRooms = numRooms;
	}

	public BuildingDto(int id, String street_address, int totalFloors, int numRooms) {

		this.id = id;
		this.street_address = street_address;
		this.totalFloors = totalFloors;
		this.numRooms = numRooms;

	}

	@Override
	public String toString() {

		return "BuildingDto{" +
				"id=" + id +
				", street_address='" + street_address + '\'' +
				", totalFloors=" + totalFloors +
				", numRooms=" + numRooms +
				'}';

	}

}
