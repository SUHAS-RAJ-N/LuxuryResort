package com.res.DAO;

import com.res.Connection.Connector;
import com.res.DTO.Notification;
import java.sql.*;
import java.util.*;

public class NotificationDAOImplementation implements NotificationDAO {
	 private Connection con;

	    public NotificationDAOImplementation() {
	        this.con = Connector.requestConnection(); 
	    }// Connection through a utility class
	        @Override
	        public List<Notification> getAllNotifications() {
	            List<Notification> notifications = new ArrayList<>();
	            String sql = "SELECT * FROM notifications";

	            try (
	                 PreparedStatement ps = con.prepareStatement(sql)) {

	                ResultSet rs = ps.executeQuery();
	                while (rs.next()) {
	                    notifications.add(new Notification(
	                        rs.getInt("notification_id"),
	                        rs.getInt("user_id"),
	                        rs.getString("message"),
	                        rs.getString("status"),
	                        rs.getTimestamp("notification_date")
	                    ));
	                }

	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return notifications;
	        }
	}