package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuildingDto {

	private int id;
	private String street_address;
	private int totalFloors;
	private int numRooms;
	
	

}
