package com.res.DAO;

import java.sql.*;
import java.util.ArrayList;
import com.res.Connection.Connector;
import com.res.DTO.Booking;

public class BookingDAOImplementation implements BookingDAO {
    private Connection con;

    public BookingDAOImplementation() {
        this.con = Connector.requestConnection();  // Connection through a utility class
    }

    // Method to book a room (Insert into the database)
    @Override
    public int bookRoom(Booking booking) {
        int generatedId = -1;
        String sql = "INSERT INTO booking (users_id, mail, room_type, check_in, check_out) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Get customer_id from email
            int customerId = getCustomerIdByEmail(booking.getMail());
            if (customerId == -1) {
                return -1;
            }

            stmt.setInt(1, customerId);
            stmt.setString(2, booking.getMail());
            stmt.setString(3, booking.getRoomType());
            stmt.setDate(4, booking.getCheckIn());
            stmt.setDate(5, booking.getCheckOut());

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    // Get Customer ID from email
    private int getCustomerIdByEmail(String email) {
        String sql = "SELECT id FROM users WHERE mail = ?";
        int customerId = -1;
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customerId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerId;
    }

    // Check if a room is available for the given dates
    @Override
    public boolean isRoomAvailable(String roomType, Date checkIn, Date checkOut) {
        String sql = "SELECT COUNT(*) FROM booking WHERE room_type = ? AND (check_in < ? AND check_out > ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, roomType);
            ps.setDate(2, checkOut);
            ps.setDate(3, checkIn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update an existing booking
    @Override
    public boolean updateBooking(Booking booking) {
        String sql = "UPDATE booking SET check_in = ?, check_out = ?, room_type = ? WHERE id = ? AND mail = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(booking.getCheckIn().getTime()));
            stmt.setDate(2, new java.sql.Date(booking.getCheckOut().getTime()));
            stmt.setString(3, booking.getRoomType());
            stmt.setInt(4, booking.getId());
            stmt.setString(5, booking.getMail());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get booking details by booking ID
    @Override
    public Booking getBookingById(int bookingId) {
        String sql = "SELECT * FROM booking WHERE id = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Booking(
                            rs.getInt("id"),
                            rs.getString("mail"),
                            rs.getString("room_type"),
                            rs.getDate("check_in"),
                            rs.getDate("check_out")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all bookings
    @Override
    public ArrayList<Booking> getAllBookings() {
        ArrayList<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM booking";
        
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("id"),
                        rs.getString("mail"),
                        rs.getString("room_type"),
                        rs.getDate("check_in"),
                        rs.getDate("check_out")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Get all bookings by a specific user
    @Override
    public ArrayList<Booking> getBookingsByUser(String mail) {
        ArrayList<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM booking WHERE mail = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, mail);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(new Booking(
                            rs.getInt("id"),
                            rs.getString("mail"),
                            rs.getString("room_type"),
                            rs.getDate("check_in"),
                            rs.getDate("check_out")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Cancel a booking by ID and user email
    @Override
    public boolean cancelBooking(int bookingId, String mail) {
        String sql = "DELETE FROM booking WHERE id = ? AND mail = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);
            stmt.setString(2, mail);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
