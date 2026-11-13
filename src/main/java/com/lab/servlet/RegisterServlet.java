package com.lab.servlet;

import com.lab.dao.StaffDAO;
import com.lab.model.Staff;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String staffId = request.getParameter("staffId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String role = request.getParameter("role");

        // Validation
        if (staffId == null || staffId.trim().isEmpty() ||
                name == null || name.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {

            response.sendRedirect("register.jsp?error=All fields are required");
            return;
        }

        if (!password.equals(confirmPassword)) {
            response.sendRedirect("register.jsp?error=Passwords do not match");
            return;
        }

        if (password.length() < 4) {
            response.sendRedirect("register.jsp?error=Password must be at least 4 characters");
            return;
        }

        Staff staff = new Staff();
        staff.setStaffId(staffId.trim());
        staff.setName(name.trim());
        staff.setPassword(password);
        staff.setRole(role != null ? role : "LabAssistant");

        StaffDAO dao = new StaffDAO();
        boolean success = dao.registerStaff(staff);

        if (success) {
            response.sendRedirect("login.jsp?msg=Registration successful! Please login");
        } else {
            response.sendRedirect("register.jsp?error=Staff ID already exists. Try another ID");
        }
    }
}