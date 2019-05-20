<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- USEBEAN / JSTL -->
    <jsp:useBean class="java.util.ArrayList" id="vehicleList" scope="request" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Modifier le stock</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<h1>Welcome to the stock managing page</h1>
	<c:forEach var="vehicle" items="${vehicleList}">
		<div style="margin-top:10px">
			Brand : <c:out value="${vehicle.getBrand()}"/><br/>
			Model : <c:out value="${vehicle.getModel()}"/><br/>
			Kilometers : <c:out value="${vehicle.getKilometers()}"/><br/>
			Price : <c:out value="${vehicle.getPrice()}"/> €<br/>
		</div>
	</c:forEach>
</body>
</html>