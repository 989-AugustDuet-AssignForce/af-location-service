package com.revature.service;

import com.revature.Exception.NotFoundException;
import com.revature.dto.RoomDetailsDto;
import com.revature.dto.RoomDto;
import com.revature.dto.RoomRequestDto;
import com.revature.model.Building;
import com.revature.model.Room;
import com.revature.repository.BuildingRepository;
import com.revature.repository.RoomRepository;
import org.springframework.stereotype.Service;


@Service
public class RoomServiceImpl implements RoomService {
	
	private RoomRepository rr;
	
	@Autowired
	public RoomServiceImpl(RoomRepository rr) {
		this.rr=rr;
	}

}
