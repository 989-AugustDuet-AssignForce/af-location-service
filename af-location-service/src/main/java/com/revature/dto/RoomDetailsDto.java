package com.revature.dto;

import java.util.Objects;
import java.util.Set;

public class RoomDetailsDto {

    private int id;
    private String name;
    private String type;
    private String occupation;
    private int capacity;

    @Override
    public String toString() {

        return "RoomDetailsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", occupation='" + occupation + '\'' +
                ", capacity=" + capacity +
                ", floorNumber=" + floorNumber +
                ", amenities=" + amenities +
                '}';
    }

    private int floorNumber;
    private Set<String> amenities;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
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

    public RoomDetailsDto(int id, String name, String type, String occupation,
                          int capacity, int floorNumber, Set<String> amenities) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.occupation = occupation;
        this.capacity = capacity;
        this.floorNumber = floorNumber;
        this.amenities = amenities;

    }

    public RoomDetailsDto() {

    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass () != o.getClass () ) return false;
        RoomDetailsDto that = (RoomDetailsDto) o;
        return getId () == that.getId () && getCapacity () == that.getCapacity () && getFloorNumber () == that.getFloorNumber () && Objects.equals ( getName (), that.getName () ) && Objects.equals ( getType (), that.getType () ) && Objects.equals ( getOccupation (), that.getOccupation () ) && Objects.equals ( getAmenities (), that.getAmenities () );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getId (), getName (), getType (), getOccupation (), getCapacity (), getFloorNumber (), getAmenities () );
    }
}
