package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuildingRequestDto {

    private String city;
    private String street_address;
    private String zipCode;
    private int totalFloors;

}
