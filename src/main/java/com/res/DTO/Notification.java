package com.res.DTO;

import java.sql.Timestamp;

public class Notification {
    private int notificationId;
    private int userId;
    private String message;
    private String status;
    private Timestamp notificationDate;

    public Notification(int notificationId, int userId, String message, String status, Timestamp notificationDate) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.status = status;
        this.notificationDate = notificationDate;
    }

    // Getters and Setters
    public int getNotificationId() { return notificationId; }
    public void setNotificationId(int notificationId) { this.notificationId = notificationId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getNotificationDate() { return notificationDate; }
    public void setNotificationDate(Timestamp notificationDate) { this.notificationDate = notificationDate; }
}
