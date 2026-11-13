<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.lab.model.Equipment" %>
<%
    if (session.getAttribute("staff") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Equipment> list = (List<Equipment>) request.getAttribute("equipmentList");
%>
<!DOCTYPE html>
<html>
<head>
    <title>View Equipment</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>View / Search Equipment</h1>
        <div class="nav">
            <a href="dashboard">Dashboard</a>
            <a href="logout">Logout</a>
        </div>
    </div>

    <div class="card">
        <form action="viewEquipment" method="get" style="display:flex; gap:10px; margin-bottom:20px;">
            <input type="text" name="search" placeholder="Search by name or description..."
                   style="flex:1; padding:10px; border-radius:8px; border:1px solid #cbd5e1;">
            <button type="submit" class="btn">Search</button>
            <a href="viewEquipment" class="btn" style="background:#64748b;">Reset</a>
        </form>

        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Total Qty</th>
                    <th>Available Qty</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
            <% if (list != null && !list.isEmpty()) {
                for (Equipment eq : list) { %>
                <tr>
                    <td><%= eq.getEquipmentId() %></td>
                    <td><%= eq.getName() %></td>
                    <td><%= eq.getTotalQuantity() %></td>
                    <td><strong style="color:<%= eq.getAvailableQuantity() > 0 ? "#16a34a" : "#dc2626" %>">
                        <%= eq.getAvailableQuantity() %></strong></td>
                    <td><%= eq.getDescription() %></td>
                </tr>
            <% } } else { %>
                <tr><td colspan="5" style="text-align:center;">No equipment found</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>