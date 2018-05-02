<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<!--<h1>Product Inventory</h1> -->

<div class="col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 main">

    <h1 class="page-header">Product Inventory</h1>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Category</th>
                <th>Description</th>
                <th>Vendor Name</th>
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
                    <td>${product.category.getName()}</td>
                    <td>${product.description}</td>
                    <td>${product.vendor.getCompanyName()}</td>
                    <td>${product.status}</td>

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
        <a href="/admin/product/save"><button class="btn btn-primary">Add Product</button></a>
    </div>
</div>
</div>
</div>



<%@include file="/WEB-INF/jsp/template/footer.jsp"%>