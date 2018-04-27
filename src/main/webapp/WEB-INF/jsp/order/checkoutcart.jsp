<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/template/header.jsp" %>

<div class="container">
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
    </table>


    <form:form modelAttribute="customerOrderShippingForm" method="post">
        <br/>
        <div class="container">
            <h1>Shipping Address Detail</h1>
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


        <input type="submit" class="btn btn-default" value="Review Order"/>
    </form:form>
</div>


<%@include file="/WEB-INF/jsp/template/footer.jsp" %>