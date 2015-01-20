<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:if test="${product ne null}">
	<h2>${product.name}</h2>
	
	<div class="content">
		<c:if test="${product.category ne null}">
			<c:url value="/category/${product.category.id}" var="categoryUrl"/>
			Category: <a href="${categoryUrl}">${product.category.name}</a><br>
		</c:if>
		Description: ${product.description}
	</div>
</c:if>