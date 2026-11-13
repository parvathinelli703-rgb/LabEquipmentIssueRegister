<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lab.model.Staff" %>
<%
    Staff staff = (Staff) session.getAttribute("staff");
    if (staff == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Lab Equipment</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Welcome, <%= staff.getName() %></h1>
        <div class="nav">
            <a href="logout">Logout</a>
        </div>
    </div>

    <div class="card">
        <h2 style="margin-bottom:10px;">Staff Dashboard</h2>
        <p style="color:#64748b;">Select an option below</p>

        <div class="menu-grid">
            <a href="viewEquipment" class="menu-item">View / Search Equipment</a>
            <a href="issueEquipment" class="menu-item">Issue Equipment</a>
            <a href="recordReturn" class="menu-item">Record Return</a>
            <a href="reports" class="menu-item">Reports</a>
            <a href="logout" class="menu-item" style="background:linear-gradient(135deg,#dc2626,#b91c1c);">Logout</a>
        </div>
    </div>
</div>
</body>
</html>