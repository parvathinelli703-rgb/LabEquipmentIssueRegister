<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="card" style="text-align:center; padding:50px;">
        <h2 style="color:#dc2626;">Something went wrong!</h2>
        <p style="margin:20px 0;"><%= exception != null ? exception.getMessage() : "Unknown error" %></p>
        <a href="dashboard" class="btn">Go to Dashboard</a>
    </div>
</div>
</body>
</html>