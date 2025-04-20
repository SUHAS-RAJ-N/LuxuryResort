package com.res.DAO;

import java.sql.*;
import java.util.*;

import com.res.Connection.Connector;
import com.res.DTO.Feedback;


public class FeedbackDAOImplementation implements FeedbackDAO {
	private Connection con;

	public FeedbackDAOImplementation() {
		this.con = Connector.requestConnection(); 
	}// Connection through a utility class
		@Override
		public List<Feedback> getAllFeedback() {
			List<Feedback> list = new ArrayList<>();
			String sql = "SELECT * FROM feedback";
			try (
					PreparedStatement ps = con.prepareStatement(sql)) {

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					list.add(new Feedback(
							rs.getInt("feedback_id"),
							rs.getInt("user_id"),
							rs.getInt("room_id"),
							rs.getInt("rating"),
							rs.getString("comment"),
							rs.getTimestamp("feedback_date")
							));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	}
