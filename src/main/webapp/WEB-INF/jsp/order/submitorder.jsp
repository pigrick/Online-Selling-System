<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/template/header.jsp" %>

<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/order/order.css">

<div class="container">
<form:form id="submit-payment" modelAttribute="paymentForm" method="post" action="checkout/submit">
    <div class="creditCardForm">
        <div class="heading">
            <h1>Confirm Purchase</h1>
        </div>
        <div class="payment">

            <div class="form-group owner">
                <label for="owner">Card Holder</label>
                <form:input path="cardHolderName" class="form-control" id="owner"/>
            </div>
            <div class="form-group CVV">
                <label for="cvv">CVV</label>
                <form:input path="cvv" class="form-control" id="cvv"/>
            </div>
            <div class="form-group" id="card-number-field">
                <label for="cardNumber">Card Number</label>
                <form:input path="cardNumber" class="form-control" id="cardNumber"/>
            </div>
            <div class="form-group" id="expiration-date">
                <label>Expiration Date</label>
                <select name="month">
                    <option value="01">January</option>
                    <option value="02">February</option>
                    <option value="03">March</option>
                    <option value="04">April</option>
                    <option value="05">May</option>
                    <option value="06">June</option>
                    <option value="07">July</option>
                    <option value="08">August</option>
                    <option value="09">September</option>
                    <option value="10">October</option>
                    <option value="11">November</option>
                    <option value="12">December</option>
                </select>
                <select name="year">
                    <option value="18"> 2018</option>
                    <option value="19"> 2019</option>
                    <option value="20"> 2020</option>
                    <option value="21"> 2021</option>
                    <option value="20"> 2022</option>
                    <option value="21"> 2023</option>
                    <option value="21"> 2024</option>
                </select>
            </div>
            <div class="form-group" id="credit_cards">
                <img src="/resources/paymentdetail/images/visa.jpg" id="visa">
                <img src="/resources/paymentdetail/images/mastercard.jpg" id="mastercard">
            </div>
            <div class="form-group" id="pay-now">
                <button type="submit" class="btn btn-default" id="confirm-purchase">Confirm</button>
            </div>

        </div>
    </div>
    </div>


    <input type="submit" class="btn btn-default" value="Review Order"/>
</form:form>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/paymentdetail/js/jquery.payform.min.js" charset="utf-8"></script>
<script src="/resources/paymentdetail/js/script.js"></script>


<%@include file="/WEB-INF/jsp/template/footer.jsp" %>

