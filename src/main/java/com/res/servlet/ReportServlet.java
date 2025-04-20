package com.res.servlet;

import com.res.DAO.ReportsDAO;
import com.res.DAO.ReportsDAOImplementation;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Map;
@WebServlet("/ReportsServlet")
public class ReportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ReportsDAO dao = new ReportsDAOImplementation();
        Map<String, Integer> stats = dao.getBookingStats();

        // Debugging line: print the stats to console/log
        System.out.println("Booking Stats: " + stats);

        request.setAttribute("stats", stats);
        request.getRequestDispatcher("viewReports.jsp").forward(request, response);
    }
}
