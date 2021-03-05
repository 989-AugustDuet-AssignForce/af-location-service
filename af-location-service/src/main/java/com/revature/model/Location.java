package com.revature.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


@Data
@NoArgsConstructor
public class Location {


    private int id;
    private String state;
    private String city;
    private String zipCode;
    private List<Building> buildings;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the buildings
	 */
	public List<Building> getBuildings() {
		return buildings;
	}
	/**
	 * @param buildings the buildings to set
	 */
	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}
    
    

}
