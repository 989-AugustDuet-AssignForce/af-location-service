package com.revature.dto;

import java.util.Objects;

public class RoomDto {

	private int id;
	private String type;
	private String occupation;


	@Override
	public String toString() {

		return "RoomDto{" +
				"id=" + id +
				", type='" + type + '\'' +
				", occupation='" + occupation + '\'' +
				'}';

	}

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public String getOccupation() {

		return occupation;
	}

	public void setOccupation(String occupation) {

		this.occupation = occupation;
	}

	public RoomDto(int id, String type, String occupation) {

		this.id = id;
		this.type = type;
		this.occupation = occupation;
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass () != o.getClass () ) return false;
		RoomDto roomDto = (RoomDto) o;
		return getId () == roomDto.getId () && Objects.equals ( getType (), roomDto.getType () ) && Objects.equals ( getOccupation (), roomDto.getOccupation () );
	}

	@Override
	public int hashCode() {
		return Objects.hash ( getId (), getType (), getOccupation () );
	}

	public RoomDto() {

	}

}
