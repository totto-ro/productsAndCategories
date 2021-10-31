<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New Product</title>
		<link rel="stylesheet" type="text/css" href="./css/styles.css">
	</head>
	<body>
		<main>
			<nav>
			    <a href="/">Home</a>
			</nav>
			<h1>New Product</h1>
			<div>
				<form:form action="/products/new" method="POST" modelAttribute="product">
			    <p>
				    <form:label path="name"> Name: </form:label>
			        <form:errors path="name"/>
			        <form:input path="name"/>
			    </p>
			    <p>
				    <form:label path="description">Description: </form:label>
			        <form:errors path="description"/>
			        <form:textarea path="description"/>
			    </p>
			    <p>
				    <form:label path="price">Price: </form:label>
			        <form:errors path="price"/>
			        <form:input path="price"/>
			    </p>   
			    <input class="bSummit" type="submit" value="Create"/>
			</form:form>    
			</div>
		</main>
	
	</body>
</html>