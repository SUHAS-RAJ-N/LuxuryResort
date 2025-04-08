package com.res.servlet;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Properties;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        // Check if all fields are provided
        if (name != null && email != null && subject != null && message != null) {
            // Sending the email
            String to = "suhasrajn113@gmail.com";  // Resort's contact email
            String from = "no-reply@resort.com";          // From email (this is often an automated email address)
            String host = "smtp.gmail.com";               // SMTP host (Gmail SMTP for example)

            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("suhasrajn113@gmail.com", "cxdf gdmi duzc yydu");  // Gmail credentials
                }
            });

            try {
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(from));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                msg.setSubject("Contact Us Message: " + subject);
                msg.setText("You have received a new message from:\n\n" +
                            "Name: " + name + "\n" +
                            "Email: " + email + "\n\n" +
                            "Message: " + message);

                // Send email
                Transport.send(msg);

                // Set success message and forward to success page
                request.setAttribute("responseMessage", "Your message has been sent successfully. We will get back to you shortly.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Success.jsp");
                dispatcher.forward(request, response);

            } catch (MessagingException e) {
                // Handle error and forward to error page
                e.printStackTrace();
                request.setAttribute("responseMessage", "Failed to send message. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Error.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // If any required field is missing, set error message
            request.setAttribute("responseMessage", "All fields are required.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Contact.jsp");
            dispatcher.forward(request, response);
        }
    }
}
