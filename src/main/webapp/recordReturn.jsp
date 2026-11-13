<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.lab.model.Issue" %>
<%
    if (session.getAttribute("staff") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Issue> list = (List<Issue>) request.getAttribute("issuedList");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Record Return</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Record Return</h1>
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
        <h3 style="margin-bottom:15px;">Currently Issued Equipment</h3>
        <table class="table">
            <thead>
                <tr>
                    <th>Issue ID</th>
                    <th>Student</th>
                    <th>Equipment</th>
                    <th>Issue Date</th>
                    <th>Due Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <% if (list != null && !list.isEmpty()) {
                for (Issue iss : list) { %>
                <tr>
                    <td><%= iss.getIssueId() %></td>
                    <td><%= iss.getStudentName() %> (<%= iss.getStudentRoll() %>)</td>
                    <td><%= iss.getEquipmentName() %></td>
                    <td><%= iss.getIssueDate() %></td>
                    <td><%= iss.getDueDate() %></td>
                    <td>
                        <form action="recordReturn" method="post" style="display:inline;">
                            <input type="hidden" name="issueId" value="<%= iss.getIssueId() %>">
                            <input type="date" name="returnDate" required value="<%= java.time.LocalDate.now() %>" style="padding:5px; margin-right:5px;">
                            <select name="condition" required style="padding:5px; margin-right:5px;">
                                <option value="Good">Good</option>
                                <option value="Damaged">Damaged</option>
                            </select>
                            <button type="submit" class="btn btn-success" style="padding:6px 12px;">Return</button>
                        </form>
                    </td>
                </tr>
            <% } } else { %>
                <tr><td colspan="6" style="text-align:center;">No equipment currently issued</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>