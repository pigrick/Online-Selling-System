<%@include file="/WEB-INF/include.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<!--<h1>Product Inventory</h1> -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <h1 class="page-header">Product Inventory</h1>

    <div class="table-responsive">
        <table class="table table-striped">

            <thead>
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Description</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${productList}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.quantity}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                    <td>${product.status}</td>
                    <td>
                        <a href="#">
                            <span class="glyphicon glyphicon-info-sign"></span></a>
                        <a href="/vendor/product/save?id=${product.id}">
                            <span class="glyphicon glyphicon-pencil"></span></a>
                        <a href="/vendor/product/delete?id=${product.id}"
                           onclick="if (!('Are you sure you want to delete this product?')) return false">
                            <span class="glyphicon glyphicon-remove"></span></a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <a href="/vendor/product/save"
        onclick="$window.location.href='saveProduct'"><button class="btn btn-primary">Add Product</button></a>
    </div>
</div>
</div>
</div>



<%@include file="/WEB-INF/jsp/template/footer.jsp"%>