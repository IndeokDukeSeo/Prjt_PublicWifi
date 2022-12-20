<%@ page import="importclass.jdbcController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Delete History data</title>

    <%
        request.setCharacterEncoding("UTF-8");
        String stringId = request.getParameter("delID");
        int id = Integer.parseInt(stringId);
        try {
            jdbcController.deleteUserHistory(id);
            System.out.println("ID: "+ stringId + " has been deleted");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    %>

</head>
<body>

</body>
</html>
