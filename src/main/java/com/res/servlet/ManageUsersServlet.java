package com.res.servlet;

import java.io.IOException;
import java.util.ArrayList;
import com.res.DAO.UsersDAO;
import com.res.DAO.UsersDAOImplementation;
import com.res.DTO.Users;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/manageUsers")
public class ManageUsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsersDAO cdao = new UsersDAOImplementation();
        ArrayList<Users> userList = cdao.getAllUsers();  // Fetch all users from the database

        // Set the user list as a request attribute
        request.setAttribute("userList", userList);

        // Optionally, set a message if needed
        String message = (userList.isEmpty()) ? "No users found" : null;
        request.setAttribute("message", message);

        // Forward the request to the manageUsers.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageUsers.jsp");
        dispatcher.forward(request, response);
    }
}
