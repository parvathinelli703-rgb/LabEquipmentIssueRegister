<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lab Equipment - Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login-box">
    <h2>Lab Equipment Issue Register</h2>
    <h3 style="text-align:center; color:#64748b; margin-bottom:20px;">Staff Login</h3>

    <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-error"><%= request.getParameter("error") %></div>
    <% } %>
    <% if (request.getParameter("msg") != null) { %>
        <div class="alert alert-success"><%= request.getParameter("msg") %></div>
    <% } %>

    <form action="login" method="post">
        <div class="form-group">
            <label>Staff ID</label>
            <input type="text" name="staffId" required placeholder="e.g. LA001">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" required placeholder="Enter password">
        </div>
        <button type="submit" class="btn" style="width:100%;">Login</button>
    </form>
    <p style="margin-top:20px; text-align:center; font-size:13px; color:#64748b;">
        Demo: LA001 / lab123 &nbsp;|&nbsp; SUP001 / sup123
    </p>
    <p style="margin-top:20px; text-align:center;">
        New Staff?
        <a href="register" style="color:#2563eb; text-decoration:none; font-weight:600;">Register here</a>
    </p>
</div>
</body>
</html>