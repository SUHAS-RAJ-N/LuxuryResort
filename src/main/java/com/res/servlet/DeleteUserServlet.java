package com.res.servlet;

import java.io.IOException;
import com.res.DAO.UsersDAO;
import com.res.DAO.UsersDAOImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("id")); // Get user ID to be deleted
        UsersDAO usersDAO = new UsersDAOImplementation();

        // Delete user from database
        boolean isDeleted = usersDAO.deleteUser(userId);

        if (isDeleted) {
            response.sendRedirect("manageUsers.jsp"); // Redirect to the user management page after deletion
        } else {
            response.getWriter().write("Error deleting user.");
        }
    }
}
