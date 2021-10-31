<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Products and Categories</title>
		<link rel="stylesheet" type="text/css" href="./css/styles.css">
	</head>
	<body>
		<main>
			<h1>Products & Categories</h1>
			<div class="div">
				<a class="links" href="/products/new">New Product </a>
				<a class="links" href="/categories/new"> New Category</a>
			</div>
			<table>
				<thead>
					<tr>
						<th> Product Name </th>
					</tr>
				</thead>
				<tbody>
					
					<tr>
						<c:forEach var="element" items="${productList}">
							<td><a href="/products/${ element.id }">${element.name}</a></td>
						</c:forEach>			
					</tr>
				</tbody>
			</table>
			<table>
				<thead>
					<tr>
						<th> Category Name </th>
					</tr>
				</thead>
				<tbody>
					
					<tr>

						<c:forEach var="element" items="${categoryList}">
							<td><a href="/categories/${ element.id }">${element.name}</a></td>	
						</c:forEach>			
					</tr>
				</tbody>
			</table>
		</main>
	</body>
</html>