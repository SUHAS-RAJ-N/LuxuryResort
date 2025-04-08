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
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Retrieve user input
        String email = req.getParameter("mail");
        String password = req.getParameter("password");

        // Validate input fields
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("error", "Email and Password are required.");
            RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
            rd.forward(req, resp);
            return; // Stop execution
        }

        // Check credentials
        UsersDAO cdao = new UsersDAOImplementation();
        Users customer = cdao.getCustomer(email, password);

        if (customer != null) { 
            // Invalidate previous session and create a new one to prevent session fixation
            HttpSession oldSession = req.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession session = req.getSession(true);
            session.setAttribute("userId", customer.getId());
            session.setAttribute("mail", email);
            session.setAttribute("customer", customer);

            // Check if the user is an admin (ID = 1 means admin)
            if (customer.getId() == 1) {
                session.setAttribute("role", "admin");
                session.setAttribute("adminEmail", email);
                resp.sendRedirect("admin-dashboard.jsp"); // Redirect to admin panel
            } else {
                session.setAttribute("role", "user");
                resp.sendRedirect("userDashboard.jsp"); // Redirect to user dashboard
            }
        } else {
            req.setAttribute("error", "Invalid Credentials");
            RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
            rd.forward(req, resp);
        }
    }
}
