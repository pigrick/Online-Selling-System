<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<%--<h1>Hello World!</h1>--%>

<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<!-- Start Promo section -->
<section id="aa-promo">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-promo-area">
                    <div class="row">
                        <!-- promo left -->
                        <div class="col-md-5 no-padding">
                            <div class="aa-promo-left">
                                <div class="aa-promo-banner">
                                    <img style="object-fit: cover;" src="${resourcePath}${products.get(0).image}" alt="img">
                                    <div class="aa-prom-content">
                                        <span>${products.get(0).name}</span>
                                        <h4><a href="#">${products.get(0).category.name}</a></h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- promo right -->
                        <div class="col-md-7 no-padding">
                            <div class="aa-promo-right">
                                <c:forEach items="${topProducts}" var="product" begin="4">
                                <div class="aa-single-promo-right">
                                    <div class="aa-promo-banner">
                                        <img style="object-fit: cover;" src="${resourcePath}${product.image}" alt="img">
                                        <div class="aa-prom-content">
                                            <span>${product.name}</span>
                                            <h4><a href="#">${product.category.name}</a></h4>
                                        </div>
                                    </div>
                                </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- / Promo section -->


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
                                <c:set var="counter" value="0" scope="page"/>
                                <c:forEach items="${mainCategories}" var="category">
                                    <c:set var="counter" value="${counter + 1}" scope="page"/>
                                    <c:if test="${counter eq 1}">
                                        <li><a href="#${category}" data-toggle="tab">${category.name}</a></li>
                                    </c:if>
                                    <c:if test="${counter ne 1}">
                                        <li><a href="#${category}" data-toggle="tab">${category.name}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>

                            <!-- start single Product Item-->
                            <div class="tab-pane fade in active">
                                <ul class="aa-product-catg aa-popular-slider">
                                    <!-- start single product item -->
                                    <c:forEach items="${products}" var="product" begin="4">
                                        <%--<c:if test="${category eq product.category.name}">--%>
                                        <li>
                                            <figure>
                                                <a class="aa-product-img"
                                                   href="<c:url value="/product/${product.id}" />">
                                                    <img style="width: 250px; height: 300px"
                                                         src="<c:url value="${resourcePath}${product.image}" />"></a>

                                                <a class="aa-add-card-btn" style="cursor: hand" href="/order/addToCart/${product.id}">
                                                    <span class="fa fa-shopping-cart"></span>Add To Cart
                                                </a>
                                                <figcaption>
                                                    <h4 class="aa-product-title"><a
                                                            href="<c:url value="/product/${product.id}" />">${product.name}</a>
                                                    </h4>
                                                    <span class="aa-product-price">$${product.price}</span><span
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
    </div>
</section>
<!-- / Products section -->


<!-- Client Brand -->
<section id="aa-client-brand">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-client-brand-area">
                    <ul class="aa-client-brand-slider">
                        <li><a href="#"><img src="<c:url value="/static/img/client-brand-ak.png" />" alt="ak img"></a></li>
                        <li><a href="#"><img src="<c:url value="/static/img/client-brand-bart.png" />" alt="bart img"></a></li>
                        <li><a href="#"><img src="<c:url value="/static/img/client-brand-gamet.png" />" alt="gamet img"></a></li>
                        <li><a href="#"><img src="<c:url value="/static/img/client-brand-panasonic.png" />" alt="panasonic img"></a></li>
                        <li><a href="#"><img src="<c:url value="/static/img/client-brand-redible.png" />" alt="redible img"></a></li>



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