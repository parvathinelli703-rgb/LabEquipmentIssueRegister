package com.lab.servlet;

import com.lab.dao.IssueDAO;
import com.lab.model.Issue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/recordReturn")
public class RecordReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        IssueDAO dao = new IssueDAO();
        List<Issue> list = dao.getAllIssued();
        request.setAttribute("issuedList", list);
        request.getRequestDispatcher("recordReturn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int issueId = Integer.parseInt(request.getParameter("issueId"));
            Date returnDate = Date.valueOf(request.getParameter("returnDate"));
            String condition = request.getParameter("condition");

            IssueDAO dao = new IssueDAO();
            boolean success = dao.recordReturn(issueId, returnDate, condition);

            if (success) {
                response.sendRedirect("recordReturn?msg=Return recorded successfully");
            } else {
                response.sendRedirect("recordReturn?error=Failed to record return");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("recordReturn?error=Error occurred");
        }
    }
}