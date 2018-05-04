<%@include file="/WEB-INF/include.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <td><fmt:formatNumber value="${orderdetail.price}" type="currency" currencySymbol="$"/></td>
                    <td>${orderdetail.quantity}</td>
                    <td><fmt:formatNumber value="${orderdetail.calculateTotalPrice()}" type="currency"
                                          currencySymbol="$"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="2"></td>
                <td>Tax ( 7% )</td>
                <td id="tax"><fmt:formatNumber value="${shoppingcart.calculateTax()}" type="currency"
                                               currencySymbol="$"/></td>
            </tr>
            <tr class="border-dark">
                <td colspan="3"></td>
                <td id="totalpricewithtax"><fmt:formatNumber value="${shoppingcart.calculateTotalPriceWithTax()}"
                                                             type="currency" currencySymbol="$"/></td>
            </tr>
            </tbody>
        </table>

        <div class="container pull-right">
            <a href="/order/shoppingcart">
                <button class="btn btn-warning">Back to cart</button>
            </a>
        </div>

    </div>
    <div class="container">
        <h1>Shipping Address Detail</h1>

        <div class="container">
            <div class="row">
                <c:forEach var="address" items="${addresses}">
                    <div class="col-sm-2 addborder" id="address${address.id}">
                        <button class="btn btn-danger" type="button" data-toggle="modal" data-target="#removeAddress${address.id}">
                            Remove
                        </button>
                        <br/>
                        <br/>${address.street} <br/>
                            ${address.city}, ${address.state} ${address.zipcode} <br/>
                        Phone: ${address.phoneNumber} <br/><br/>

                        <form:form cssClass="inlineForm" method="post">
                            <input type="hidden" name="addressId" value="${address.id}" />
                            <input type="submit" class="btn btn-primary" value="Deliver to this address"/>
                        </form:form>
                    </div>
                    <div class="modal fade" id="removeAddress${address.id}" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Are you sure you want to remove this address! <br/><br/> ${address.street} <br/>
                                            ${address.city}, ${address.state} ${address.zipcode} <br/>
                                        Phone: ${address.phoneNumber} </h4>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-danger" onclick="removeAddress(${address.id})" data-dismiss="modal">Remove
                                        Address
                                    </button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--<form:form modelAttribute="customerOrderShippingForm" method="post" cssClass="inlineForm">--%>
                        <%--<div class="col-sm-2 addborder">--%>
                                <%--${address.street} <br/>--%>
                                <%--${address.city}, ${address.state} ${address.zipcode} <br/>--%>
                            <%--Phone: ${address.phoneNumber} <br />--%>
                            <%--<form:hidden path="addressId" value="${address.id}" />--%>
                            <%--<form:hidden path="phoneNumber" value="${address.phoneNumber}"/>--%>
                            <%--<form:hidden path="street" value="${address.street}"/>--%>
                            <%--<form:hidden path="city" value="${address.city}"/>--%>
                            <%--<form:hidden path="state" value="${address.state}"/>--%>
                            <%--<form:hidden path="zipcode" value="${address.zipcode}"/>--%>
                            <%--<input class="btn btn-primary" type="submit" value="Deliver to this address"/>--%>
                        <%--</div>--%>
                    <%--</form:form>--%>
                </c:forEach>
            </div>
        </div>
        <br/>
        <h2>Ship to Another Address</h2>
        <form:form modelAttribute="customerOrderShippingForm" method="post">
            <br/>
            <div class="container">


                <label>Phone Number: <form:errors path="phoneNumber" cssClass="alert-danger"/> </label>
                <form:input path="phoneNumber" class="form-control"/>
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


            <br/>

            <input type="submit" class="btn btn-primary" value="Review Order"/>
        </form:form>
    </div>
</div>

<script src="/resources/js/order/order.js"></script>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>