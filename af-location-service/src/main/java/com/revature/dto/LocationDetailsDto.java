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
public class LocationDetailsDto {

    private int id;
    private String state;
    private String city;
    private String zipCode;
    private List<BuildingDto> buildings;

}
