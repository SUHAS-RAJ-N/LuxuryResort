package com.res.DAO;

import java.util.List;
import com.res.DTO.Rooms;

public interface RoomsDAO {
    boolean addRoom(Rooms room);
    boolean updateRoom(Rooms room);
    boolean deleteRoom(int id);
    Rooms getRoomById(int id);
    List<Rooms> getAllRooms();
}
