<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>

<%@include file="/WEB-INF/jsp/template/header.jsp" %>

<link rel="stylesheet" type="text/css" href="/resources/css/order/order.css">


<div class="container tpy">

    <h2 align="center">Shopping cart</h2>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col">Product Name</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Update Quantity</th>
            <th scope="col">Total Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="orderDetail" items="${shoppingcart.orderDetails}">
            <tr id="${orderDetail.product.id}">
                <input type="hidden" id="productid" name="productid" value="${orderDetail.product.id}"/>
                <td><a href="/product/${orderDetail.product.id}"><img src="/resources/images/${orderDetail.product.id}/0.png" alt="img" height="100" width="100"></a></td>
                <td><a href="/product/${orderDetail.product.id}">${orderDetail.product.name}</a></td>
                <td id="price"><fmt:formatNumber value="${orderDetail.price}" type="currency" currencySymbol="$" /></td>
                <td id="quantity">${orderDetail.quantity}</td>
                <td>
                    <input type="number" id="cartquantity2" onblur="updateQuantity(${orderDetail.product.id})" min="0" value="${orderDetail.quantity}"/>
                    <%--<button type="submit" class="btn btn-warning" onclick="updateQuantity(${orderdetail.product.id})">--%>
                        <%--Update--%>
                    <%--</button>--%>
                </td>
                <td id="totalprice"><fmt:formatNumber value="${orderDetail.calculateTotalPrice()}"
                                                      type="currency" currencySymbol="$" /></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="6"></td>
        </tr>
        <tr>
            <td colspan="4"></td>
            <td>Tax ( 7% )</td>
            <td id="tax"><fmt:formatNumber value="${shoppingcart.calculateTax()}" type="currency" currencySymbol="$" /></td>
        </tr>
        <tr class="border-dark">
            <td colspan="5"></td>
            <td id="totalpricewithtax"><fmt:formatNumber value="${shoppingcart.calculateTotalPriceWithTax()}"
                                                         type="currency" currencySymbol="$" /></td>
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