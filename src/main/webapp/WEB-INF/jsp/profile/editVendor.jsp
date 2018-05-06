<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 5/5/2018
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<%@include file="/WEB-INF/include.jsp"%>

<script>
    create = {
         init: function() {
             console.log('Hello');

        //     $('#editForm').ajaxForm({
        //         target:'#edit-target',
        //         url:'/admin/user/vendor/edit'
        //     });
         },
        success: function() {

            $('#edit-modal').modal('hide');
            module.list();
        },
        submit: function() {
            console.log('Hello');
            $('#editForm').submit();
        }
    };
</script>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">Edit <i>${editForm.username}</i> Vendor</h4>
</div>
<div class="modal-body">
    <form:form modelAttribute="editForm" action="${pageContext.request.contextPath}/profile/vendor/edit" method="post">
        <form:hidden path="id"/>
        <div class="form-group">
            <form:errors path="companyName" cssStyle="color: red" />
            <label for="companyName">Company Name</label>
            <form:input path="companyName" class="form-Control" />
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

