<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New Person</title>
		<link rel="stylesheet" type="text/css" href="/css/styles.css">
	</head>
	<body>
		<main>
			<nav>
			    <a href="/">Home</a>
			</nav>
			<h1>New Category</h1>
			<div>
				<form:form action="/categories/new" method="POST" modelAttribute="category">
			    <p>
			        <form:label path="name">Name:</form:label>
			        <form:errors path="name"/>
			        <form:input path="name"/>
			    </p> 
			    <input class="bSummit" type="submit" value="Create"/>
			</form:form>    
			</div>
		</main>
	
	</body>
</html>