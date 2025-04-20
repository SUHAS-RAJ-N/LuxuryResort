package com.res.servlet;

import java.io.IOException;

import com.res.DAO.UsersDAOImplementation;
import com.res.DTO.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password"); // Password field (can be empty)

        UsersDAOImplementation userDao = new UsersDAOImplementation();
        Users user = new Users();
        user.setId(userId);
        user.setName(name);
        user.setPhone(phone);
        user.setMail(mail);

        // Only update password if it's not empty
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(password); // Set new password
        } else {
            // Don't update password if left blank
            user.setPassword(null); // Or retain the existing password logic in your DAO
        }

        boolean isUpdated = userDao.updateCustomer(user);

        if (isUpdated) {
            response.sendRedirect("manageUsers.jsp"); // Redirect to user management page after update
        } else {
            response.getWriter().write("Error updating user.");
        }
    }
}
