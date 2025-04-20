package com.res.DAO;

import java.sql.*;
import java.util.*;

import com.res.Connection.Connector;


public class ReportsDAOImplementation implements ReportsDAO {
	 private Connection con;

	    public ReportsDAOImplementation() {
	        this.con = Connector.requestConnection();  // Connection through a utility class
	    }
    @Override
    public Map<String, Integer> getBookingStats() {
        Map<String, Integer> stats = new HashMap<>();
        String sql = "SELECT roomType, COUNT(*) AS total FROM booking GROUP BY roomType";

        try (
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stats.put(rs.getString("roomType"), rs.getInt("total"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stats;
    }
}
