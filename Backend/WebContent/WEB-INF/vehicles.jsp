<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean class="java.util.ArrayList" id="vehicleList" scope="request" />
<jsp:useBean class="garage.GarageManager" id="gm" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Managing vehicle page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<%@ include file="menu.jsp" %>
	<h1>Welcome to the vehicle managing page</h1>
	<c:forEach var="vehicle" items="${vehicleList}">
		<div class="col-sm-3">
			<form method="post" style="margin-top:10px">
				<input hidden="true" name="vehicle" value='<c:out value="${vehicle.getBrand()}" />/<c:out value="${vehicle.getModel()}" />/<c:out value="${vehicle.getKilometers()}"/>/<c:out value="${vehicle.getPrice()}"/>'/>
				Brand : <c:out value="${vehicle.getBrand()}"/><br/>
				Model : <c:out value="${vehicle.getModel()}"/><br/>
				Kilometers : <c:out value="${vehicle.getKilometers()}"/><br/>
				Price : <c:out value="${vehicle.getPrice()}"/> €<br/>
				<input type="submit" value="Sell Vehicle" />
			</form>
		</div>
	</c:forEach>
	
	<form method="post">
		<h3>Add a car</h3>
		<label>Brand</label>
		<input type="text" name="brand"/>
		<label>Model</label>
		<input type="text" name="model"/>
		<label>Kilometers</label>
		<input type="number" min="0" name="kilometers"/>
		<label>Price</label>
		<input type="number" min="0" name="price"/>
		<input type="submit" value="Add"/>
	</form>
	
</body>
</html>