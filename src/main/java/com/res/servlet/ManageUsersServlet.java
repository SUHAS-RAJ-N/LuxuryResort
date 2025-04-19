package com.res.servlet;

import com.res.DAO.UsersDAO;
import com.res.DAO.UsersDAOImplementation;
import com.res.DTO.Users;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
@WebServlet("/manageUsers")
public class ManageUsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsersDAO usersDAO = new UsersDAOImplementation();  
        ArrayList<Users> usersList = usersDAO.getAllUsers();
        
        System.out.println("Users List: " + usersList);  // Log the list to ensure data is being fetched

        request.setAttribute("users", usersList);
        request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
    }
}
