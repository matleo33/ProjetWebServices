<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- USEBEAN / JSTL -->
    <jsp:useBean class="java.util.ArrayList" id="vehicleList" scope="request" />
    <jsp:useBean class="java.util.ArrayList" id="carPartList" scope="request" />
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
	<c:forEach var="carPart" items="${carPartList}">
		<div class="col-sm-3" style="margin-top:10px">
			Name : <c:out value="${carPart.getName()}"/><br/>
			Price : <c:out value="${carPart.getPrice()}"/><br/>
			Quantity : <c:out value="${carPart.getQuantity()}"/><br/>
			Vehicle : <c:out value="${carPart.getVehicle()}"/><br/>
		</div>
	</c:forEach>
	
	<form method="post">
		<h3>Add a car part</h3>
		<label>Name</label>
		<input type="text" name="name"/>
		<label>Price</label>
		<input type="number" name="price"/>
		<label>Quantity</label>
		<input type="number" min="0" name="quantity"/>
		<select name="model">
		<c:forEach var="vehicle" items="${vehicleList}">
			<option value="<c:out value="${vehicle.getBrand()}"/>&<c:out value="${vehicle.getModel()}" />">
				<c:out value="${vehicle.getBrand()}"/>,<c:out value="${vehicle.getModel()}" />
			</option>
		</c:forEach>
		</select>
		<input type="submit" value="Add"/>
	</form>
	
</body>
</html>