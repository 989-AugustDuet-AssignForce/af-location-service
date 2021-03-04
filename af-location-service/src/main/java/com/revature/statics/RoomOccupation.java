package com.revature.statics;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum RoomOccupation {

    TRAINING( 1000 ),
    MEETING( 1001 );

    @Getter
    private Integer occupation;

    RoomOccupation ( Integer occupation ) {

        this.occupation = occupation;

    }

    public static RoomOccupation of ( Integer occupation ) {

        return Stream.of(RoomOccupation.values())
                .filter(s -> s.getOccupation().equals(occupation))
                .findFirst().orElseThrow(IllegalArgumentException::new);

    }

    public static String getRoomOccupation ( RoomOccupation roomOccupation ) {

        Map<RoomOccupation, String> roomOccupationMap = new HashMap<>();
        roomOccupationMap.put(TRAINING, "Training");
        roomOccupationMap.put(MEETING, "Meeting");
        return roomOccupationMap.get(roomOccupation);

    }

}
