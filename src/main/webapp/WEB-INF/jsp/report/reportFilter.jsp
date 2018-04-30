<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <%@include file="/WEB-INF/jsp/template/header.jsp" %>
    <script>
        // document.getElementById('#from').valueAsDate = new Date();
        // document.getElementById('myDate').val(new Date().toDateInputValue();
        // document.getElementById('to').valueAsDate = new Date();
        document.getElementById("from").defaultValue = today;
    </script>
</head>
<body>
<title>Report</title>
<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-myaccount-area" style="padding: 50px">
                    <div class="col-md-10" style="float: none; margin-left: 20%; width: 50%; height: 50%">
                        <h4 style="color:orangered" align="center">REPORT</h4>
                        <form:form modelAttribute="reportFilterForm" method="post">
                            <div class="form-group" style="border-width: 20px ">
                                <label class="col-md-3">From:</label>
                                <form:input id="from" class="col-md-7" path="begin_Date" type="date" min="2017-01-10"/>
                            </div>
                            <br/><br/>
                            <div class="form-group" style="border-width: 20px ">
                                <label class="col-md-3 ">To:</label>
                                <form:input id="to" class="col-md-7"  path="end_Date" type="date" max="2050-01-10"/>
                            </div>
                            <br/>
                            <!-- check sec ROLE-->
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <div class="form-group">
                                    <label class="col-md-3">Vendor:</label>
                                    <select id="lstVendor_Id" class="col-md-7" path="lstVendor_Id" multiple="multiple"
                                            size="5">
                                        <option selected="selected" value="0">ALL</option>
                                        <c:forEach var="v" items="${vendors}">
                                            <option value="${v.id}">${v.companyName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <br/><br/><br/><br/><br/>
                            </sec:authorize>
                            <div class="form-group">
                                <label class="col-md-3">Category:</label>
                                <select id="lstCategory_Id" class="col-md-7" path="lstCategory_Id" multiple="multiple"
                                        size="5">
                                    <option selected="selected" value="0">ALL</option>
                                    <c:forEach var="c" items="${categories}">
                                        <option value="c.id">${c.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br/><br/> <br/><br/><br/>
                            <div class="col-md-10" align="center">
                                <input id="btnExport" style="color: orangered" type="submit" value="Export" formtarget="_blank"/>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>

</body>
</html>