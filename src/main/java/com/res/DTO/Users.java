package com.res.DTO;

import java.sql.Date;

public class Users {
    private int id;
    private String name;
    private String phone;  // Changed to String to store phone numbers correctly
    private String mail;
    private String password;
    private Date date; // Changed to java.sql.Date for proper date handling
    private String role;
    private int totalBookings;

    // Default constructor
    public Users() {}

    // Constructor to initialize all fields (except password and role)
    public Users(int id, String mail, String name, String phone, Date date,String role,int totalBookings) {
        this.id = id;
        this.mail = mail;
        this.name = name;
        this.phone = phone;  // Directly assigning the phone as String
        this.date = date;    // Directly assigning the date as java.sql.Date
        this.role=role;
        this.totalBookings=totalBookings;
        
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date registrationDate) {
        this.date = registrationDate;
    }

    // Getter and Setter for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", phone=" + phone + ", mail=" + mail + ", password=" + password
				+ ", date=" + date + ", role=" + role + ", totalBookings=" + totalBookings + "]";
	}

    
}
