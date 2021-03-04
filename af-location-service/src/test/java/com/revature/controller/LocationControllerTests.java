package com.revature.controller;

import com.revature.service.BuildingService;
import com.revature.service.LocationService;
import com.revature.service.RoomService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class LocationControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LocationService locationService;
    @MockBean
    private BuildingService buildingService;
    @MockBean
    private RoomService roomService;

    @Test
    public void shouldReturn200AcceptedForCreateLocation() throws Exception {

        this.mockMvc.perform(post("/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"state\": \"Texas\",\"city\": \"Plano\", \"zipCode\": \"75023\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturn400BadRequestForCreateLocation() throws Exception {

        this.mockMvc.perform(post("/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturn200IsOkGetAllLocations() throws Exception {

        this.mockMvc.perform(get("/locations")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn401AccessDeniedGetAllLocations() throws Exception {

        this.mockMvc.perform(get("/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturn200AcceptedUpdateLocationState() throws Exception {

        this.mockMvc.perform(patch("/locations/{id}/", "1")
                .param("state", "New York")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn401AccessDeniedUpdateLocationState() throws Exception {

        this.mockMvc.perform(patch("/locations/{id}/", "0")
                .param("state", "")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturn200AcceptedUpdateLocationCity() throws Exception {

        this.mockMvc.perform(patch("/locations/{id}/", "1")
                .param("city", "Plano")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn401AccessDeniedUpdateLocationCity() throws Exception {

        this.mockMvc.perform(patch("/locations/{id}/", "0")
                .param("city", " ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void shouldReturn200AcceptedUpdateLocationZipCode() throws Exception {

        this.mockMvc.perform(patch("/locations/{id}/", "1")
                .param("zipCode", "75075")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldReturn401AccessDeniedUpdateLocationZipCode() throws Exception {

        this.mockMvc.perform(patch("/locations/{id}/", "0")
                .param("zipCode", "76467")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void shouldReturn200AcceptedDeleteLocation() throws Exception {

        this.mockMvc.perform(delete("/locations/{id}/", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldReturn400BadRequestDeleteLocation() throws Exception {

        this.mockMvc.perform(delete("/locations/{id}/", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldReturn200AcceptedUpdateLocation() throws Exception {

        this.mockMvc.perform(put("/locations/{id}/", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"state\": \"Texas\",\"city\": \"Plano\", \"zipCode\": \"75023\""))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldReturn400BadRequestUpdateLocation() throws Exception {

        this.mockMvc.perform(put("/locations/{id}/", "0")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"state\": \"Texas\",\"city\": \"Plano\", \"zipCode\": \"75023\""))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldReturn200IsOkGetAllLocationsByState() throws Exception {

        this.mockMvc.perform(get("/locations/{state}/")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn401AccessDeniedGetAllLocationsByState() throws Exception {

        this.mockMvc.perform(get("/locations/{state}/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturn200IsOkGetAllLocationsByCity() throws Exception {

        this.mockMvc.perform(get("/locations/{city}/")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn401AccessDeniedGetAllLocationsByCity() throws Exception {

        this.mockMvc.perform(get("/locations/{city}/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturn200IsOkGetAllLocationsByZipcode() throws Exception {

        this.mockMvc.perform(get("/locations/{zipcode}/")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn401AccessDeniedGetAllLocationsByZipcode() throws Exception {

        this.mockMvc.perform(get("/locations/{zipcode}/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturn200IsOkGetLocationById() throws Exception {

        this.mockMvc.perform(get("/locations/{id}/")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn401AccessDeniedGetLocationById() throws Exception {

        this.mockMvc.perform(get("/locations/{id}/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andExpect(status().isUnauthorized());
    }
  
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
