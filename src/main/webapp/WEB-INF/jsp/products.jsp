<%--
  Created by ChanPiseth
  User: chanpiseth
  Date: 4/24/2018
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<section id="aa-catg-head-banner">
    <img src="<spring:url value="/resources/img/fashion/fashion-header-bg-8.jpg" />" alt="fashion img">
    <div class="aa-catg-head-banner-area">
        <div class="container">
            <div class="aa-catg-head-banner-content">
                <h2>${category}</h2>
            </div>
        </div>
    </div>
</section>



<%@include file="/WEB-INF/jsp/template/footer.jsp"%>
