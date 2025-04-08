package com.res.DAO;

import java.util.List;
import com.res.DTO.Rooms;

public interface RoomsDAO {
   
    List<Rooms> getAllRooms();
    Rooms getRoomById(int id);
    boolean updateRoomStatus(int id, boolean isAvailable);
    boolean addRoom(Rooms room);
    boolean updateRoom(Rooms room);
    boolean deleteRoom(int id);
}
