package com.Resort.utils;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailSender {
    private static final String SENDER_EMAIL = "suhasrajn113@gmail.com"; // Change to your email
    private static final String SENDER_PASSWORD = "hlzi nwdt tknl urog"; // Use an App Password for Gmail
    private static final String REPLY_TO_EMAIL = "suhasrajn113@gmail.com"; // Optional Reply-To address

    public static void sendEmail(String recipient, String subject, String body, boolean isHtml) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true"); // Enable debugging for troubleshooting

        // Create session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);

            // Set optional Reply-To address
            message.setReplyTo(InternetAddress.parse(REPLY_TO_EMAIL));

            // Send as HTML if required
            if (isHtml) {
                message.setContent(body, "text/html; charset=UTF-8");
            } else {
                message.setText(body);
            }

            Transport.send(message);
            System.out.println("✅ Email sent successfully to " + recipient);
        } catch (MessagingException e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
