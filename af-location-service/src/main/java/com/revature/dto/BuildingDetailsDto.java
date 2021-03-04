package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuildingDetailsDto {

    private int id;
    private String city;
    private String street_address;
    private String zipCode;
    private int totalFloors;
    private List<RoomDto> rooms;

}
