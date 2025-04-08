package com.res.servlet;

import java.io.IOException;

import com.res.DAO.UsersDAO;
import com.res.DAO.UsersDAOImplementation;
import com.res.DTO.Users;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // DAO object for database operations
    private UsersDAO usersDAO = new UsersDAOImplementation();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Create a new user object
            Users user = new Users();
            
            // Retrieve form data
            String name = req.getParameter("name");
            String phoneStr = req.getParameter("phone");
            String mail = req.getParameter("mail");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");

            // Validate input fields
            if (name == null || phoneStr == null || mail == null || password == null || confirmPassword == null ||
                name.trim().isEmpty() || phoneStr.trim().isEmpty() || mail.trim().isEmpty() || 
                password.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
                req.setAttribute("error", "All fields are required!");
                forwardToSignupPage(req, resp);
                return;
            }

            // Set user details
            user.setName(name);
            
            // Validate phone number
            try {
                user.setPhone(Long.parseLong(phoneStr));
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Invalid phone number!");
                forwardToSignupPage(req, resp);
                return;
            }

            user.setMail(mail);

            // Password validation
            if (!password.equals(confirmPassword)) {
                req.setAttribute("error", "Passwords do not match!");
                forwardToSignupPage(req, resp);
                return;
            }

            user.setPassword(password);

            // Insert into database
            boolean isInserted = usersDAO.insertCustomer(user);

            if (isInserted) {
                req.setAttribute("success", "Registration successful! Please login.");
            } else {
                req.setAttribute("error", "Registration failed. Please try again.");
            }
            
            forwardToSignupPage(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred. Please try again.");
            forwardToSignupPage(req, resp);
        }
    }

    private void forwardToSignupPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("Signup.jsp");
        rd.forward(req, resp);
    }
}
