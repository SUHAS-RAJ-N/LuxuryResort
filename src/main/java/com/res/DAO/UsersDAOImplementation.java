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
		this.con = Connector.requestConnection();
	}

	@Override
	public boolean insertCustomer(Users user) {
		String query = "INSERT INTO USERS (name, phone, mail, password, registration_date, role) VALUES (?, ?, ?, ?, NOW(), ?)";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getPhone());
			ps.setString(3, user.getMail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRole() != null ? user.getRole() : "user");

			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCustomer(Users user) {
		String query = "UPDATE USERS SET name=?, phone=?, mail=?, password=?, role=? WHERE id=?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getPhone());
			ps.setString(3, user.getMail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRole());
			ps.setInt(6, user.getId());

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
		String query = "SELECT * FROM USERS WHERE mail=? AND password=?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, mail);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new Users();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setMail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setDate(rs.getDate("registration_date"));
				user.setRole(rs.getString("role"));
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
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setMail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setDate(rs.getDate("registration_date"));
				user.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Users getUserById(int userId) {
	    String query = "SELECT * FROM users WHERE id = ?";
	    try (PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setInt(1, userId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            Users user = new Users();
	            user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setPhone(rs.getString("phone"));
	            user.setMail(rs.getString("mail"));
	            user.setRole(rs.getString("role"));
	            user.setDate(rs.getDate("registration_date"));
	            return user;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	@Override
	public ArrayList<Users> getAllUsers() {
	    ArrayList<Users> userList = new ArrayList<>();
	    
	    String query = "SELECT u.*, " +
	                   "(SELECT COUNT(*) FROM booking b WHERE b.users_id = u.id) AS totalBookings " +
	                   "FROM users u";
	    
	    try (PreparedStatement stmt = con.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Users user = new Users();
	            user.setId(rs.getInt("id"));
	            user.setMail(rs.getString("mail"));
	            user.setName(rs.getString("name"));
	            user.setPhone(rs.getString("phone"));
	            user.setDate(rs.getDate("registration_date"));
	            user.setRole(rs.getString("role"));
	            user.setTotalBookings(rs.getInt("totalBookings")); // 🔥 Add this line
	            userList.add(user);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return userList;
	}


    
	@Override
	public boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, userId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
