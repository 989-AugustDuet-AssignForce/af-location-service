package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService{
	
	private RoomRepository rr;
	
	@Autowired
	public RoomServiceImpl(RoomRepository rr) {
		this.rr=rr;
	}

}
