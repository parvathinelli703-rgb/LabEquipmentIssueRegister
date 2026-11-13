package com.lab.servlet;

import com.lab.dao.EquipmentDAO;
import com.lab.model.Equipment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewEquipment")
public class ViewEquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("search");
        EquipmentDAO dao = new EquipmentDAO();
        List<Equipment> list;
        if (keyword != null && !keyword.trim().isEmpty()) {
            list = dao.searchEquipment(keyword.trim());
        } else {
            list = dao.getAllEquipment();
        }
        request.setAttribute("equipmentList", list);
        request.getRequestDispatcher("viewEquipment.jsp").forward(request, response);
    }
}