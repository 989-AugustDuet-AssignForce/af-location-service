package com.revature.dto;


import java.util.Objects;
import java.util.Set;


public class RoomRequestDto {

    private String name;
    private String type;
    private String occupation;
    private int capacity;
    private int floorNumber;
    private Set<String> amenities;


    public RoomRequestDto(String name, String type, String occupation,
                          int capacity, int floorNumber, Set<String> amenities) {

        this.name = name;
        this.type = type;
        this.occupation = occupation;
        this.capacity = capacity;
        this.floorNumber = floorNumber;
        this.amenities = amenities;
    }


    @Override
    public String toString() {

        return "RoomRequestDto{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", occupation='" + occupation + '\'' +
                ", capacity=" + capacity +
                ", floorNumber=" + floorNumber +
                ", amenities=" + amenities +
                '}';
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getOccupation() {

        return occupation;
    }

    public void setOccupation(String occupation) {

        this.occupation = occupation;
    }

    public int getCapacity() {

        return capacity;
    }

    public void setCapacity(int capacity) {

        this.capacity = capacity;
    }

    public int getFloorNumber() {

        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {

        this.floorNumber = floorNumber;
    }

    public Set<String> getAmenities() {

        return amenities;
    }

    public void setAmenities(Set<String> amenities) {

        this.amenities = amenities;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass () != o.getClass () ) return false;
        RoomRequestDto that = (RoomRequestDto) o;
        return getCapacity () == that.getCapacity () && getFloorNumber () == that.getFloorNumber () && Objects.equals ( getName (), that.getName () ) && Objects.equals ( getType (), that.getType () ) && Objects.equals ( getOccupation (), that.getOccupation () ) && Objects.equals ( getAmenities (), that.getAmenities () );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getName (), getType (), getOccupation (), getCapacity (), getFloorNumber (), getAmenities () );
    }


    public RoomRequestDto() {

    }
  
}
