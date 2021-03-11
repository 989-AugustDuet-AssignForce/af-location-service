package com.revature.service;

import java.util.List;

import java.util.stream.Collectors;

import com.revature.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.BuildingDto;
import com.revature.repository.BuildingRepository;

@Service
public class BuildingServiceImpl implements BuildingService{

	private BuildingRepository br;

	@Autowired
	public BuildingServiceImpl(BuildingRepository br) {
		this.br=br;
	}

	@Override
	public List<BuildingDto> getAllBuildingsAtLocation(int id) {
		List<Building> buildings = br.findAllBuildingsByLocationId(id);
		return buildings.stream().map(building -> {
			BuildingDto buildingDto = new BuildingDto();
			buildingDto.setBuildingId(building.getBuildingId());
			buildingDto.setStreet_address(building.getStreetAddress());
			buildingDto.setNumRooms(building.getRooms().size());
			buildingDto.setTotalFloors(building.getTotalFloors());
			return buildingDto;
		}).collect(Collectors.toList());
	}

}
