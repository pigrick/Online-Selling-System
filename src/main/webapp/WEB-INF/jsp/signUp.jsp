<%@include file="/WEB-INF/include.jsp"%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-myaccount-area">
                    <div class="col-md-6" style="float: none; margin-left: 35%; width: 30%">
                        <div class="aa-myaccount-login">

                            <h4>Register</h4>

                            <c:if test="${!empty message}">
                                <c:choose>
                                    <c:when test="${message.type eq 'SUCCESS'}">
                                        <div class="panel-success">${messaage.message}</div>
                                    </c:when>
                                    <c:when test="${message.type eq 'FAILED'}">
                                        <div class="panel-danger">${messaage.message}</div>
                                    </c:when>
                                </c:choose>
                            </c:if>
                            <%--<c:url value="/signup" var="signupProcessingUrl"/>--%>
                            <%--<form:form action="${signupProcessingUrl}" method="post">--%>
                                <form:form modelAttribute="moduleForm" action="${pageContext.request.contextPath}/signup" method="post">

                                <div class="form-group">
                                    <label for="firstName">First Name<span style="color: red">*</span></label>
                                    <form:errors path="firstName" cssStyle="color: red" />
                                    <form:input path="firstName" id="firstName" class="form-Control" />
                                </div>

                                <div class="form-group">
                                    <label for="lastName">Last Name<span style="color: red">*</span></label>
                                    <form:errors path="lastName" cssStyle="color: red" />
                                    <form:input path="lastName" id="lastName" class="form-Control" />
                                </div>

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
                                    <form:password path="password" class="form-Control" />
                                </div>
                                <div class="form-group">
                                    <label for="rePassword">Password again<span style="color: red">*</span></label>
                                    <form:password path="rePassword" class="form-Control" />
                                </div>

                                <script>
                                    $("#rePassword").on("change paste keyup", function() {
                                        var password = $("#password").val();
                                        var confirmPassword = $("#rePassword").val();

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