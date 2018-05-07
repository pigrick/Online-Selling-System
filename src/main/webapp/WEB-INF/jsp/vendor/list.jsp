
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Category</th>
        <th>Description</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${productList}" var="row">
        <tr <c:if test="${row.status ne 'ENABLED'}">class="danger" </c:if>>
            <td><i>${row.name}</i></td>
            <td><i>${row.quantity}</i></td>
            <td>${row.price}</td>
            <td>${row.category.getName()}</td>
            <td>${row.description}</td>
            <td>
                    <%--<a href="#" type="button" onclick="module.delete('${row.id}')">
                        <i class="glyphicon glyphicon-info-sign"></i>
                    </a>--%>
                <a href="#edit" onclick="module.edit('${row.id}')">
                    <i class="glyphicon glyphicon-pencil"></i>
                </a>

                    <a href="#delete" type="button" onclick="module.delete('${row.id}')">
                        <i class="glyphicon glyphicon-remove"></i>
                    </a>

            </td>td
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@include file="/WEB-INF/jsp/template/pagination.jsp"%>
