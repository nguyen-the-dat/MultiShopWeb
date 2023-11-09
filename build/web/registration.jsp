<%-- 
    Document   : registration
    Created on : Nov 1, 2023, 1:28:51 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign Up Form by MultiShop</title>

        <!-- Font Icon -->
        <link rel="stylesheet"
              href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="css/style4.css">
    </head>
    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
                        <form method="post" action="signup" class="register-form"
                              id="register-form">
                            <div class="form-group">
                                <label for="name"><i
                                        class="zmdi zmdi-account material-icons-name"></i></label> <input
                                    type="text" name="name" id="name" placeholder="Your Name" value="${requestScope.name}" />
                            </div>
                            <div class="form-group">
                                <label for="user"><i
                                        class="zmdi zmdi-account material-icons-name"></i></label> <input
                                    type="text" name="user" id="user" placeholder="User Name" value="${requestScope.user}" required/>
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label> <input required
                                                                                                  type="email" name="email" id="email" placeholder="Your Email" value="${requestScope.email}" />
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label> <input required
                                                                                                type="password" name="pass" id="pass" placeholder="Password" value="${requestScope.pass}" />
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" required
                                       placeholder="Repeat your password" value="${requestScope.re_pass}"/>
                            </div>
                            <p style="color:red">${requestScope.error}</p>
                            <div class="form-group">
                                <label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="text" name="contact" id="contact"
                                       placeholder="Contact no" value="${requestScope.contact}"/>
                            </div>
                            <div class="form-group">
                                <label for="address"><i class="zmdi zmdi-pin"></i></label>
                                <textarea name="address" id="address" placeholder="Your Address" style="width: 280px; height: 100px;" rows="4">${requestScope.address}</textarea>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term"
                                       class="agree-term" required/> <label for="agree-term"
                                       class="label-agree-term"><span><span></span></span>I
                                    agree all statements in <a href="#" class="term-service">Terms
                                        of service</a></label>
                            </div>
                            <input type="hidden" name="role" value="1" />
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup"
                                       class="form-submit" value="Register" />
                            </div>

                        </form>
                    </div>
                    <div class="signup-image">
                        <figure>
                            <img src="images/signup-image.jpg" alt="sing up image">
                        </figure>
                        <a href="login.jsp" class="signup-image-link">${requestScope.mess}</a>
                        <!--                            <a href="login.jsp" class="signup-image-link">I am already
                                                        member</a>-->
                    </div>
                    <!--                            <form method="" action="" class="register-form"
                                                      id="register-form">
                                                    <div class="form-group">
                                                        <label for="name"><i
                                                                class="zmdi zmdi-account material-icons-name"></i></label> <input
                                                            type="text" name="name" id="name" placeholder="Your Name" />
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="email"><i class="zmdi zmdi-email"></i></label> <input
                                                            type="email" name="email" id="email" placeholder="Your Email" />
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
                                                            type="password" name="pass" id="pass" placeholder="Password" />
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                                        <input type="password" name="re_pass" id="re_pass"
                                                               placeholder="Repeat your password" />
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
                                                        <input type="text" name="contact" id="contact"
                                                               placeholder="Contact no" />
                                                    </div>
                                                    <div class="form-group">
                                                        <input type="checkbox" name="agree-term" id="agree-term"
                                                               class="agree-term" /> <label for="agree-term"
                                                               class="label-agree-term"><span><span></span></span>I
                                                            agree all statements in <a href="#" class="term-service">Terms
                                                                of service</a></label>
                                                    </div>
                                                    <div class="form-group form-button">
                                                        <input type="submit" name="signup" id="signup"
                                                               class="form-submit" value="Register" />
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="signup-image">
                                                <figure>
                                                    <img src="images/signup-image.jpg" alt="sing up image">
                                                </figure>
                                                <a href="login.jsp" class="signup-image-link">I am already
                                                    member</a>
                                            </div>-->
                </div>
            </div>
        </section>


    </div>
    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>