package com.revature.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.revature.service.LocationService;

public class LocationControllerTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private LocationService locationService;

	@Test
	public void checkGetBuildingsAtLocationControllerValid() throws Exception {
		this.mockMvc.perform(get("/locations").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isOk());

	}

	@Test
	public void checkGetBuildingsAtLocationConrtollerInvalid() throws Exception {
		this.mockMvc.perform(get("/locations").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isUnauthorized());
	}
}
