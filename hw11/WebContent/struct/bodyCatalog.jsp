<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<h2>Catalog</h2>

<div class="content">
	<c:forEach items="${categories}" var="category">
		<t:category category="${category}"/>
	</c:forEach>
</div>