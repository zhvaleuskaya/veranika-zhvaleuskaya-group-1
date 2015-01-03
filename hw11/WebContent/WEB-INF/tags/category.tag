<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="category" required="true" type="unic.mentoring.springmvc.data.CategoryData" %>

<c:url value="/category/${category.id}" var="categoryUrl"/>
<a href="${categoryUrl}">${category.name}</a><br>