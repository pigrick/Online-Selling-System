<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-myaccount-area">
                    <div class="col-md-6" style="float: none; margin-left: 35%; width: 30%">
                        <div class="aa-myaccount-login">

                            <h4>Edit card</h4>
                            <div class="alert-danger"><c:out value="${badcard}"/></div>
                            <form:form modelAttribute="editCard" action="/profile/card/edit" method="post" >

                                <%--CARD INFO--%>
                                <form:hidden path="cardType" id="card-type"/>
                                <div class="form-group owner">
                                    <label for="cardHolderName">Card Holder<span style="color: red">*</span></label>
                                    <form:errors path="cardHolderName" cssStyle="color: red" />
                                    <form:input path="cardHolderName" class="form-control" id="cardHolderName"/>
                                </div>
                                <div class="form-group CVV">
                                    <label for="cvv">CVV<span style="color: red">*</span></label>
                                    <form:errors path="cvv" cssStyle="color: red" />
                                    <form:input path="cvv" class="form-control" id="cvv"/>
                                </div>
                                <div class="form-group" id="card-number-field">
                                    <label for="cardNumber">Card Number<span style="color: red">*</span></label>
                                    <form:errors path="cardNumber" cssStyle="color: red" />
                                    <form:input path="cardNumber" class="form-control" id="cardNumber" maxlength="16" />
                                </div>
                                <div class="form-group" id="expiration-date">
                                    <label>Expiration Date<span style="color: red">*</span></label>
                                    <select name="month" value="cvv">
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
                                        <option value="2018"> 2018</option>
                                        <option value="2019"> 2019</option>
                                        <option value="2020"> 2020</option>
                                        <option value="2021"> 2021</option>
                                        <option value="2020"> 2022</option>
                                        <option value="2021"> 2023</option>
                                        <option value="2021"> 2024</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Zipcode<span style="color: red">*</span></label>
                                    <form:errors path="zipcode" cssStyle="color: red" />
                                    <form:input path="zipcode" class="form-control" />
                                </div>
                                <div class="form-group" id="credit_cards">
                                    <img src="/static/paymentdetail/images/visa.jpg" id="visa">
                                    <img src="/static/paymentdetail/images/mastercard.jpg" id="mastercard" >
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary" >Submit</button>
                                    <a href="<c:url value="/" />" class="btn btn-default">Cancel</a>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jsp/template/footer.jsp"%>

<script>
    $(function() {
        var cardNumberField = $('#card-number-field');
        var cardNumber = $('#cardNumber');
        var CVV = $("#cvv");
        var mastercard = $("#mastercard");
        var visa = $("#visa");

        cardNumber.payform('formatCardNumber');
        CVV.payform('formatCardCVC');

        cardNumber.keyup(function() {
            visa.removeClass('transparent');
            mastercard.removeClass('transparent');

            if ($.payform.validateCardNumber(cardNumber.val()) == false) {
                cardNumberField.addClass('has-error');
            } else {
                cardNumberField.removeClass('has-error');
                cardNumberField.addClass('has-success');
            }

            if ($.payform.parseCardType(cardNumber.val()) == 'visa') {
                mastercard.addClass('transparent');
                $("#card-type").val("Visa");
            } else if ($.payform.parseCardType(cardNumber.val()) == 'mastercard') {
                visa.addClass('transparent');
                $("#card-type").val("Mastercard");
            }
        });
    });
</script>

<c:if test="${!empty message}">
    <script>
        $.sticky('<spring:message code="${message.message}"/>', {autoclose : 5000, position: "top-right", type: "st-${fn:toLowerCase(message.type)}" });

        <c:if test="${message.type eq 'SUCCESS'}">
        window.location = "/";
        </c:if>
    </script>
</c:if>