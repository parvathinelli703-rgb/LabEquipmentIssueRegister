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
    <title>Issue Equipment</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Issue Equipment</h1>
        <div class="nav">
            <a href="dashboard">Dashboard</a>
            <a href="logout">Logout</a>
        </div>
    </div>

    <% if (request.getParameter("msg") != null) { %>
        <div class="alert alert-success"><%= request.getParameter("msg") %></div>
    <% } %>
    <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-error"><%= request.getParameter("error") %></div>
    <% } %>

    <div class="card">
        <form action="issueEquipment" method="post">
            <div class="form-group">
                <label>Student Roll Number</label>
                <input type="text" name="studentRoll" required>
            </div>
            <div class="form-group">
                <label>Student Name</label>
                <input type="text" name="studentName" required>
            </div>
            <div class="form-group">
                <label>Select Equipment</label>
                <select name="equipmentId" required>
                    <option value="">-- Select Equipment --</option>
                    <% if (list != null) {
                        for (Equipment eq : list) {
                            if (eq.getAvailableQuantity() > 0) { %>
                        <option value="<%= eq.getEquipmentId() %>">
                            <%= eq.getName() %> (Available: <%= eq.getAvailableQuantity() %>)
                        </option>
                    <% } } } %>
                </select>
            </div>
            <div class="form-group">
                <label>Issue Date</label>
                <input type="date" name="issueDate" required value="<%= java.time.LocalDate.now() %>">
            </div>
            <div class="form-group">
                <label>Due Date</label>
                <input type="date" name="dueDate" required>
            </div>
            <button type="submit" class="btn">Issue Equipment</button>
        </form>
    </div>
</div>
</body>
</html>