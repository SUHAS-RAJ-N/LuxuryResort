package com.Resort.utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class CancelEmail {

    public static void sendCancellationEmail(String toEmail, String bookingId) throws MessagingException {
        String fromEmail = "suhasrajn113@gmail.com"; // Your email address
        String subject = "Booking Cancellation Confirmation";
        String body = "Dear Customer,\n\nYour booking with ID " + bookingId + " has been successfully canceled.\n\nThank you for choosing Resort Paradise.\n\nBest Regards,\nResort Paradise Team";

        String host = "smtp.gmail.com"; // Correct SMTP server for Gmail
        String username = "suhasrajn113@gmail.com"; // SMTP username
        String password = "cxdf gdmi duzc yydu"; // SMTP password (ensure you use app-specific password if using Gmail)

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }
}
