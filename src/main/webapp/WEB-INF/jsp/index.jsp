<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<%--<h1>Hello World!</h1>--%>

<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<!-- Products section -->

<section id="aa-product">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="aa-product-area">
                        <div class="aa-product-inner">
                            <!-- start prduct navigation -->
                            <ul class="nav nav-tabs aa-products-tab">
                                <c:set var="counter" value="0" scope="page" />
                                <c:forEach items="${mainCategories}" var="category">
                                    <c:set var="counter" value="${counter + 1}" scope="page"/>
                                    <c:if test="${counter eq 1}">
                                        <li><a href="#${category}" data-toggle="tab">${category}</a></li>
                                    </c:if>
                                    <c:if test="${counter ne 1}">
                                        <li><a href="#${category}" data-toggle="tab">${category}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>

                        <!-- start single Product Item-->

                        <ul>
                            <!-- start single product item -->
                            <c:forEach items="${products}" var="product">
                                <%--<c:if test="${category eq product.category.name}">--%>
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="<c:url value="/{productId}=${product.id}" />">
                                                <img style="width: 250px; height: 300px" src="<c:url value="/resources/images/${product.id}/0.png" />" ></a>

                                            <a class="aa-add-card-btn" style="cursor: hand" >
                                                <span class="fa fa-shopping-cart"></span>Add To Cart
                                            </a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="<c:url value="/{productId}=${product.id}" />">${product.name}</a></h4>
                                                <span class="aa-product-price">$${product.price}</span><span class="aa-product-price"><del>$999</del></span>
                                            </figcaption>
                                        </figure>

                                    </li>
                                <%--</c:if>--%>
                            </c:forEach>
                        </ul>
                        <!-- / start single Product Item -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- / Products section -->

<!-- banner section -->
<section id="aa-banner">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="aa-banner-area">
                        <a href="#"><img src="<c:url value="/resources/img/banner-footer1-1170x170.jpg" />" alt="fashion banner img"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Client Brand -->
<section id="aa-client-brand">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-client-brand-area">
                    <ul class="aa-client-brand-slider">
                        <li><a href="#"><img src="<c:url value="/resources/img/client-brand-ak.png" />" alt="ak img"></a></li>
                        <li><a href="#"><img src="<c:url value="/resources/img/client-brand-bart.png" />" alt="bart img"></a></li>
                        <li><a href="#"><img src="<c:url value="/resources/img/client-brand-gamet.png" />" alt="gamet img"></a></li>
                        <li><a href="#"><img src="<c:url value="/resources/img/client-brand-panasonic.png" />" alt="panasonic img"></a></li>
                        <li><a href="#"><img src="<c:url value="/resources/img/client-brand-redible.png" />" alt="redible img"></a></li>



                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- / Client Brand -->


<%@include file="/WEB-INF/jsp/template/footer.jsp"%>


<%--<form action="/logout" method="post">
    <button type="submit">Logout</button>
</form>--%>
</body>
</html>