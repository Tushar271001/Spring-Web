<%@ page import="com.maven.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserProfile</title>
</head>
<body>
<%
   User user =(User) session.getAttribute("user");
%>
<h2>
    UserInfo
</h2>
<ul>
    <%if(user !=null){%>
    <li>First Name:<%=user.getFirstName()%></li>
    <li>Last Name:<%=user.getLastName()%></li>
    <li>Email:<%=user.getUserEmail()%></li>
    <li>Password:<%=user.getPassword()%></li>
    <%}else{
        response.sendRedirect("login");
    }%>
</ul>
<form method="post" action="logout">
    <button type="submit">Logout</button>
</form>
</body>
</html>
