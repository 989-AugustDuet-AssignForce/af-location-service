package com.revature.controller;

import com.revature.service.*;

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

@WebMvcTest(LocationController.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class LocationControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LocationServiceImpl locationService;
    @MockBean
    private BuildingServiceImpl buildingService;
    @MockBean
    private RoomServiceImpl roomService;

    @Test
    public void shouldReturn202AcceptedForCreateLocation() throws Exception {

        this.mockMvc.perform(post("/location/api/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"state\": \"Texas\",\"city\": \"Plano\", \"zipCode\": \"75023\"}"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldReturn400BadRequestForCreateLocation() throws Exception {

        this.mockMvc.perform(post("/location/api/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturn200IsOkGetAllLocations() throws Exception {

        this.mockMvc.perform(get("/location/api/locations")
                .contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404NotFoundGetAllLocations() throws Exception {

        this.mockMvc.perform(get("/location/api/location")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn202AcceptedUpdateLocationState() throws Exception {

        this.mockMvc.perform(patch("/location/api/locations/{id}/updateState", "1")
                .param("state", "New York")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldReturn400BadRequestUpdateLocationState() throws Exception {

        this.mockMvc.perform(patch("/location/api/locations/{id}/updateState", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn202AcceptedUpdateLocationCity() throws Exception {

        this.mockMvc.perform(patch("/location/api/locations/{id}/updateCity", "1")
                .param("city", "Plano")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldReturn400BadRequestUpdateLocationCity() throws Exception {

        this.mockMvc.perform(patch("/location/api/locations/{id}/updateCity", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldReturn202AcceptedUpdateLocationZipCode() throws Exception {

        this.mockMvc.perform(patch("/location/api/locations/{id}/updateZipCode", "1")
                .param("zipCode", "75075")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }

    @Test
    public void shouldReturn400BadRequestUpdateLocationZipCode() throws Exception {

        this.mockMvc.perform(patch("/location/api/locations/{id}/updateZipCode", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldReturn202AcceptedDeleteLocation() throws Exception {

        this.mockMvc.perform(delete("/location/api/locations/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }

    @Test
    public void shouldReturn400BadRequestDeleteLocation() throws Exception {

        this.mockMvc.perform(delete("/location/api/locations/{id}", "j")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldReturn200AcceptedUpdateLocation() throws Exception {

        this.mockMvc.perform(put("/location/api/locations/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"state\": \"Texas\",\"city\": \"Plano\", \"zipCode\": \"75023\"}"))
                .andExpect(status().isAccepted());

    }

    @Test
    public void shouldReturn400BadRequestUpdateLocation() throws Exception {

        this.mockMvc.perform(put("/location/api/locations/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldReturn200IsOkGetAllLocationsByState() throws Exception {

        this.mockMvc.perform(get("/location/api/locations/state/{state}", "VA")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn405NotAllowedGetAllLocationsByState() throws Exception {

        this.mockMvc.perform(get("/location/api/locations/state/{state}", "")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void shouldReturn200IsOkGetAllLocationsByCity() throws Exception {

        this.mockMvc.perform(get("/location/api/locations/city/{city}", "Reston")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn405NotAllowedAllLocationsByCity() throws Exception {

        this.mockMvc.perform(get("/location/api/locations/city/{city}", "")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void shouldReturn200IsOkGetAllLocationsByZipcode() throws Exception {

        this.mockMvc.perform(get("/location/api/locations/zip/{zipCode}", "75023")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn405NotAllowedGetAllLocationsByZipcode() throws Exception {

        this.mockMvc.perform(get("/location/api/locations/zip/{zipCode}", "")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void shouldReturn200IsOkGetLocationById() throws Exception {

        this.mockMvc.perform(get("/location/api/locations/id/{index}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn400NotAllowedGetLocationById() throws Exception {

        this.mockMvc.perform(get("/location/api/locations/id/{index}", "")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

}
