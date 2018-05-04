<%--
  Created by IntelliJ IDEA.
  User: chanpiseth
  Date: 5/3/2018
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>

<!-- product category -->
<section id="aa-product-details">
    <div class="container">

        <c:set var="role" scope="page" value="${param.role}" />
        <c:set var="url" scope="page" value="/pd" />
        <c:if test="${role='admin'}">
            <c:set var="url" scope="page" value="/admin" />
        </c:if>

        <div class="row">
            <div class="col-md-12">
                <div class="aa-product-details-area">
                    <div class="aa-product-details-content">
                        <div class="row">
                            <!-- Modal view slider -->
                            <div class="col-md-5 col-sm-5 col-xs-12">
                                <div class="aa-product-view-slider">
                                    <div id="demo-1" class="simpleLens-gallery-container">
                                        <div class="simpleLens-container">
                                            <div class="simpleLens-big-image-container">
                                                <a data-lens-image="/static/images/${product.id}/0.png" class="simpleLens-lens-image">
                                                    <img src="/static/images/${product.id}/0.png" class="simpleLens-big-image" >
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Modal view content -->
                            <div class="col-md-7 col-sm-7 col-xs-12">
                                <div class="aa-product-view-content">
                                    <h3>${product.name}</h3>
                                    <div class="aa-price-block">
                                        <span class="aa-product-view-price">$${product.price}</span>
                                        <p class="aa-product-avilability">Avilability: <span id="stock">${product.status}</span></p>
                                    </div>
                                    <p>${product.description}</p>
                                    <div class="aa-prod-quantity">
                                        <input type="number" style="width: 50px;cursor: default" id="quantity"
                                               min="1" max="${product.quantity}" value="1">
                                    </div>
                                    <div class="aa-prod-view-bottom">
                                        <a class="aa-add-to-cart-btn" style="cursor: hand" href="/order/addToCart/${product.id}">Add To Cart</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

            </div>
        </div>
    </div>
</section>

<br>
<br>
<!-- / product category -->


<%@include file="/WEB-INF/jsp/template/footer.jsp"%>

