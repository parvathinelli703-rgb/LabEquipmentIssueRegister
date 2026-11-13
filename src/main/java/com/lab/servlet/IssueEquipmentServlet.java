package com.lab.servlet;

import com.lab.dao.EquipmentDAO;
import com.lab.dao.IssueDAO;
import com.lab.model.Equipment;
import com.lab.model.Issue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/issueEquipment")
public class IssueEquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EquipmentDAO dao = new EquipmentDAO();
        List<Equipment> list = dao.getAllEquipment();
        request.setAttribute("equipmentList", list);
        request.getRequestDispatcher("issueEquipment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Issue issue = new Issue();
            issue.setStudentRoll(request.getParameter("studentRoll"));
            issue.setStudentName(request.getParameter("studentName"));
            issue.setEquipmentId(Integer.parseInt(request.getParameter("equipmentId")));
            issue.setIssueDate(Date.valueOf(request.getParameter("issueDate")));
            issue.setDueDate(Date.valueOf(request.getParameter("dueDate")));
            issue.setIssuedBy((String) request.getSession().getAttribute("staffId"));

            IssueDAO dao = new IssueDAO();
            boolean success = dao.issueEquipment(issue);

            if (success) {
                response.sendRedirect("issueEquipment?msg=Equipment issued successfully");
            } else {
                response.sendRedirect("issueEquipment?error=Failed to issue. Insufficient quantity or invalid data");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("issueEquipment?error=Error occurred while issuing equipment");
        }
    }
}