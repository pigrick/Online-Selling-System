<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Vendor Report</title>

</head>
<body>
<%@include file="/WEB-INF/jsp/template/header.jsp" %>
<%@include file="/WEB-INF/jsp/template/accountSider.jsp" %>
<form:form>
    <h1 class="header">WELCOME TO VENDOR REPORT</h1>
    <table>
        <tr>
            <td>From:<input type="date" name="from" min="2017-01-10"/></td>
            <td>To:<input type="date" name="to" max="2050-01-10"/></td>
        </tr>
        <tr>
            <td>Category:</td>
            <td><select name="category">
                <c:forEach var="c" items="${categories}">
                    <option>${c}</option>
                </c:forEach>
            </select>
            </td>
        </tr>
        <%--<tr><<a href="/report/vendorExport?from=${}?to=${}?category=${}" class="btn btn-default">Export</a> </tr>--%>
    </table>
</form:form>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>
</body>
</html>