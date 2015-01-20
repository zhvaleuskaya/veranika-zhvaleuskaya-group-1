<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:if test="${category ne null}">
	<h2>${category.name}</h2>
	
	<div class="content">
		<c:if test="${category.category ne null}">
			<c:url value="/category/${category.category.id}" var="categoryUrl"/>
			<p>Parent category: <a href="${categoryUrl}">${category.category.name}</a></p>
		</c:if>
		
		<c:if test="${not empty category.subcategories}">
			Subcategories:<br>
			<c:forEach items="${category.subcategories}" var="category">
				<t:category category="${category}"/>
			</c:forEach>
			<br>
		</c:if>
		
		<c:if test="${not empty category.products}">
			Products:<br>
			<c:forEach items="${category.products}" var="product">
				<t:product product="${product}"/>
			</c:forEach>
		</c:if>
	</div>
</c:if>