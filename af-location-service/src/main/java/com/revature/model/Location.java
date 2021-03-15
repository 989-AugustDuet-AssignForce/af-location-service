package com.revature.model;

import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="location_id")
	private int locationId;

	@NotNull
	private String state;

	@NotNull
	private String city;

	@Column(name = "zip_code")
	@NotNull
	private String zipCode;

	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Building> buildings;

	public Location(int locationId, String state, String city,
					String zipCode, List<Building> buildings) {

		this.locationId = locationId;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.buildings = buildings;
	}

	public Location() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buildings == null) ? 0 : buildings.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + locationId;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + (( zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (buildings == null) {
			if (other.buildings != null)
				return false;
		} else if (!buildings.equals(other.buildings))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (locationId != other.locationId)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId
				+ ", state=" + state + ", city=" + city
				+ ", zipCode=" + zipCode + ", buildings=" + buildings.size() + "]";
	}

	public int getLocationId() {

		return locationId;
	}

	public void setLocationId(int locationId) {

		this.locationId = locationId;
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

	public List<Building> getBuildings() {

		return buildings;
	}

	public void setBuildings(List<Building> buildings) {

		this.buildings = buildings;
	}

}
