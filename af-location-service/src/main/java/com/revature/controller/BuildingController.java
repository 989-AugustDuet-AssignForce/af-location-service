package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.BuildingService;

@RestController
@RequestMapping("building")
@CrossOrigin
public class BuildingController {

	private BuildingService bs;

	@Autowired
	public BuildingController(BuildingService bs) {
		this.bs = bs;
	}

}
