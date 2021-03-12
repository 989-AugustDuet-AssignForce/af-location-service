package com.revature.dto;

import java.util.List;
import java.util.Objects;

public class BuildingDetailsDto {

    private int id;
    private String city;
    private String street_address;
    private String zipCode;
    private int totalFloors;
    private List<RoomDto> rooms;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

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

    public List<RoomDto> getRooms() {

        return rooms;
    }

    public void setRooms(List<RoomDto> rooms) {

        this.rooms = rooms;
    }

    public BuildingDetailsDto(int id, String city, String street_address,
                              String zipCode, int totalFloors, List<RoomDto> rooms) {

        this.id = id;
        this.city = city;
        this.street_address = street_address;
        this.zipCode = zipCode;
        this.totalFloors = totalFloors;
        this.rooms = rooms;
    }

    public BuildingDetailsDto() {

    }

    @Override
    public String toString() {

        return "BuildingDetailsDto{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street_address='" + street_address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", totalFloors=" + totalFloors +
                ", rooms=" + rooms +
                '}';
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass () != o.getClass () ) return false;
        BuildingDetailsDto that = (BuildingDetailsDto) o;
        return getId () == that.getId () && getTotalFloors () == that.getTotalFloors () && Objects.equals ( getCity (), that.getCity () ) && Objects.equals ( getStreet_address (), that.getStreet_address () ) && Objects.equals ( getZipCode (), that.getZipCode () ) && Objects.equals ( getRooms (), that.getRooms () );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getId (), getCity (), getStreet_address (), getZipCode (), getTotalFloors (), getRooms () );
    }
}
