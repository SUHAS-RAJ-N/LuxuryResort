package com.res.servlet;

import com.res.DAO.FeedbackDAO;
import com.res.DAO.FeedbackDAOImplementation;
import com.res.DTO.Feedback;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/manageFeedback")
public class ManageFeedbackServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FeedbackDAO feedbackDAO = new FeedbackDAOImplementation();
        List<Feedback> feedbackList = feedbackDAO.getAllFeedback();

        request.setAttribute("feedbackList", feedbackList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageFeedback.jsp");
        dispatcher.forward(request, response);
    }
}
