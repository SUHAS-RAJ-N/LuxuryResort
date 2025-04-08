package com.res.servlet;

import java.io.IOException;

import com.res.DAO.UsersDAOImplementation;
import com.res.DTO.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        UsersDAOImplementation userDao = new UsersDAOImplementation();
        Users user = userDao.getUserById(userId);

        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("editUser.jsp").forward(request, response);
        } else {
            response.sendRedirect("manageUsers.jsp");
        }
    }
}
