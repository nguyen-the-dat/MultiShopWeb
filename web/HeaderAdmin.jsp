<%-- 
    Document   : HeaderAdmin
    Created on : Oct 24, 2023, 8:45:45 PM
    Author     : Admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Responsive Admin Dashboard | Korsat X Parmaga</title>
        <!-- ======= Styles ====== -->
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha384-FhC5WS6Gn2p5Iczur6l1PzLz0AUZmhpCZ8xwV0y6DZz5T3JbECwjlelmL3kgXsPk" crossorigin="anonymous" />

    </head>
    <body>
        <div class="navigation">
            <ul>
                <li>
                    <a href="adminload">
                        <span class="icon">
                            <!--                            <ion-icon name="logo-apple"></ion-icon>-->
                          <ion-icon name="heart"></ion-icon>

                        </span>
                        <span class="title">Multi Shop</span>
                    </a>
                </li>

                <li>
                    <a href="adminload">
                        <span class="icon">
                            <ion-icon name="home-outline"></ion-icon>
<!--<ion-icon name="heart"></ion-icon>-->

                        </span>
                        <span class="title">Dashboard</span>
                    </a>
                </li>

                <li>
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="cart-outline"></ion-icon>
                        </span>
                        <span class="title">Orders</span>
                    </a>
                </li>
                
                
                <li>
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="people-outline"></ion-icon>
                        </span>
                        <span class="title">Customers</span>
                    </a>
                </li>

                

                <!--                <li>
                                    <a href="#">
                                        <span class="icon">
                                            <ion-icon name="help-outline"></ion-icon>
                                        </span>
                                        <span class="title">Help</span>
                                    </a>
                                </li>-->

                <!--                <li>
                                    <a href="#">
                                        <span class="icon">
                                            <ion-icon name="settings-outline"></ion-icon>
                                        </span>
                                        <span class="title">Settings</span>
                                    </a>
                                </li>-->

                <li>
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="lock-closed-outline"></ion-icon>
                        </span>
                        <span class="title">Profile</span>
                    </a>
                </li>

                <li>
                    <a href="logout">
                        <span class="icon">
                            <ion-icon name="log-out-outline"></ion-icon>
                        </span>
                        <span class="title">Sign Out</span>
                    </a>
                </li>
            </ul>
        </div>

        <script src="assets/js/main.js"></script>

        <!-- ====== ionicons ======= -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>
</html>
