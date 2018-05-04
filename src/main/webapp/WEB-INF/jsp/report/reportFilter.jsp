<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@include file="/WEB-INF/jsp/template/header.jsp" %>

<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-myaccount-area" style="padding: 50px">
                    <div class="col-md-10" style="float: none; margin-left: 20%; width: 50%; height: 50%">
                        <h4 style="color:orangered" align="center">REPORT</h4>
                        <form:form method="post" modelAttribute="reportFilterForm" action="/report/reportFilter">
                            <div class="form-group" style="border-width: 20px ">
                                <label class="col-md-3">From:</label>
                                <%@ page import="java.text.*, java.util.Date" %>
                                <% DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                    String formattedDate = df.format(new Date()); %>
                                <form:input class="col-md-7" path="begin_Date" type="date" min="2017-01-10"
                                            value="<%= formattedDate %>"/>
                            </div>
                            <br/><br/>
                            <div class="form-group" style="border-width: 20px ">
                                <label class="col-md-3">To:</label>
                                <form:input class="col-md-7" path="end_Date" type="date" max="2050-01-10"
                                            value="<%= formattedDate %>"/>
                            </div>
                            <br/>
                            <!-- check sec ROLE-->
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <div class="form-group">
                                    <label class="col-md-3">Vendor:</label>
                                    <label for="allVendor" class="col-md-7">
                                        <input type="checkbox" value="0" id="allVendor" checked
                                               onclick="myFunctionReport('#allVendor','#vendor')"/> All</label><br/>
                                    <label class="col-md-3"></label>
                                    <form:select id="vendor" class="col-md-7" path="lstVendor_Id"
                                                 multiple="multiple" disabled="true">
                                        <%--<form:option id="vendorAll" selected="selected" value="0">ALL</form:option>--%>
                                        <c:forEach var="v" items="${vendors}">
                                            <form:option id="otherVendors"
                                                         value="${v.id}">${v.companyName}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <br/><br/><br/><br/>
                            </sec:authorize>

                            <div class="form-group">
                                <label class="col-md-3">Category:</label>
                                <label class="col-md-7"><input type="checkbox" value="0"
                                                               id="allCategory" checked
                                                               onclick="myFunctionReport('#allCategory','#category')"/> All</label><br/>
                                <label class="col-md-3"></label>
                                <form:select id="category" class="col-md-7" path="lstCategory_Id"
                                             multiple="multiple" size="5" disabled="true">
                                    <%--<form:option selected="selected" value="0">ALL</form:option>--%>
                                    <c:forEach var="c" items="${categories}">
                                        <form:option value="${c.id}">${c.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <br/><br/> <br/><br/><br/>
                            <div class="col-md-10" align="center">
                                <input id="btnExport" style="color: orangered" type="submit" value="Export"
                                       formtarget="_blank"/>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="/static/js/report/report.js"/>
<%@include file="/WEB-INF/jsp/template/footer.jsp" %>
