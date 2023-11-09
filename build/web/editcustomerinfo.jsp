<%-- 
    Document   : editcustomerinfo
    Created on : Nov 6, 2023, 2:04:19 PM
    Author     : Admin
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">


        <title>Edit profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
            body{
                margin-top:20px;
                background-color:#f2f6fc;
                color:#69707a;
            }
            .img-account-profile {
                height: 10rem;
            }
            .rounded-circle {
                border-radius: 50% !important;
            }
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }
            .card .card-header {
                font-weight: 500;
            }
            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }
            .card-header {
                padding: 1rem 1.35rem;
                margin-bottom: 0;
                background-color: rgba(33, 40, 50, 0.03);
                border-bottom: 1px solid rgba(33, 40, 50, 0.125);
            }
            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }
            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            }
        </style>
    </head>
    <body>

        <div class="container-xl px-4 mt-4">
            <p style="color:blue"><a href="home">Back home</a></p>
            <nav class="nav nav-borders">
                <a class="nav-link active ms-0" href="profile">Profile</a>
                <!--                <a class="nav-link" href="bs5-profile-billing-page.jsp">Billing</a>-->
                <a class="nav-link" href="check">Security</a>
            </nav>
            <hr class="mt-0 mb-4">
            <div class="row">


                <div class="col-xl-12">

                    <div class="card mb-4">
                        <div class="card-header">Account Details</div>
                        <div class="card-body">
                            <form action="profile" method="POST" >

                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">Username (how your name will appear to other users on the site)</label>
                                    <input class="form-control" id="inputUsername" type="text" name="user" placeholder="Enter your username" value="${requestScope.user}" readonly>
                                </div>


                                <div class="mb-3">
                                    <label class="small mb-1" for="inputFirstName">Full Name</label>
                                    <input class="form-control" id="inputFirstName" type="text" placeholder="Enter your first name" value="${requestScope.name}" name="name">
                                </div>



                                <div class="mb-3">
                                    <label class="small mb-1" for="inputLocation">Location</label>
                                    <input class="form-control" id="inputLocation" type="text" name="address" placeholder="Enter your location" value="${requestScope.address}">
                                </div>


                                <div class="row gx-3 mb-3">

                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputPhone">Phone number</label>
                                        <input class="form-control" id="inputPhone" type="text" name="phone" placeholder="Enter your phone number" value="${requestScope.phone}">
                                    </div>
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputEmailAddress">Email address</label>
                                        <input class="form-control" id="inputEmailAddress" type="email" name="email" placeholder="Enter your email address" value="${requestScope.email}">
                                    </div>

                                </div>
                                <input type="number" value="${requestScope.cid}" name="cid" hidden>
                                <button class="btn btn-primary" type="submit">Save changes</button>
                            </form>
                            <c:choose>
                                <c:when test="${requestScope.mess eq 'Update Success!'}">
                                    <p style="color:red"><a href="home">${requestScope.mess}</a></p>
                                    </c:when>
                                    <c:when test="${requestScope.mess eq 'Update Failed, email already exist!'}">
                                    <p style="color:red">${requestScope.mess}</p>
                                </c:when>
                                <c:otherwise>
                                    <p style="color:red">${requestScope.mess}</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>


            </div>
        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">

        </script>
    </body>
</html>
