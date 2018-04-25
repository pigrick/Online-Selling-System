<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<%--<h1>Hello World!</h1>--%>

<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<!-- Products section -->

    <c:set var="counter" value="0" scope="page" />
    <c:forEach items="${mainCategoryNameList}" var="category">
        <c:set var="counter" value="${counter + 1}" scope="page"/>
        <c:if test="${counter eq 1}">
            <li class="active"><a href="#${category}" data-toggle="tab">${category}</a></li>
        </c:if>
        <c:if test="${counter ne 1}">
            <li><a href="#${category}" data-toggle="tab">${category}</a></li>
        </c:if>
    </c:forEach>



<!-- / Products section -->










<%@include file="/WEB-INF/jsp/template/footer.jsp"%>


<%--<form action="/logout" method="post">
    <button type="submit">Logout</button>
</form>--%>
</body>
</html>