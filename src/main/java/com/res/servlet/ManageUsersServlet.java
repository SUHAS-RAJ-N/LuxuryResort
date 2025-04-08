package com.res.servlet;

import com.res.DAO.UsersDAO;
import com.res.DAO.UsersDAOImplementation;
import com.res.DTO.Users;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/manageUsers")
public class ManageUsersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch users from the database
        UsersDAO usersDAO = new UsersDAOImplementation();
        ArrayList<Users> usersList = usersDAO.getAllUsers();

        // Set the users list as a request attribute
        request.setAttribute("users", usersList);

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageUsers.jsp");
        dispatcher.forward(request, response);
    }
}
