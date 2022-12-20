<%@ page import="importclass.jdbcController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>

    <%
        request.setCharacterEncoding("UTF-8");
        String stringLat = request.getParameter("lat");
        String stringLng = request.getParameter("lng");
        Double lat = Double.parseDouble(stringLat);
        Double lng = Double.parseDouble(stringLng);
        System.out.println(lat);
        System.out.println(lng);
        jdbcController jdbcController = new jdbcController();
        try {
            jdbcController.createUserHistory(lat, lng);
            jdbcController.updateDistance(lat, lng);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    %>

</head>
<body>

</body>
</html>
