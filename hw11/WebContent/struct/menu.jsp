<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value='/' var="homeUrl"/>
<c:url value='/catalog' var="catalogUrl"/>

<a href="${homeUrl}">Home</a><br>
<a href="${catalogUrl}">Catalog</a><br>