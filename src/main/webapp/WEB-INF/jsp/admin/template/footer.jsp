<%@include file="/WEB-INF/include.jsp"%>
<!-- footer -->
<footer id="aa-footer">
    <!-- footer bottom -->
    <div class="aa-footer-top">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="aa-footer-top-area">
                        <div class="row">
                            <div class="col-md-3 col-sm-6">
                                <div class="aa-footer-widget">
                                    <h3>Main Menu</h3>
                                    <ul class="aa-footer-nav">
                                        <li><a href="#">Home</a></li>
                                        <li><a href="#">Our Services</a></li>
                                        <li><a href="#">Our Products</a></li>
                                        <li><a href="#">About Us</a></li>
                                        <li><a href="#">Contact Us</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="aa-footer-widget">
                                    <div class="aa-footer-widget">
                                        <h3>Knowledge Base</h3>
                                        <ul class="aa-footer-nav">
                                            <li><a href="#">Delivery</a></li>
                                            <li><a href="#">Services</a></li>
                                            <li><a href="#">Discount</a></li>
                                            <li><a href="#">Special Offer</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="aa-footer-widget">
                                    <div class="aa-footer-widget">
                                        <h3>Useful Links</h3>
                                        <ul class="aa-footer-nav">
                                            <li><a href="#">Site Map</a></li>
                                            <li><a href="#">Search</a></li>
                                            <li><a href="#">Advanced Search</a></li>
                                            <li><a href="#">Suppliers</a></li>
                                            <li><a href="#">FAQ</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="aa-footer-widget">
                                    <div class="aa-footer-widget">
                                        <h3>Contact Us</h3>
                                        <address>
                                            <p> xx xxxxx xx, IA xxxxx, USA</p>
                                            <p><span class="fa fa-phone"></span>+1 xxx-xxx-xxxx</p>
                                            <p><span class="fa fa-envelope"></span>xxxxx@gmail.com</p>
                                        </address>
                                        <div class="aa-footer-social">
                                            <a href="#"><span class="fa fa-facebook"></span></a>
                                            <a href="#"><span class="fa fa-twitter"></span></a>
                                            <a href="#"><span class="fa fa-google-plus"></span></a>
                                            <a href="#"><span class="fa fa-youtube"></span></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- footer-bottom -->
    <div class="aa-footer-bottom">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="aa-footer-bottom-area">
                        <p><a href="<spring:url value="/version"/>">Version</a></p>
                        <div class="aa-footer-payment">
                            <span class="fa fa-cc-mastercard"></span>
                            <span class="fa fa-cc-visa"></span>
                            <span class="fa fa-paypal"></span>
                            <span class="fa fa-cc-discover"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- / footer -->

<!-- Login Modal -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4>Login or Register</h4>

                <form class="aa-login-form" action="#" method="post">


                    <div class="form-group">
                        <label for="email">Email<span>*</span></label>
                        <input id="email" type="text" name="email" placeholder="Email address" class="form-control" />
                    </div>

                    <div class="form-group">
                        <label for="password">Password<span>*</span></label>
                        <input id="password" type="password" name="password" placeholder="Password" class="form-control">
                    </div>

                    <button type="submit" class="aa-browse-btn">Login</button>


                    <label class="rememberme" for="rememberme"><input type="checkbox" id="rememberme"> Remember me </label>
                    <p class="aa-lost-password"><a href="#">Lost your password?</a></p>
                    <div class="aa-register-now">
                        Don't have an account?<a href="#" />">Register now!</a>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!-- Angular JS -->
<<script src="/resources/js/angular.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
<!-- SmartMenus jQuery plugin -->
<script type="text/javascript" src="/resources/js/jquery.smartmenus.js"></script>

<!-- SmartMenus jQuery Bootstrap Addon -->

<script type="text/javascript" src="/resources/js/jquery.smartmenus.bootstrap.js" ></script>

<!-- To Slider JS -->

<script src="/resources/js/sequence.js" ></script>

<script src="/resources/js/sequence-theme.modern-slide-in.js"  ></script>

<!-- prduct view slider -->
<script  type="text/javascript" src="/resources/js/jquery.simpleGallery.js"   ></script>

<script  type="text/javascript" src="/js/jquery.simpleLens.js"  ></script>

<!-- slick slider -->
<script  type="text/javascript" src="/resources/js/slick.js"  ></script>


<!-- Price picker slider -->
<script  type="text/javascript" src="/resources/js/nouislider.min.js" ></script>

<!-- Custom js -->

<script src="/resources/js/custom.js" ></script>

<script  src="/resources/js/controller.js" ></script>

</body>
</html>