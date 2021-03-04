package com.revature.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.revature.service.BuildingService;

@WebMvcTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BuildingControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	private BuildingService buildingService = Mockito.mock(BuildingService.class);
	
	@Test
	public void goodCreateBuilding() throws Exception{
		mockMvc.perform(post("/building").contentType(MediaType.APPLICATION_JSON).
				content("{\"city\":\"Miami\",\"street_address\":\"Main Street\", \"zipCode\": \"1234\", \"totalFloors\": 3}"))
				.andExpect(status().isCreated());
	}
	@Test
	public void createBadBuilding() throws Exception{
		mockMvc.perform(post("/building").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isBadRequest());
	}
	@Test
	public void getAllLocations() throws Exception{
		mockMvc.perform(get("/building").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isOk());
	}
	@Test
	public void getAllLocationsUnauthorized() throws Exception{
		mockMvc.perform(get("/building").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isUnauthorized());
	}
	@Test 
	public void getBuildingsByCity() throws Exception {
		mockMvc.perform(get("/building/city/{city}","Miami").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isOk());
	}
	@Test
	public void getBuildingsByCityUnauthorized() throws Exception {
		mockMvc.perform(get("/building/city/{city"))
	}
}
