<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Staff Registration - Lab Equipment</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login-box">
    <h2>Staff Registration</h2>
    <h3 style="text-align:center; color:#64748b; margin-bottom:20px;">Create New Account</h3>

    <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-error"><%= request.getParameter("error") %></div>
    <% } %>
    <% if (request.getParameter("msg") != null) { %>
        <div class="alert alert-success"><%= request.getParameter("msg") %></div>
    <% } %>

    <form action="register" method="post">
        <div class="form-group">
            <label>Staff ID</label>
            <input type="text" name="staffId" required placeholder="e.g. LA003">
        </div>
        <div class="form-group">
            <label>Full Name</label>
            <input type="text" name="name" required placeholder="Enter full name">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" required placeholder="Enter password">
        </div>
        <div class="form-group">
            <label>Confirm Password</label>
            <input type="password" name="confirmPassword" required placeholder="Confirm password">
        </div>
        <div class="form-group">
            <label>Role</label>
            <select name="role" required>
                <option value="LabAssistant">Lab Assistant</option>
                <option value="Supervisor">Supervisor</option>
            </select>
        </div>
        <button type="submit" class="btn" style="width:100%;">Register</button>
    </form>

    <p style="margin-top:20px; text-align:center;">
        Already have an account?
        <a href="login.jsp" style="color:#2563eb; text-decoration:none; font-weight:600;">Login here</a>
    </p>
</div>
</body>
</html>