<!--

created by Pagmaa


-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>

<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<body>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

<div class="container">
    <div class="page-header">
        <h1>${title}</h1>
    </div>

<form:form modelAttribute="productForm" action="${pageContext.request.contextPath}/vendor/product/save" method="post">
    <table class="table table-hover">
        <tbody>
        <tr>
            <td scope="col"><label>Category:</label></td>
            <td scope="col">
    <form:hidden path="id"/>
    <form:select path="categoryId">
        <form:option value="">Select Category</form:option>
        <c:forEach items="${categories}" var="row">
            <form:option value="${row.id}">${row.name}</form:option>
        </c:forEach>
    </form:select>
            </td>
        </tr>

        <tr>
            <td><label>Name:</label></td>
            <td><form:input path="name" /></td>
        </tr>

        <tr>
            <td><label>Quantity:</label></td>
            <td><form:input path="quantity" /></td>
        </tr>

        <tr>
            <td><label>Price:</label></td>
            <td><form:input path="price" /></td>
        </tr>

        <tr>
            <td><label>Description:</label></td>
            <td><form:textarea path="description"/></td>
        </tr>

        <tr>
            <td><label></label></td>
            <td><a href="/vendor/product/all"
                    onclick="$window.location.href='productManagement'"><button class="btn btn-primary">Add Product</button></a></td>
        </tr>

        </tbody>
    </table>

</form:form>

</div>
</div>
</body>



<%@include file="/WEB-INF/jsp/template/footer.jsp"%>
