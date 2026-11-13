<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.lab.model.Issue" %>
<%
    if (session.getAttribute("staff") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Issue> list = (List<Issue>) request.getAttribute("reportList");
    String title = (String) request.getAttribute("reportTitle");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Reports</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Reports</h1>
        <div class="nav">
            <a href="dashboard">Dashboard</a>
            <a href="logout">Logout</a>
        </div>
    </div>

    <div class="card">
        <div style="margin-bottom:20px; display:flex; gap:10px; flex-wrap:wrap;">
            <a href="reports?type=issued" class="btn">Currently Issued</a>
            <a href="reports?type=overdue" class="btn" style="background:#ea580c;">Overdue</a>
            <a href="reports?type=damaged" class="btn btn-danger">Damaged</a>
            <a href="reports" class="btn" style="background:#64748b;">All Issues</a>
        </div>

        <h3 style="margin-bottom:15px;"><%= title != null ? title : "All Issues" %></h3>

        <table class="table">
            <thead>
                <tr>
                    <th>Issue ID</th>
                    <th>Student</th>
                    <th>Equipment</th>
                    <th>Issue Date</th>
                    <th>Due Date</th>
                    <th>Return Date</th>
                    <th>Condition</th>
                    <th>Status</th>
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
                    <td><%= iss.getReturnDate() != null ? iss.getReturnDate() : "-" %></td>
                    <td><%= iss.getConditionOnReturn() != null ? iss.getConditionOnReturn() : "-" %></td>
                    <td>
                        <span style="padding:4px 10px; border-radius:20px; font-size:12px; font-weight:600;
                            background:<%= "Issued".equals(iss.getStatus()) ? "#dbeafe" :
                                          "Overdue".equals(iss.getStatus()) ? "#ffedd5" :
                                          "Damaged".equals(iss.getStatus()) ? "#fee2e2" : "#dcfce7" %>;
                            color:<%= "Issued".equals(iss.getStatus()) ? "#1e40af" :
                                     "Overdue".equals(iss.getStatus()) ? "#c2410c" :
                                     "Damaged".equals(iss.getStatus()) ? "#991b1b" : "#166534" %>;">
                            <%= iss.getStatus() %>
                        </span>
                    </td>
                </tr>
            <% } } else { %>
                <tr><td colspan="8" style="text-align:center;">No records found</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>