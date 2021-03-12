package com.revature.dto;

import java.util.Objects;

public class LocationRequestDto {

    private String state;
    private String city;
    private String zipCode;

    @Override
    public String toString() {

        return "LocationRequestDto{" +
                "state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getZipCode() {

        return zipCode;
    }

    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;
    }

    public LocationRequestDto(String state, String city, String zipCode) {

        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
    }

    public LocationRequestDto() {

    }


}
