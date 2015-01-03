<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta charset="utf-8">
		<c:url value="/res/css/common.css" var="cssUrl"/>
		<link rel="stylesheet" href="${cssUrl}">
		<title><tiles:getAsString name="title"/></title>
	</head>
	<body>
		<table class="content">
			<tr class="header">
				<td colspan="2"><tiles:insertAttribute name="header" /></td>
			</tr>
			<tr>
				<td class="menu"><tiles:insertAttribute name="menu" /></td>
				<td class="body"><tiles:insertAttribute name="body" /></td>
			</tr>
			<tr class="footer">
				<td colspan="2"><tiles:insertAttribute name="footer" /></td>
			</tr>
		</table>
	</body>
</html>