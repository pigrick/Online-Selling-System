<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 4/25/2018
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <div class="page-header">
        <h1>${title}</h1>
    </div>

    <form:form modelAttribute="category" action="${pageContext.request.contextPath}/admin/category/save" method="post">
        <div class="form-group">
            <label class="control-label col-md-4">MainCategory</label>
            <div class="col-md-8">
                <sf:select path="id" items="${categories}" itemLabel="name" itemValue="id" class="form-control"/>

                <div class="text-right">
                    <br/>
                    <sf:hidden path="parentCategory"/>

                </div>
            </div>

        </div>

        <label class="control-label col-md-4">SubCategory</label>
        <div class="col-md-8">
            <sf:select path="name" items="${categories}" itemLabel="name" itemValue="id" class="form-control"/>
            <div class="text-right">
                <br/>

                <input type="submit" value="Save" class="btn btn-default">
                <a href="<c:url value="/admin/category/m" />" class="btn btn-default">Cancel</a>
            </div>
        </div>
    </form:form>
</div>

<%@include file="/WEB-INF/jsp/template/footer.jsp"%>