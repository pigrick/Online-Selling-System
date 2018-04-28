<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/template/header.jsp" %>
<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Product Name</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Total Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="orderdetail" items="${shoppingcart.orderDetails}">
            <tr>
                <td><a href="">${orderdetail.product.name}</a></td>
                <td><fmt:formatNumber value="${orderdetail.price}" type="currency"/></td>
                <td>${orderdetail.quantity}</td>
                <td><fmt:formatNumber value="${orderdetail.calculateTotalPrice()}" type="currency"/></td>
            </tr>
        </c:forEach>
        <tr></tr>
        <td colspan="3"></td>
        <td><fmt:formatNumber value="${shoppingcart.calculateTotalPrice()}" type="currency"/></td>
        </tr>
        </tbody>
    </table>
    <div class="container">
        <form action="./checkout">
            <input class="btn btn-warning pull-right" type="submit" value="Checkout"/>
        </form>
    </div>
</div>


<%@include file="/WEB-INF/jsp/template/footer.jsp" %>