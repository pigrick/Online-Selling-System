<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 4/25/2018
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <div class="container">
        <div class="page-header">
            <h1>${title}</h1>
        </div>

        <form:form action="/admin/category/save" method="post" commandName="category">

            <form:hidden path="name" value="${category.categoryname}" />
            <div class="form-group"><form:errors path="mainCategoryName" cssStyle="color: red" />
                <label for="mainCategoryName">MainCategory</label>
                <form:input path="mainCategoryName" id="mainCategoryName" class="form-Control" />
            </div>

            <div class="form-group"><form:errors path="subCategoryName" cssStyle="color: red" />
                <label for="subCategoryName">SubCategory</label>
                <form:input path="subCategoryName" id="subCategoryName" class="form-Control" />
            </div>

            <input type="submit" value="submit" class="btn btn-default">
            <a href="<c:url value="/admin/category/m" />" class="btn btn-default">Cancel</a>

        </form:form>
    </div>
</div>




<%@include file="/WEB-INF/jsp/template/footer.jsp"%>