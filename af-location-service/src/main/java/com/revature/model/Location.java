package com.revature.model;

import java.util.List;

public class Location {

    private int id;
    private String state;
    private String city;
    private List<Building> buildings;
    private String zipCode;
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
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
