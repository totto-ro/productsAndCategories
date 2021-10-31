<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Product Info</title>
		<link rel="stylesheet" type="text/css" href="./css/product_category.css">
	</head>
	<body>
		<main>
			<nav>
				<a href="/">Dashboard</a>
			</nav>
			<section class="leftSide">
            <h1>Title: {{categories.name}}</h1>
            <div class="tableDiv">
                <div>
                    <ul>
                        <li><h2>Products:</h2></li>
                        <c:forEach items="${ product }" var="element">
                        <li>${ element.name }</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <a class="homelink" href="/">Home</a>
        </section>
        <section class="rightSide">
            <form:form action="/ninjas/new" method="POST" modelAttribute="ninja">
                <h1>Add a Product to: {{categories.name}} </h1>
                <div class="inputLines">
                    
                    <form:label path="product"><h1>Add Product: </h1></form:label>
			        <form:errors path="product"/>
			        <form:select path="product">
			        <form:option value=""> -- Select Product -- </form:option>
			        <c:forEach items="${ product }" var="element">
			        	<form:option value="${ element.getId() }">${ element.getName() }</form:option>
			        </c:forEach>
			        </form:select>
			        
                </div>
                <button type="submit">
                    Add
                </button>
            </form:form> 
        </section>
		</main>
	</body>
</html>