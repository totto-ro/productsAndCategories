<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Product Info</title>
		<link rel="stylesheet" type="text/css" href="/css/product_category.css">
	</head>
	<body>
		<main>
			<section class="leftSide">
            <h1>Product: <c:out value="${product.name}"/></h1>
            <div class="tableDiv">
                <div>
                    <ul>
                        <li><h2>Categories:</h2></li>
                        <c:forEach items="${ categorytList }" var="element">
                        <li>${ element.name }</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <a class="homelink" href="/">Home</a>
        </section>
	      <section class="rightSide">
	           <form:form action="/products/${product.id}" method="POST">
	           <input type="hidden" name="_method" value="put">
	                <div class="inputLines">
	                    <label for="name">Add category:</label>
				        <select class="select" name="id" id="id">
				        <option value=""> -- Select Category -- </option>
				        <c:forEach items="${ notInProducts }" var="element">
				        	<option class="option" value="${ element.id }">${ element.name }</option>
				        </c:forEach>
				        </select>
	                </div>
	                <button type="submit">
	                    Add
	                </button>
	            </form:form> 
	        </section>
		</main>
	</body>
</html>