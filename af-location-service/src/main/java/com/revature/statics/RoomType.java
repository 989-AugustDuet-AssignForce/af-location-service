package com.revature.statics;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum RoomType {

    VIRTUAL( 1000 ),
    PHYSICAL( 1001 ),
    REMOTE( 1002 );
    


    public Integer getType() {

        return type;
    }

    private Integer type;

    RoomType ( Integer type ) {

        this.type = type;

    }

    public static RoomType of ( Integer type ) {

        return Stream.of(RoomType.values())
                .filter(s -> s.getType().equals(type))
                .findFirst().orElseThrow(IllegalArgumentException::new);

    }

    public static String getRoomType ( RoomType roomType ) {

        Map<RoomType, String> roomTypeMap = new HashMap<>();
        roomTypeMap.put(VIRTUAL, "Virtual");
        roomTypeMap.put(PHYSICAL, "Physical");
        roomTypeMap.put(REMOTE, "Remote");
        return roomTypeMap.get(roomType);

    }

}
