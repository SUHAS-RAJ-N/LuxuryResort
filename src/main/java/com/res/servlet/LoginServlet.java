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

        // Retrieve email and password from the login form
        String email = req.getParameter("mail");
        String password = req.getParameter("password");

        // Input validation
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("error", "Email and Password are required.");
            RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
            rd.forward(req, resp);
            return;
        }

        // Validate the user's credentials
        UsersDAO cdao = new UsersDAOImplementation();
        Users customer = cdao.getCustomer(email, password);

        if (customer != null) {
            // Invalidate old session if it exists
            HttpSession oldSession = req.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            // Create a new session
            HttpSession session = req.getSession(true);
            session.setAttribute("userId", customer.getId());
            session.setAttribute("mail", email);
            session.setAttribute("customer", customer);
            session.setAttribute("role", customer.getRole());

            // Check the role and redirect accordingly
            if ("admin".equalsIgnoreCase(customer.getRole())) {
                session.setAttribute("adminEmail", email);
                resp.sendRedirect("admin-dashboard.jsp");
            } else {
                resp.sendRedirect("userDashboard.jsp");
            }
        } else {
            // Invalid credentials
            req.setAttribute("error", "Invalid Credentials");
            RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
            rd.forward(req, resp);
        }
    }
}
