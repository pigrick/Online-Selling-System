<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 5/5/2018
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<script>
    create = {
        init: function() {
            console.log('init method')
            // $('#editForm').ajaxForm({
            //     target:'#edit-target',
            //     url:'/profile/edit'
            // });
        },
        success: function() {
            $('#edit-modal').modal('hide');
            module.list();
        },
        submit: function() {
            $('#editForm').submit();
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
                            <h4 class="modal-title text-center">Edit Profile</h4>
                            <form:form modelAttribute="editForm"  action="${pageContext.request.contextPath}/profile/edit" method="post">
                                <form:hidden path="id"/>
                                <div class="form-group">
                                    <form:errors path="firstName" cssStyle="color: red" />
                                    <label for="firstName">First Name</label>
                                    <form:input path="firstName" class="form-Control" />
                                </div>
                                <div class="form-group">
                                    <form:errors path="lastName" cssStyle="color: red" />
                                    <label for="lastName">Last Name</label>
                                    <form:input path="lastName" class="form-Control" />
                                </div>
                                <div class="form-group">
                                    <form:errors path="username" cssStyle="color: red" />
                                    <label for="username">username</label>
                                    <form:input path="username" class="form-Control" />
                                </div>
                                <div class="form-group">
                                    <form:errors path="email" cssStyle="color: red" />
                                    <label for="email">email</label>
                                    <form:input path="email" type="email" class="form-Control" />
                                </div>
                            </form:form>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" onclick="create.submit()">Save changes</button>
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





