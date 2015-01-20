<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta charset="utf-8">
		<c:url value="/res/css/common.css" var="cssUrl"/>
		<link rel="stylesheet" href="${cssUrl}">
		<title>JMS</title>
	</head>
	<body>
		<c:url value="/message" var="messageFormUrl"/>
		<form action="${messageFormUrl}" method="post">
			<label for="topic">Topic:</label><br>
			<input type="text" name="topic"/><br><br>
			<label for="message">Message:</label><br>
			<textarea name="message" rows="10" cols="50">Test message.</textarea><br><br>
			<input type="submit" value="Send message"/>
		</form>
		<c:if test="${not empty message}">
			<p class="message">${message}</p>
		</c:if>
	</body>
</html>