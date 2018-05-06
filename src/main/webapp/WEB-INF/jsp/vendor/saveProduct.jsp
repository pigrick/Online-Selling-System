<%--
  Created by IntelliJ IDEA.
  User: Erdenebayar
  Date: 5/2/2018
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>

<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<body>

<div class="col-sm-10 col-sm-offset-2 col-md-10 col-md-offset-2 main">

<div class="container">
    <div class="page-header">
        <h1>${title}</h1>
    </div>

    <c:if test="${!empty message}">
        <c:choose>
            <c:when test="${message.type eq 'SUCCESS'}">
                <div class="alert alert-success alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>${message.message}</strong>
                </div>
            </c:when>
            <c:when test="${message.type eq 'ERROR'}">
                <div class="alert alert-danger alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>${message.message}</strong>
                </div>
            </c:when>
        </c:choose>
    </c:if>

<form:form modelAttribute="productForm" action="${pageContext.request.contextPath}/vendor/product/save" method="post" enctype="multipart/form-data">
    <form:hidden path="id"/>
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
                <select name="categoryId">
                    <option value="">Select Category</option>
                    <c:forEach items="${categories}" var="row">
                        <option value="${row.id}">${row.name}</option>
                        <c:if test="${row.childCategories ne null}">
                            <c:forEach items="${row.childCategories}" var="cRow">
                                <option value="${cRow.id}">---${cRow.name}</option>
                                <c:if test="${cRow.childCategories ne null}">
                                    <c:forEach items="${cRow.childCategories}" var="sRow">
                                        <option value="${sRow.id}">------${sRow.name}</option>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><label>Name:</label></td>
            <td><form:input path="name" /></td>
        </tr>

        <tr>
            <td><label>Quantity:</label></td>
            <td><form:input path="quantity" type="number"/></td>
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
            <td><label>Image:</label></td>
            <td><input type="file" name="file" /><br/></td>
        </tr>

        <tr>
            <td><label></label></td>
            <td><a href="/vendor/product/all"
                    onclick="$window.location.href='vendor/productManagement'"><button class="btn btn-primary">Save</button></a></td>
        </tr>

        </tbody>
    </table>

</form:form>

</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary" onclick="create.submit()">Save changes</button>
</div>

<script type="text/javascript">
    $(function () {
        create.init();
    });
</script>



<%@include file="/WEB-INF/jsp/template/footer.jsp"%>
<c:if test="${!empty message}">
    <script>
        $.sticky('<spring:message code="${message.message}"/>', {autoclose : 5000, position: "top-right", type: "st-${fn:toLowerCase(message.type)}" });

        <c:if test="${message.type eq 'SUCCESS'}">
        create.success();
        </c:if>
    </script>
</c:if>
