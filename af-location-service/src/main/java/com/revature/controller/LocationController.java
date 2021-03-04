package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.LocationService;

@RestController
@RequestMapping("location")
@CrossOrigin
public class LocationController {
	
	private LocationService ls;

	@Autowired
	public LocationController(LocationService ls) {
		this.ls = ls;
	}

}
