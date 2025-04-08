package com.res.DAO;

import java.sql.Date;
import java.util.ArrayList;

import com.res.DTO.Booking;

public interface BookingDAO {
    int bookRoom(Booking booking);
    boolean updateBooking(Booking booking);
    boolean cancelBooking(int bookingId, String mail);
    Booking getBookingById(int bookingId);
    ArrayList<Booking> getAllBookings();
    boolean isRoomAvailable(String roomType, Date checkIn, Date checkOut);
    ArrayList<Booking> getBookingsByUser(String mail);
}
