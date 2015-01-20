<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="product" required="true" type="unic.mentoring.springmvc.data.ProductData" %>

<c:url value="/product/${product.id}" var="productUrl"/>
<a href="${productUrl}">${product.name}</a><br>