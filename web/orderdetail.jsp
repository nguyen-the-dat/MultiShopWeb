<%-- 
    Document   : orderdetail
    Created on : Nov 5, 2023, 2:43:09 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/adminstyle.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />

    </head>
    <body>
        <!--        <div class="py-3 bg-primary">
                    <div class="container">
                        <h6 class="text-white">
                            <a href="index.php" class="text-white">
                                Home /
                            </a>
                            <a href="my-orders.php" class="text-white">
                                My Orders
                            </a>
                            <a href="#" class="text-white">
                                View Order
                            </a>
                        </h6>
                    </div>
                </div>-->


        <%@include file="HeaderAdmin.jsp" %>

        <div class="main">
            <div class="py-5">
                <div class="container">
                    <div class="">
                        <div class="row">
                            <div class="col-md-12 mb-3">
                                <div class="card">
                                    <!--                                <div class="card-header bg-primary">
                                                                        <span class="text-white">
                                                                            View order
                                                                        </span>
                                                                        <a href="#" class="btn btn-warning float-end"> <i class="fa fa-reply"></i> Back</a>
                                                                    </div>-->
                                    <div class="card-header bg-primary d-flex justify-content-between align-items-center">
                                        <span class="text-white">
                                            View order detail
                                        </span>
                                        <a href="#" class="btn btn-warning"> <i class="fa fa-reply"></i> Back</a>
                                    </div>


                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <h4>Delivery Details</h4>
                                                <hr>
                                                <div class="row">
                                                    <div class="col-md-12 mb-3">
                                                        <label class="fw-bold">
                                                            Name
                                                        </label>
                                                        <div class="border p-1">
                                                            ${requestScope.customer.fullName}
                                                        </div>

                                                    </div>

                                                    <div class="col-md-12 mb-3">
                                                        <label class="fw-bold">
                                                            Email
                                                        </label>
                                                        <div class="border p-1">
                                                            ${requestScope.customer.email}
                                                        </div>

                                                    </div>

                                                    <div class="col-md-12 mb-3">
                                                        <label class="fw-bold">
                                                            Phone
                                                        </label>
                                                        <div class="border p-1">
                                                            ${requestScope.customer.phoneNumber}
                                                        </div>

                                                    </div>

                                                    <div class="col-md-12 mb-3">
                                                        <label class="fw-bold">
                                                            Account user
                                                        </label>
                                                        <div class="border p-1">
                                                            ${requestScope.customer.username}
                                                        </div>

                                                    </div>

                                                    <div class="col-md-12 mb-3">
                                                        <label class="fw-bold">
                                                            Address
                                                        </label>
                                                        <div class="border p-1">
                                                            ${requestScope.customer.address}
                                                        </div>

                                                    </div>

                                                    <!--                                                    <div class="col-md-12 mb-3">
                                                                                                            <label class="fw-bold">
                                                                                                                Pin code
                                                                                                            </label>
                                                                                                            <div class="border p-1">
                                                                                                                 pin code 
                                                                                                            </div>
                                                    
                                                                                                        </div>-->
                                                </div>





                                            </div>

                                            <div class="col-md-6">
                                                <h4>Order Details</h4>
                                                <hr>
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>Product</th>
                                                            <th>Price</th>
                                                            <th>Quantity</th>

                                                        </tr>
                                                    </thead>

                                                    <tbody>

                                                        <c:forEach items="${requestScope.listOdetail}" var="p">
                                                            <c:set var="pe" value="0"></c:set>

                                                                <tr>
                                                                    <td class="align-middle">
                                                                        <img src="${p.product.image}" width="50px" height="50px">
                                                                    <span> ${p.product.name}</span>
                                                                </td>
                                                                <td class="align-middle">
                                                                    $${p.product.price}
                                                                </td>

                                                                <td class="align-middle">
                                                                    ${p.quantity}
                                                                </td>

                                                            </tr>
                                                            <c:set var="pe" value="${pe+p.product.price*p.quantity}"></c:set>
                                                        </c:forEach>

                                                    </tbody>
                                                </table>
                                                <hr>
                                                <h5 class="d-flex justify-content-between">
                                                    <span>Total price:</span>
                                                    <span class="fw-bold">${pe}</span>
                                                </h5>

                                                <hr>
                                                <label class="fw-bold">Payment Mode</label>
                                                <div class="border p-1 mb-3">

                                                    Shipping
                                                </div>
                                                <label class="fw-bold">Status</label>
                                                <div class="border p-1 mb-3">


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
        </div>

      
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
