package com.revature.dto;

import java.util.Objects;

public class LocationDto {

    private int id;
    private String state;
    private String city;
    private String zipCode;
    private int numBuildings;

    @Override
    public String toString() {

        return "LocationDto{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", numBuildings=" + numBuildings +
                '}';
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
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

    public int getNumBuildings() {

        return numBuildings;
    }

    public void setNumBuildings(int numBuildings) {

        this.numBuildings = numBuildings;
    }

    public LocationDto(int id, String state, String city, String zipCode, int numBuildings) {

        this.id = id;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.numBuildings = numBuildings;
    }


    public LocationDto() {

    }

}
