<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 5/5/2018
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<%@include file="/WEB-INF/include.jsp"%>

<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-myaccount-area">
                    <div class="col-md-6" style="float: none; margin-left: 35%; width: 30%">
                        <div class="aa-myaccount-login">
                            <div class="modal-body">
                                <div class="modal-header">
                                    <h4 class="modal-title">Edit Password</h4>
                                </div>
                                <div class="alert-danger"><c:out value="${badcard}"/></div>
                                <form:form modelAttribute="passwordForm"  action="${pageContext.request.contextPath}/profile/edit/password" method="post">
                                    <div class="form-group">
                                        <form:errors path="password" cssStyle="color: red" />
                                        <label for="password">Password</label>
                                        <form:input path="password" type="email" class="form-Control" />
                                    </div>

                                    <div class="form-group">
                                        <form:errors path="rePassword" cssStyle="color: red" />
                                        <label for="rePassword">re-Password</label>
                                        <form:input path="rePassword" type="email" class="form-Control" />
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-primary" onclick="create.submit()">Save changes</button>
                                        <a href="<c:url value="/" />" class="btn btn-default">Close</a>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jsp/template/footer.jsp"%>

<c:if test="${!empty message}">
    <script>
        $.sticky('<spring:message code="${message.message}"/>', {autoclose : 5000, position: "top-right", type: "st-${fn:toLowerCase(message.type)}" });

        <c:if test="${message.type eq 'SUCCESS'}">
        window.location = "/";
        </c:if>
    </script>
</c:if>