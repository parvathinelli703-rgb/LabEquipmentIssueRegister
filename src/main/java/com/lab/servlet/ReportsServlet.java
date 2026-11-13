package com.lab.servlet;

import com.lab.dao.IssueDAO;
import com.lab.model.Issue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/reports")
public class ReportsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        IssueDAO dao = new IssueDAO();
        List<Issue> list;

        if ("overdue".equals(type)) {
            list = dao.getOverdue();
            request.setAttribute("reportTitle", "Overdue Equipment");
        } else if ("damaged".equals(type)) {
            list = dao.getDamaged();
            request.setAttribute("reportTitle", "Damaged Equipment");
        } else if ("issued".equals(type)) {
            list = dao.getAllIssued();
            request.setAttribute("reportTitle", "Currently Issued Equipment");
        } else {
            list = dao.getAllIssues();
            request.setAttribute("reportTitle", "All Issues Report");
        }

        request.setAttribute("reportList", list);
        request.getRequestDispatcher("reports.jsp").forward(request, response);
    }
}