<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/WEB-INF/include.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Report</title>

</head>
<body>
<%@include file="/WEB-INF/jsp/template/header.jsp" %>
<%@include file="/WEB-INF/jsp/template/accountSider.jsp" %>
<form:form>
    <h1 class="header">WELCOME TO ADMIN REPORT</h1>
    <table>
        <tr>
            <td>From:<input type="date" name="from" min="2017-01-10"/></td>
            <td>To:<input type="date" name="to" max="2050-01-10"/></td>
        </tr>
        <tr>
            <td>Vendor:</td>
            <td><select name="vendor">
                <c:forEach var="v" items="${vendors}">
                    <option>${v}</option>
                </c:forEach>
            </select>
            </td>
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
        <%--<tr><<a href="/report/adminExport?from=${}?to=${}?vendor=${}?category=${}" class="btn btn-default">Export</a> </tr>--%>
    </table>
</form:form>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>
</body>
</html>