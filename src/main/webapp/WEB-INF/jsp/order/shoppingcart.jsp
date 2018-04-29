<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>

<%@include file="/WEB-INF/jsp/template/header.jsp" %>

<link rel="stylesheet" type="text/css" href="/resources/css/order/order.css">


<div class="container tpy">

    <h1 align="center">Shopping cart</h1>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Product Name</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Update Quantity</th>
            <th scope="col">Total Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="orderdetail" items="${shoppingcart.orderDetails}">

            <tr id="${orderdetail.product.id}" class='clickable-row' data-href="text">
                <input type="hidden" id="productid" name="productid" value="${orderdetail.product.id}"/>
                <td>${orderdetail.product.name}</td>
                <td id="price"><fmt:formatNumber value="${orderdetail.price}" type="currency"/></td>
                <td id="quantity">${orderdetail.quantity}</td>
                <td>
                    <input type="number" id="cartquantity2" onblur="updateQuantity(${orderdetail.product.id})" min="0" value="${orderdetail.quantity}"/>
                    <%--<button type="submit" class="btn btn-warning" onclick="updateQuantity(${orderdetail.product.id})">--%>
                        <%--Update--%>
                    <%--</button>--%>
                </td>
                <td id="totalprice"><fmt:formatNumber value="${orderdetail.calculateTotalPrice()}"
                                                      type="currency"/></td>
            </tr>

        </c:forEach>
        <tr>
            <td colspan="5"></td>
        </tr>
        <tr>
            <td colspan="3"></td>
            <td>Tax ( 7% )</td>
            <td id="tax"><fmt:formatNumber value="${shoppingcart.calculateTax()}" type="currency"/></td>
        </tr>
        <tr class="border-dark">
            <td colspan="4"></td>
            <td id="totalpricewithtax"><fmt:formatNumber value="${shoppingcart.calculateTotalPriceWithTax()}"
                                                         type="currency"/></td>
        </tr>
        </tbody>
    </table>
    <div class="container centered">
        <a href="/order/guest/checkout" class="btn btn-warning">Guest Checkout</a>
        <a href="/order/checkout" class="btn btn-warning">Checkout</a>
    </div>
</div>

<script src="/resources/js/order/order.js"></script>

<%@include file="/WEB-INF/jsp/template/footer.jsp" %>