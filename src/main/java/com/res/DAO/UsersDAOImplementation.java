package com.res.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.res.Connection.Connector;
import com.res.DTO.Users;

public class UsersDAOImplementation implements UsersDAO {
	private Connection con;

	public UsersDAOImplementation() {
		this.con = Connector.requestConnection(); // Assuming Connector manages your DB connections
	}

	@Override
	public boolean insertCustomer(Users user) {
		String query = "INSERT INTO USERS (name, phone, mail, password, registration_date) VALUES (?, ?, ?, ?, NOW())";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, user.getName());
			ps.setLong(2, user.getPhone());
			ps.setString(3, user.getMail());
			ps.setString(4, user.getPassword());

			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCustomer(Users user) {
		String query = "UPDATE USERS SET name=?, phone=?, mail=?, password=? WHERE id=?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, user.getName());
			ps.setLong(2, user.getPhone());
			ps.setString(3, user.getMail());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getId());

			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCustomer(Users user) {
		String query = "DELETE FROM USERS WHERE id=?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, user.getId());

			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Users getCustomer(String mail, String password) {
		Users user = null;
		String query = "SELECT * FROM USERS WHERE MAIL=? AND PASSWORD=?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, mail);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new Users();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setPhone(rs.getLong("PHONE"));
				user.setMail(rs.getString("MAIL"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setDate(rs.getString("registration_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Users getCustomer(String mail) {
		Users user = null;
		String query = "SELECT * FROM USERS WHERE mail = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new Users();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setPhone(rs.getLong("PHONE"));
				user.setMail(rs.getString("MAIL"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setDate(rs.getString("registration_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public ArrayList<Users> getAllUsers() {
	    ArrayList<Users> usersList = new ArrayList<>();
	    String sql = "SELECT * FROM users";

	    try (Connection con = Connector.requestConnection();
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Users user = new Users();
	            user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setPhone(rs.getLong("phone"));
	            user.setMail(rs.getString("mail"));
	            user.setPassword(rs.getString("password"));
	            user.setDate(rs.getString("registration_date"));
	            usersList.add(user);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return usersList;
	}



	@Override
	public Users getUserById(int id) {
		Users user = null;
		String query = "SELECT * FROM USERS WHERE id = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new Users();
				user.setId(rs.getInt("ID"));
				user.setName(rs.getString("NAME"));
				user.setPhone(rs.getLong("PHONE"));
				user.setMail(rs.getString("MAIL"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setDate(rs.getString("registration_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
