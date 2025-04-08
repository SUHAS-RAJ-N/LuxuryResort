package com.res.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.res.Connection.Connector;
import com.res.DTO.Rooms;

public class RoomsDAOImplementation implements RoomsDAO {
    private Connection conn;

    public RoomsDAOImplementation() {
        this.conn = Connector.requestConnection();
    }

    @Override
    public boolean addRoom(Rooms room) {
        String sql = "INSERT INTO rooms (name, type, price, description, imageUrl, isAvailable) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, room.getName());
            stmt.setString(2, room.getType());
            stmt.setDouble(3, room.getPrice());
            stmt.setString(4, room.getDescription());
            stmt.setString(5, room.getImageUrl());
            stmt.setBoolean(6, room.isAvailable());

            return stmt.executeUpdate() > 0; // Returns true if insert is successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Rooms> getAllRooms() {
        List<Rooms> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Rooms room = new Rooms(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getDouble("price"),
                    rs.getString("description"),
                    rs.getString("imageUrl"),
                    rs.getBoolean("isAvailable")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Rooms getRoomById(int id) {
        String sql = "SELECT * FROM rooms WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Rooms(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getDouble("price"),
                    rs.getString("description"),
                    rs.getString("imageUrl"),
                    rs.getBoolean("isAvailable")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateRoom(Rooms room) {
        String sql = "UPDATE rooms SET name=?, type=?, price=?, description=?, imageUrl=?, isAvailable=? WHERE id=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, room.getName());
            stmt.setString(2, room.getType());
            stmt.setDouble(3, room.getPrice());
            stmt.setString(4, room.getDescription());
            stmt.setString(5, room.getImageUrl());
            stmt.setBoolean(6, room.isAvailable());
            stmt.setInt(7, room.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteRoom(int id) {
        String sql = "DELETE FROM rooms WHERE id=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateRoomStatus(int id, boolean isAvailable) {
        String sql = "UPDATE rooms SET isAvailable=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, isAvailable);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	
}
