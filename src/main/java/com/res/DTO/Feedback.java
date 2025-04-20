package com.res.DTO;

import java.sql.Timestamp;

public class Feedback {
    private int feedbackId;
    private int userId;
    private int roomId;
    private int rating;
    private String comment;
    private Timestamp feedbackDate;

    public Feedback(int feedbackId, int userId, int roomId, int rating, String comment, Timestamp feedbackDate) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.roomId = roomId;
        this.rating = rating;
        this.comment = comment;
        this.feedbackDate = feedbackDate;
    }

    // Getters and Setters
    public int getFeedbackId() { return feedbackId; }
    public void setFeedbackId(int feedbackId) { this.feedbackId = feedbackId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Timestamp getFeedbackDate() { return feedbackDate; }
    public void setFeedbackDate(Timestamp feedbackDate) { this.feedbackDate = feedbackDate; }
}
