<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 4/27/2018
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-myaccount-area">
                    <div class="col-md-6" style="float: none; margin-left: 35%; width: 30%">
                        <div class="aa-myaccount-login">

                            <h4>Vendor Register</h4>


                            <%--<c:url value="/signup" var="signupProcessingUrl"/>--%>
                            <%--<form:form action="${signupProcessingUrl}" method="post">--%>
                            <form:form modelAttribute="moduleForm" action="${pageContext.request.contextPath}/vendor/signup" method="get">

                                <div class="form-group">
                                    <label for="username">User Name<span style="color: red">*</span></label>
                                    <form:errors path="username" cssStyle="color: red" />
                                    <form:input path="username" id="username" class="form-Control" />
                                </div>


                                <div class="form-group">
                                    <label for="email">Email<span style="color: red">*</span></label>
                                    <form:errors path="email" cssStyle="color: red" />
                                    <form:input type="email" path="email" id="email" class="form-Control" />
                                </div>



                                <div class="form-group">
                                    <label for="password">Password<span style="color: red">*</span></label>
                                    <form:errors path="password" cssStyle="color: red" />
                                    <form:input path="password" id="password" type="password" class="form-Control" />
                                </div>

                                <div class="form-group">
                                    <label for="passwordConfirm">Password again<span style="color: red">*</span></label>
                                    <input type="password" id="passwordConfirm" class="form-Control">
                                </div>

                                <script>
                                    $("#passwordConfirm").on("change paste keyup", function() {
                                        var password = $("#password").val();
                                        var confirmPassword = $("#passwordConfirm").val();

                                        if (password != confirmPassword){
                                            $("#register-submit").prop('disabled', true);
                                            $("#error").html("Passwords do not match!");
                                        }else{
                                            $("#register-submit").prop('disabled', false);
                                            $("#error").html("");
                                        }
                                    });
                                    function jsfunction()
                                    {
                                        //you code
                                        return false;
                                    }
                                </script>

                                <input type="submit" value="submit" id="register-submit" class="btn btn-default" disabled>
                                <a href="<c:url value="/" />" class="btn btn-default">Cancel</a>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>




<%@include file="/WEB-INF/jsp/template/footer.jsp"%>
