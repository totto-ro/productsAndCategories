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
			<table>
				<thead>
					<tr>
						<th> Product Name </th>
						<th> Category Name </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="element" items="${productCategoryList}">
					<tr>
						<td><a href="/products/${ element.product_id }">${element.name}</a></td>
						<td><a href="/categories/${ element.category_id }">${element.name}</a></td>				
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="div">
				<a class="links" href="/products/new">New Product </a>
				<a class="links" href="/categories/new"> New Category</a>
			</div>
		</main>
	</body>
</html>