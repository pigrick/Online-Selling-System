<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/include.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Online Shop | Home</title>
    <!-- Font awesome -->
    <link href="/resources/css/font-awesome.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <!-- SmartMenus jQuery Bootstrap Addon CSS -->
    <link href="/resources/css/jquery.smartmenus.bootstrap.css" rel="stylesheet">
    <!-- product view slider -->
    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.simpleLens.css">
    <!-- slick slider -->
    <link rel="stylesheet" type="text/css" href="/resources/css/slick.css">
    <!-- price picker slider -->
    <link rel="stylesheet" type="text/css" href="/resources/css/nouislider.css">
    <!-- Theme color -->
    <link id="switcher" href="/resources/css/theme-color/default-theme.css" rel="stylesheet">
    <!-- Top Slider CSS -->
    <link href="/resources/css/sequence-theme.modern-slide-in.css" rel="stylesheet" media="all">
    <!-- Main style sheet -->
    <link href="/resources/css/style.css" rel="stylesheet">
    <!-- Google Font -->
    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>

    <link href="/resources/css/custom.css" rel="stylesheet">
    <!-- jQuery library -->
    <script src="/resources/js/jquery.min.js"></script>
</head>
<body>
<!-- SCROLL TOP BUTTON -->
<a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>
<!-- END SCROLL TOP BUTTON -->

<!-- Start header section -->
<header id="aa-header">
    <!-- start header top  -->
    <div class="aa-header-top">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="aa-header-top-area">
                        <!-- start header top left -->
                        <div class="aa-header-top-left">
                            <!-- start language -->
                            <div class="aa-language">
                                <div class="dropdown">
                                    <a class="btn dropdown-toggle" href="#" type="button" id="dropdownMenu1"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                        <img src="/resources/img/flag/english.jpg" alt="english flag">ENGLISH
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                        <li><a href="#"><img src="/resources/img/flag/english.jpg" alt="">ENGLISH</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!-- / language -->
                            <a class="btn dropdown-toggle" href="#" style="margin-top: -1px">Daily Deals</a>
                        </div>
                        <!-- / header top left -->
                        <div class="aa-header-top-right">
                            <ul class="aa-head-top-nav-right">
                                <sec:authorize access="isAuthenticated()">
                                    <li><a href="#" style="cursor: default">Welcome: ${signedUser.username} </a></li>
                                    <sec:authorize access="hasRole('VENDOR')">
                                        <li><a href="#">${signedUser.companyName}</a></li>
                                    </sec:authorize>
                                    <sec:authorize access="hasRole('ADMIN')">
                                        <li><a href="#">Admin</a></li>
                                    </sec:authorize>
                                    <sec:authorize access="hasRole('CUSTOMER')"></sec:authorize>
                                    <li><a href="#">My Account</a></li>
                                    <li>
                                        <form id="logoutForm" action="/logout">
                                            <a href="#logout" onclick="$('#logoutForm').submit()">Logout</a>
                                            <sec:csrfInput/>
                                        </form>
                                    </li>
                                </sec:authorize>
                                <sec:authorize access="!isAuthenticated()">
                                    <li>
                                        <a href="/login">Login</a>
                                    </li>
                                    <li>
                                        <a href="/signup">signUp</a>
                                    </li>
                                    <li>
                                        <a href="/vendor/signup">vendor signUp</a>
                                    </li>
                                </sec:authorize>
                            </ul>
                        </div>
                        <!-- / header top right -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- / header top  -->



    <!-- start header bottom  -->
    <div class="aa-header-bottom">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="aa-header-bottom-area">
                        <!-- logo  -->
                        <div class="aa-logo">
                            <!-- Text based logo -->
                            <a href="/">
                                <span class="fa fa-shopping-cart"></span>
                                <p>Online<strong>Shop</strong> <span>Your Shopping</span></p>
                            </a>
                        </div>
                        <!-- / logo  -->
                        <!-- cart box -->
                        <div class="aa-cartbox">
                            <a class="aa-cart-link" href="/order/shoppingcart">
                                <span class="fa fa-shopping-basket"></span>
                                <span class="aa-cart-title">CART</span>


                            </a>

                            <div class="aa-cartbox-summary">
                                <ul>
                                    <c:forEach var="orderDetail" items="${shoppingcart.orderDetails}">
                                        <li>
                                            <a class="aa-cartbox-img"><img src="/resources/images/${orderDetail.product.id}/0.png" alt="img"></a>
                                            <div class="aa-cartbox-info">
                                                <h4><a href="#"></a></h4>
                                                <fmt:formatNumber value="${orderDetail.price}" type="currency"
                                                                  currencySymbol="$"/> x ${orderDetail.quantity}
                                            </div>
                                            <a class="aa-remove-product" href="#"
                                               ng-click="removeFromCart(item.cartItemId,'${_csrf.parameterName}=${_csrf.token}')">
                                                <span class="fa fa-times"></span>
                                            </a>
                                        </li>

                                    </c:forEach>
                                    <li>
                                        <span class="aa-cartbox-total-title">Total</span>
                                        <span class="aa-cartbox-total-price"><fmt:formatNumber value="${shoppingcart.calculateTotalPrice()}" type="currency"
                                                                                               currencySymbol="$"/></span>
                                    </li>
                                </ul>
                                <a class="aa-cartbox-checkout aa-primary-btn" href="/order/shoppingcart">Shopping Cart</a>
                            </div>

                        </div>
                        <!-- / cart box -->
                        <!-- search box -->
                        <form>
                            <div class="aa-search-box">
                                <input type="text" ng-model="keyword" placeholder="Product Name">
                                <button type="submit" style="width: 50px; height: 40px" class="fa fa-search"></button>
                            </div>
                        </form>
                        <!-- / search box -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- / header bottom  -->
</header>
<!-- / header section -->

<!-- menu -->
<section id="menu">
    <div class="container">
        <div class="menu-area">
            <!-- Navbar -->
            <div class="navbar navbar-default" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="navbar-collapse collapse">
                    <!-- Left nav -->
                    <ul class="nav navbar-nav">
                        <li><a href="/">Home</a></li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="/admin/product/all">Product</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_VENDOR')">
                            <li><a href="/vendor/product/all">Product</a></li>
                        </sec:authorize>
                        <li><a href="#">Men <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Trousers</a></li>
                                <li><a href="#">T-Shirt</a></li>
                                <li><a href="#">Shoes</a></li>
                                <li><a href="#">And more.. <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Sandals</a></li>
                                        <li><a href="#">Loafers</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li><a href="#">Women <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Jean</a></li>
                                <li><a href="#">Pant</a></li>
                                <li><a href="#">Shoes</a></li>
                                <li><a href="#">And more.. <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Sandals</a></li>
                                        <li><a href="#">Loafers</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>

                        <li><a href="/contact">Contact</a></li>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_VENDOR')">
                            <li><a href="/report/reportFilter">Report</a></li>
                        </sec:authorize>
                        <li><a href="#">Products<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="/product/all">All</a></li>
                                <sec:authorize access="hasRole('ROLE_VENDOR')">
                                    <li><a href="/vendor/product/all">Own products</a></li>
                                    <li><a href="/vendor/product/save">Upload</a></li>
                                </sec:authorize>
                            </ul>
                        </li>
                        <sec:authorize access="hasRole('CUSTOMER')">
                            <li><a href="/order/customer/all/1">Orders</a></li>

                        </sec:authorize>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>
    </div>
</section>


<!-- / menu -->