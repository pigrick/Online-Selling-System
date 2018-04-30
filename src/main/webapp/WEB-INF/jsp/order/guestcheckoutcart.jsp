<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>

<%@include file="/WEB-INF/jsp/template/header.jsp" %>
<link rel="stylesheet" type="text/css" href="/resources/css/order/order.css">
<div class="container tpy">
    <h1 align="center">Order</h1>
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
            <tr>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="2"></td>
                <td>Tax ( 7% )</td>
                <td><fmt:formatNumber value="${shoppingcart.calculateTax()}" type="currency"/></td>
            </tr>
            <tr class="border-dark">
                <td colspan="3"></td>
                <td><fmt:formatNumber value="${shoppingcart.calculateTotalPriceWithTax()}" type="currency"/></td>
            </tr>
            </tbody>
        </table>

        <div class="container pull-right">
            <a href="/order/shoppingcart">
                <button class="btn btn-warning">Back to cart</button>
            </a>
        </div>

    </div>
    <form:form modelAttribute="guestOrderShippingForm" method="post">
        <br/>
        <div class="container">
            <h1>Shipping Address Detail</h1>
            <label>First Name: <form:errors path="firstName" cssClass="alert-danger"/></label>
            <form:input path="firstName" class="form-control"/>
            <label>Last Name: <form:errors path="lastName" cssClass="alert-danger"/></label>
            <form:input path="lastName" class="form-control"/>
            <label>Email: <form:errors path="email" cssClass="alert-danger"/></label>
            <form:input path="email" class="form-control"/>
            <label>Street: <form:errors path="street" cssClass="alert-danger"/></label>
            <form:input path="street" class="form-control"/>
            <label>City: <form:errors path="city" cssClass="alert-danger"/></label>
            <form:input path="city" class="form-control"/>

            <label>State: <form:errors path="state" cssClass="alert-danger"/></label>
            <form:select path="state" class="form-control">
                <option value="AL">Alabama</option>
                <option value="AK">Alaska</option>
                <option value="AZ">Arizona</option>
                <option value="AR">Arkansas</option>
                <option value="CA">California</option>
                <option value="CO">Colorado</option>
                <option value="CT">Connecticut</option>
                <option value="DE">Delaware</option>
                <option value="DC">District Of Columbia</option>
                <option value="FL">Florida</option>
                <option value="GA">Georgia</option>
                <option value="HI">Hawaii</option>
                <option value="ID">Idaho</option>
                <option value="IL">Illinois</option>
                <option value="IN">Indiana</option>
                <option value="IA">Iowa</option>
                <option value="KS">Kansas</option>
                <option value="KY">Kentucky</option>
                <option value="LA">Louisiana</option>
                <option value="ME">Maine</option>
                <option value="MD">Maryland</option>
                <option value="MA">Massachusetts</option>
                <option value="MI">Michigan</option>
                <option value="MN">Minnesota</option>
                <option value="MS">Mississippi</option>
                <option value="MO">Missouri</option>
                <option value="MT">Montana</option>
                <option value="NE">Nebraska</option>
                <option value="NV">Nevada</option>
                <option value="NH">New Hampshire</option>
                <option value="NJ">New Jersey</option>
                <option value="NM">New Mexico</option>
                <option value="NY">New York</option>
                <option value="NC">North Carolina</option>
                <option value="ND">North Dakota</option>
                <option value="OH">Ohio</option>
                <option value="OK">Oklahoma</option>
                <option value="OR">Oregon</option>
                <option value="PA">Pennsylvania</option>
                <option value="RI">Rhode Island</option>
                <option value="SC">South Carolina</option>
                <option value="SD">South Dakota</option>
                <option value="TN">Tennessee</option>
                <option value="TX">Texas</option>
                <option value="UT">Utah</option>
                <option value="VT">Vermont</option>
                <option value="VA">Virginia</option>
                <option value="WA">Washington</option>
                <option value="WV">West Virginia</option>
                <option value="WI">Wisconsin</option>
                <option value="WY">Wyoming</option>

            </form:select>

            <label>Zipcode: <form:errors path="zipcode" cssClass="alert-danger"/></label>
            <br/>
            <form:input path="zipcode" class="form-control"/>
        </div>
        <br />

        <input type="submit" class="btn btn-primary" value="Review Order"/>
    </form:form>
</div>


<%@include file="/WEB-INF/jsp/template/footer.jsp" %>