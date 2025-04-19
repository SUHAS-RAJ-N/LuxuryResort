package com.Resort.utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class CancelEmail {

    public static void sendCancellationEmail(String toEmail, String bookingId) throws MessagingException {
        String fromEmail = "suhasrajn113@gmail.com"; // Your Gmail address
        String subject = "Booking Cancellation Confirmation";
        String body = "Dear Customer,\n\nYour booking with ID " + bookingId +
                      " has been successfully canceled.\n\nThank you for choosing Resort Paradise.\n\nBest Regards,\nResort Paradise Team";

        // Gmail SMTP configuration
        String host = "smtp.gmail.com";
        String username = "suhasrajn113@gmail.com";
        String password = "hlzinwdttknlurog"; // App-specific password (no spaces!)

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

        // Create a mail session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        session.setDebug(true); // Enable debug output for troubleshooting

        // Compose the email message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject(subject);
        message.setText(body);

        // Send the email
        Transport.send(message);
    }
}
