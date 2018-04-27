<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<table border="1">
    <tr>
        <th>Product Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Total Price</th>
    </tr>
    <c:forEach var="orderdetail" items="${shoppingcart.orderDetails}">
        <tr>
            <td><a href="">${orderdetail.product.name}</a></td>
            <td><fmt:formatNumber value = "${orderdetail.price}" type = "currency" /></td>
            <td>${orderdetail.quantity}</td>
            <td><fmt:formatNumber value = "${orderdetail.calculateTotalPrice()}" type = "currency" /></td>
        </tr>
    </c:forEach>
    <tr></tr>
    <td colspan="3"></td>
    <td><fmt:formatNumber value = "${shoppingcart.calculateTotalPrice()}" type = "currency" /></td>
    </tr>
    <form action="./checkout">
        <input type="submit" value="Checkout" />
    </form>
</table>


<%@include file="/WEB-INF/jsp/template/footer.jsp"%>