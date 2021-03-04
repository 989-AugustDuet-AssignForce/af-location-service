package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDetailsDto {

    private int id;
    private String name;
    private String type;
    private String occupation;
    private int capacity;
    private int floorNumber;
    private Set<String> amenities;

}
