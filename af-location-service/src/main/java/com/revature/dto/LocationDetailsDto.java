package com.revature.dto;

import java.util.List;
import java.util.Objects;

public class LocationDetailsDto {

    private int id;
    private String state;
    private String city;
    private String zipCode;

    @Override
    public String toString() {

        return "LocationDetailsDto{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", buildings=" + buildings +
                '}';
    }

    private List<BuildingDto> buildings;

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

    public List<BuildingDto> getBuildings() {

        return buildings;
    }

    public void setBuildings(List<BuildingDto> buildings) {

        this.buildings = buildings;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass () != o.getClass () ) return false;
        LocationDetailsDto that = (LocationDetailsDto) o;
        return getId () == that.getId () && Objects.equals ( getState (), that.getState () ) && Objects.equals ( getCity (), that.getCity () ) && Objects.equals ( getZipCode (), that.getZipCode () ) && Objects.equals ( getBuildings (), that.getBuildings () );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( getId (), getState (), getCity (), getZipCode (), getBuildings () );
    }
    public LocationDetailsDto(int id, String state, String city, String zipCode, List<BuildingDto> buildings) {

        this.id = id;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.buildings = buildings;
    }

    public LocationDetailsDto() {

    }


}
