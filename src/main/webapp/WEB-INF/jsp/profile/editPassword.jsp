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


<script>
    create = {
        init: function() {
            //console.log('init method')
            /*$('#passwordForm').ajaxForm({
                 target:'#edit-target',
                 url:'/profile/edit/password'
            });*/
        },
        success: function() {
            $('#edit-modal').modal('hide');
            module.list();
        },
        submit: function() {
            $('#passwordForm').submit();
        }
    };
</script>

<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-myaccount-area">
                    <div class="col-md-6" style="float: none; margin-left: 35%; width: 30%">
                        <div class="aa-myaccount-login">
                            <div class="modal-body">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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
                                </form:form>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary" onclick="create.submit()">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script type="text/javascript">
    $(function () {
        create.init();
    });
</script>

<c:if test="${!empty message}">
    <script>
        $.sticky('<spring:message code="${message.message}"/>', {autoclose : 5000, position: "top-right", type: "st-${fn:toLowerCase(message.type)}" });

        <c:if test="${message.type eq 'SUCCESS'}">
        create.success();
        </c:if>
    </script>
</c:if>

<%@include file="/WEB-INF/jsp/template/footer.jsp"%>