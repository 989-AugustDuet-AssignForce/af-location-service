package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.repository.RoomRepository;

public class RoomServiceImpl implements RoomService{
	
	private RoomRepository rr;
	
	@Autowired
	public RoomServiceImpl(RoomRepository rr) {
		this.rr=rr;
	}

}
