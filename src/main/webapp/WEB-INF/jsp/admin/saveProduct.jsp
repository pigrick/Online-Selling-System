<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 4/25/2018
  Time: 1:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>

<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<body>

${title}

<h1>Save Product</h1>

<form:form modelAttribute="productForm" action="${pageContext.request.contextPath}/admin/product/save" method="post">

    <form:hidden path="id"/>
    <form:select path="categoryId">
        <form:option value="">Select Category</form:option>
        <c:forEach items="${categories}" var="row">
            <form:option value="${row.id}">${row.name}</form:option>
        </c:forEach>
    </form:select>


</form:form>


</body>



<%@include file="/WEB-INF/jsp/template/footer.jsp"%>
