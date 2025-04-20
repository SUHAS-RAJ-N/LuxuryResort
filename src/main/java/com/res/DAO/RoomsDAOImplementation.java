package com.res.DAO;

import com.res.Connection.Connector;
import com.res.DTO.Rooms;
import java.sql.*;
import java.util.*;

public class RoomsDAOImplementation implements RoomsDAO {
	 private Connection con;

	    public RoomsDAOImplementation() {
	        this.con = Connector.requestConnection();  // Connection through a utility class
	    }
	    @Override
	    public boolean addRoom(Rooms room) {
	        String sql = "INSERT INTO rooms (name, type, price, description, imageUrl, available) VALUES (?, ?, ?, ?, ?, ?)";
	        try (
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setString(1, room.getName());
	            ps.setString(2, room.getType());
	            ps.setDouble(3, room.getPrice());
	            ps.setString(4, room.getDescription());
	            ps.setString(5, room.getImageUrl());
	            ps.setBoolean(6, room.isAvailable());
	            return ps.executeUpdate() > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    @Override
	    public boolean updateRoom(Rooms room) {
	        String sql = "UPDATE rooms SET name=?, type=?, price=?, description=?, imageUrl=?, available=? WHERE id=?";
	        try (
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setString(1, room.getName());
	            ps.setString(2, room.getType());
	            ps.setDouble(3, room.getPrice());
	            ps.setString(4, room.getDescription());
	            ps.setString(5, room.getImageUrl());
	            ps.setBoolean(6, room.isAvailable());
	            ps.setInt(7, room.getId());
	            return ps.executeUpdate() > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    @Override
	    public boolean deleteRoom(int id) {
	        String sql = "DELETE FROM rooms WHERE id=?";
	        try (
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, id);
	            return ps.executeUpdate() > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    @Override
	    public Rooms getRoomById(int id) {
	        String sql = "SELECT * FROM rooms WHERE id=?";
	        try (
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                return new Rooms(
	                    rs.getInt("id"), rs.getString("name"), rs.getString("type"),
	                    rs.getDouble("price"), rs.getString("description"),
	                    rs.getString("imageUrl"), rs.getBoolean("available")
	                );
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    @Override
	    public List<Rooms> getAllRooms() {
	        List<Rooms> list = new ArrayList<>();
	        String sql = "SELECT * FROM rooms";
	        try (
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Rooms room = new Rooms(
	                    rs.getInt("id"), rs.getString("name"), rs.getString("type"),
	                    rs.getDouble("price"), rs.getString("description"),
	                    rs.getString("imageUrl"), rs.getBoolean("available")
	                );
	                list.add(room);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	}
