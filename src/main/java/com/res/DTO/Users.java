package com.res.DTO;

import java.sql.Date;

public class Users {
    private int id;
    private String name;
    private long phone;
    private String mail;
    private String password;
    private String date;

 // Default constructor (empty constructor)
    public Users() {
    }

    // Constructor to initialize all fields
    public Users(int id, String mail, String name, String phone, Date date) {
        this.id = id;
        this.mail = mail;
        this.name = name;
        this.phone = Long.parseLong(phone);  // Assuming phone is a String in the database
        this.date = date.toString();  // Convert Date to String if needed
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

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", mail=" + mail + ", date=" + date + "]";
    }
}
