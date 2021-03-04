package com.revature.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocationDto {

    private int id;
    private String state;
    private String city;
    private String zipCode;
    private int numBuildings;

}
