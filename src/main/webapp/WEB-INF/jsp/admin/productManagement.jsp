<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 4/25/2018
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<h1>Product Inventory</h1>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <h1 class="page-header">Product Inventory</h1>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Category</th>
                <th>Description</th>
                <th>Summary</th>
                <th>Price</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${product}" var="product">
                <tr>
                    <td><img src="<c:url value="/images/${product.id}/0.png" />"
                             style="width: 50px; height: 50px"></td>
                    <td>${product.name}</td>
                    <td>${product.productCategory.mainCategoryName}</td>
                    <td>${product.quantity}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>

                    <td>
                        <a href="#">
                            <span class="glyphicon glyphicon-info-sign"></span></a>
                        <a href="#">
                            <span class="glyphicon glyphicon-pencil"></span></a>
                        <a href="#">
                            <span class="glyphicon glyphicon-remove"></span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="/admin/pd/s"><button class="btn btn-primary">Add Product</button></a>
    </div>
</div>
</div>
</div>



<%@include file="/WEB-INF/jsp/template/footer.jsp"%>