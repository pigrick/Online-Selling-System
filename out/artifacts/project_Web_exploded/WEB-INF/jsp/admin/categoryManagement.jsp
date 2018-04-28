<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 4/25/2018
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/WEB-INF/jsp/template/header.jsp"%>




<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <h1 class="page-header">Category Management</h1>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>MainCategory</th>
                <th>SubCategory</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categoryList}" var="category">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>

                    <td>
                        <a href="/admin/category/save?id=${category.id}">
                            <span class="glyphicon glyphicon-pencil"></span></a>
                        <a href="/admin/ca/d?id=${category.id}">
                            <span class="glyphicon glyphicon-remove"></span></a>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="/admin/category/save"><button class="btn btn-primary">Add Category</button></a>
    </div>
</div>
</div>
</div>



<%@include file="/WEB-INF/jsp/template/footer.jsp"%>