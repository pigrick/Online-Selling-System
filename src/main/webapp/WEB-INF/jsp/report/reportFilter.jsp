<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <%@include file="/WEB-INF/jsp/template/header.jsp" %>
</head>
<body>
<title>Report</title>
<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-myaccount-area">
                    <div class="col-md-10" style="float: none; margin-left: 20%; width: 50%; height: 45%">
                        <h4 style="color:orangered" align="center">REPORT</h4>
                        <form:form modelAttribute="reportForm" method="post">
                            <div class="form-group" style="border-width: 20px ">
                                <label class="col-md-3">From:</label>
                                <input class="col-md-7" type="date" path="from" min="2017-01-10"/>
                            </div>
                            <br/><br/>
                            <div class="form-group" style="border-width: 20px ">
                                <label class="col-md-3 ">To:</label>
                                <input class="col-md-7" type="date" path="to" max="2050-01-10"/>
                            </div>
                            <br/>
                            <!-- check sec ROLE-->
                            <%--<c:if test="${sessionScope.getAttribute("role").toString())=='Admin'}">--%>
                            <%----%>
                        <%--</c:if>--%>
                            <div class="form-group" >
                                <label class="col-md-3">Vendor:</label>
                                <select class="col-md-7" path="vendor">
                                    <option>ALL</option>
                                    <c:forEach var="v" items="${vendors}">
                                        <option>${v}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br/>
                            <!-- check sec ROLE-->

                            <div class="form-group">
                                <label class="col-md-3">Category:</label>
                                <select class="col-md-7" path="category">
                                    <option>ALL</option>
                                    <c:forEach var="c" items="${categories}">
                                        <option>${c}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br/><br/>
                            <div class="col-md-10" align="center">
                                <input style="color: orangered" type="submit" value="Export"/>
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