package com.revature.dto;

import java.util.Objects;

public class BuildingRequestDto {



    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getStreet_address() {

        return street_address;
    }

    public void setStreet_address(String street_address) {

        this.street_address = street_address;
    }

    public String getZipCode() {

        return zipCode;
    }

    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;
    }

    public int getTotalFloors() {

        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {

        this.totalFloors = totalFloors;
    }

    private String city;
    private String street_address;
    private String zipCode;
    private int totalFloors;

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass () != o.getClass () ) return false;
        BuildingRequestDto that = (BuildingRequestDto) o;
        return getTotalFloors () == that.getTotalFloors () && Objects.equals ( getCity (), that.getCity () ) && Objects.equals ( getStreet_address (), that.getStreet_address () ) && Objects.equals ( getZipCode (), that.getZipCode () );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getCity (), getStreet_address (), getZipCode (), getTotalFloors () );
    }
    public String toString() {

        return "BuildingRequestDto{" +
                "city='" + city + '\'' +
                ", street_address='" + street_address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", totalFloors=" + totalFloors +
                '}';
    }
    
    public BuildingRequestDto(String city, String street_address,
                              String zipCode, int totalFloors) {

        this.city = city;
        this.street_address = street_address;
        this.zipCode = zipCode;
        this.totalFloors = totalFloors;
    }

    public BuildingRequestDto() {

    }

}
