package com.revature.service;

import com.revature.dto.BuildingDto;
import com.revature.dto.LocationDto;
import com.revature.repository.BuildingRepository;
import com.revature.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LocationServiceImpl {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    BuildingRepository BuildingRepository;

    public void createLocation( LocationDto dto ) {

    }

    public List<LocationDto> getAllLocations() {
        return null;
    }

    public List<LocationDto> getLocationsByState( String state ) {
        return null;
    }

    public List<LocationDto> getLocationsByCity( String city ) {
        return null;
    }

    public List<LocationDto> getLocationsByZipCode( String zipCode ) {
        return null;
    }

    public LocationDto getLocation( int index ) {
        return null;
    }

    public void updateState( int index, String state ) {

    }

    public void updateCity( int index, String city ) {

    }

    public void updateZipCode( int index, String zipCode ) {

    }

    public void deleteLocation( int index ) {

    }

    public void addBuilding( int index, BuildingDto buildingDto ) {

    }

    public void updateLocation( int index, LocationDto locationDto ) {

    }
}