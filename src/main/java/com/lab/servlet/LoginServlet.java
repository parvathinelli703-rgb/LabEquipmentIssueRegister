package com.lab.servlet;

import com.lab.dao.StaffDAO;
import com.lab.model.Staff;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String staffId = request.getParameter("staffId");
        String password = request.getParameter("password");

        StaffDAO dao = new StaffDAO();
        Staff staff = dao.authenticate(staffId, password);

        if (staff != null) {
            HttpSession session = request.getSession();
            session.setAttribute("staff", staff);
            session.setAttribute("staffName", staff.getName());
            session.setAttribute("staffId", staff.getStaffId());
            response.sendRedirect("dashboard");
        } else {
            response.sendRedirect("login.jsp?error=Invalid Staff ID or Password");
        }
    }
}