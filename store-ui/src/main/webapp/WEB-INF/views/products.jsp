<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Product listing</h1>
	<table>
	<tr><td>Name</td><td>Price</td></tr>
	<c:forEach items="${products}" var="product">
	<tr>
		<td>${product.name}</td>
		<td>${product.price}</td>
	</tr>		
	</c:forEach>
	
	</table>
</body>
</html>