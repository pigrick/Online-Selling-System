<%--
  Created by IntelliJ IDEA.
  User: Erdenebayar
  Date: 5/2/2018
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>

<script>
    create = {
        init: function() {
            $('#vendorForm').ajaxForm({
                target:'#edit-target',
                url:'/vendor/edit'
            });
        },
        success: function() {
            $('#edit-modal').modal('hide');
            module.list();
        },
        submit: function() {
            $('#productForm').submit();
        }
    };
</script>

<div class="modal-heade r">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">Edit <i>${vendorForm.username}</i> Vendor</h4>
</div>
<div class="modal-body">
    <form:form modelAttribute="productForm" method="post">
        <form:hidden path="id"/>
        <div class="form-group">
            <form:errors path="name" cssStyle="color: red" />
            <label for="name">Name</label>
            <form:input path="companyName" class="form-Control" />
        </div>
        <div class="form-group">
            <form:errors path="quantity" cssStyle="color: red" />
            <label for="quantity">Quantity</label>
            <form:input path="quantity" class="form-Control" />
        </div>
        <div class="form-group">
            <form:errors path="price" cssStyle="color: red" />
            <label for="price">Price</label>
            <form:input path="price" type="email" class="form-Control" />
        </div>
        <div class="form-group">
            <form:errors path="description" cssStyle="color: red" />
            <label for="description">Description</label>
            <form:input path="description" type="email" class="form-Control" />
        </div>
    </form:form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary" onclick="module.submit()">Save changes</button>
</div>

<script type="text/javascript">
    $(function () {
        module.init();
    });
</script>

<c:if test="${!empty message}">
    <script>
        $.sticky('<spring:message code="${message.message}"/>', {autoclose : 5000, position: "top-right", type: "${fn:toLowerCase(message.type)}" });

        <c:if test="${message.type eq 'SUCCESS'}">
        module.success();
        </c:if>
    </script>
</c:if>
