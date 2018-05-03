<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 5/3/2018
  Time: 7:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>

<script>
    create = {
        init: function() {
            $('#adminForm').ajaxForm({
                target:'#edit-target',
                url:'/admin/user/admin/edit'
            });
        },
        success: function() {
            $('#edit-modal').modal('hide');
            module.list();
        },
        submit: function() {
            $('#adminForm').submit();
        }
    };
</script>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">Edit <i>${adminForm.username}</i> Admin</h4>
</div>
<div class="modal-body">
    <form:form modelAttribute="CategoryForm" method="post">
        <form:hidden path="id"/>
        <div class="form-group">
            <form:errors path="maincategory" cssStyle="color: red" />
            <label for="maincategory">ManinCategory</label>
            <form:input path="maincategory" class="form-Control" />
        </div>
        <div class="form-group">
            <form:errors path="subcategory" cssStyle="color: red" />
            <label for="subcategory">SubCategory</label>
            <form:input path="subcategory" class="form-Control" />
        </div>
        <div class="form-group">
            <form:errors path="status" cssStyle="color: red" />
            <label for="status">Status</label>
            <form:input path="status" class="form-Control" />
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
        $.sticky('<spring:message code="${message.message}"/>', {autoclose : 5000, position: "top-right", type: "${fn:toLowerCase(message.type)}" });

        <c:if test="${message.type eq 'SUCCESS'}">
        create.success();
        </c:if>
    </script>
</c:if>