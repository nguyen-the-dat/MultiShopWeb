<%-- 
    Document   : editcustomerpass
    Created on : Nov 6, 2023, 2:31:05 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">


        <title>bs5 profile security page - Bootdey.com</title>
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

            .btn-danger-soft {
                color: #000;
                background-color: #f1e0e3;
                border-color: #f1e0e3;
            }
        </style>
    </head>
    <body>
        <div class="container-xl px-4 mt-4">
            <p style="color:blue"><a href="home">Back home</a></p>
            <nav class="nav nav-borders">
                <a class="nav-link" href="profile">Profile</a>
                <!--                <a class="nav-link" href="bs5-profile-billing-page.jsp">Billing</a>-->
<!--                <a class="nav-link active ms-0" href="bs5-profile-security-page.jsp">Security</a>-->
                <a class="nav-link active ms-0" href="check">Security</a>
            </nav>
            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-lg-8">

                    <div class="card mb-4">
                        <div class="card-header">Change Password</div>
                        <div class="card-body">
                            <form action="check" method="post">

                                <div class="mb-3">
                                    <label class="small mb-1" for="currentPassword">Current Password</label>
                                    <input class="form-control" id="currentPassword" type="password" placeholder="Enter current password" name="pass">
                                </div>

                                <div class="mb-3">
                                    <label class="small mb-1" for="newPassword">New Password</label>
                                    <input class="form-control" id="newPassword" type="password" placeholder="Enter new password" name="newpass">
                                </div>

                                <div class="mb-3">
                                    <label class="small mb-1" for="confirmPassword">Confirm Password</label>
                                    <input class="form-control" id="confirmPassword" type="password" placeholder="Confirm new password" name="check">
                                </div>
                                <input type="text" name="passc" value="${requestScope.passc}">
                                <input type="text" name="email" value="${requestScope.email}">
                                <button class="btn btn-primary" type="submit">Save</button>

                            </form>
                            <c:choose>
                                <c:when test="${requestScope.mess eq 'Update Success!'}">
                                    <p style="color:red"><a href="home">${requestScope.mess}</a></p>
                                </c:when>
                                <c:otherwise>
                                    <p style="color:red">${requestScope.mess}</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <!--                    <div class="card mb-4">
                                            <div class="card-header">Security Preferences</div>
                                            <div class="card-body">
                    
                                                <h5 class="mb-1">Account Privacy</h5>
                                                <p class="small text-muted">By setting your account to private, your profile information and posts will not be visible to users outside of your user groups.</p>
                                                <form>
                                                    <div class="form-check">
                                                        <input class="form-check-input" id="radioPrivacy1" type="radio" name="radioPrivacy" checked>
                                                        <label class="form-check-label" for="radioPrivacy1">Public (posts are available to all users)</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input class="form-check-input" id="radioPrivacy2" type="radio" name="radioPrivacy">
                                                        <label class="form-check-label" for="radioPrivacy2">Private (posts are available to only users in your groups)</label>
                                                    </div>
                                                </form>
                                                <hr class="my-4">
                    
                                                <h5 class="mb-1">Data Sharing</h5>
                                                <p class="small text-muted">Sharing usage data can help us to improve our products and better serve our users as they navigation through our application. When you agree to share usage data with us, crash reports and usage analytics will be automatically sent to our development team for investigation.</p>
                                                <form>
                                                    <div class="form-check">
                                                        <input class="form-check-input" id="radioUsage1" type="radio" name="radioUsage" checked>
                                                        <label class="form-check-label" for="radioUsage1">Yes, share data and crash reports with app developers</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input class="form-check-input" id="radioUsage2" type="radio" name="radioUsage">
                                                        <label class="form-check-label" for="radioUsage2">No, limit my data sharing with app developers</label>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                    
                                        <div class="card mb-4">
                                            <div class="card-header">Two-Factor Authentication</div>
                                            <div class="card-body">
                                                <p>Add another level of security to your account by enabling two-factor authentication. We will send you a text message to verify your login attempts on unrecognized devices and browsers.</p>
                                                <form>
                                                    <div class="form-check">
                                                        <input class="form-check-input" id="twoFactorOn" type="radio" name="twoFactor" checked>
                                                        <label class="form-check-label" for="twoFactorOn">On</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input class="form-check-input" id="twoFactorOff" type="radio" name="twoFactor">
                                                        <label class="form-check-label" for="twoFactorOff">Off</label>
                                                    </div>
                                                    <div class="mt-3">
                                                        <label class="small mb-1" for="twoFactorSMS">SMS Number</label>
                                                        <input class="form-control" id="twoFactorSMS" type="tel" placeholder="Enter a phone number" value="555-123-4567">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>-->

<!--                    <div class="card mb-4">
                        <div class="card-header">Delete Account</div>
                        <div class="card-body">
                            <p>Deleting your account is a permanent action and cannot be undone. If you are sure you want to delete your account, select the button below.</p>
                            <button class="btn btn-danger-soft text-danger" type="button">I understand, delete my account</button>
                        </div>
                    </div>-->
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">

        </script>
    </body>
</html>