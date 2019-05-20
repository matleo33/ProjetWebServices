<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean class="java.util.ArrayList" id="vehicleList" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Managing vehicle page</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<h1>Welcome to the vehicle managing page</h1>
	<c:forEach var="vehicle" items="${vehicleList}">
		<div style="margin-top:10px">
			Brand : <c:out value="${vehicle.getBrand()}"/><br/>
			Model : <c:out value="${vehicle.getModel()}"/><br/>
			Kilometers : <c:out value="${vehicle.getKilometers()}"/><br/>
			Price : <c:out value="${vehicle.getPrice()}"/> €<br/>
			<button onClick="${m_gm.sellVehicle($vehicle)}">Sell Vehicle</button>
		</div>
	</c:forEach>
</body>
</html>