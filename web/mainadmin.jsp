<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Responsive Admin Dashboard | Korsat X Parmaga</title>
        <!-- ======= Styles ====== -->
        <link rel="stylesheet" href="assets/css/style.css">
        <link href="https://code.ionicframework.com/1.3.5/css/ionicons.min.css" rel="stylesheet">

    </head>

    <body>
        <!-- =============== Navigation ================ -->
        <div class="container">

            <%@include file="HeaderAdmin.jsp" %>

            <!-- ========================= Main ==================== -->
            <div class="main">
               
                <!-- ======================= Cards ================== -->
                <div class="cardBox">
                    <div class="card">
                        <a href="admincontroller?action=category" style="text-decoration: none;">
                            <div>
                                <div class="numbers">${requestScope.cateList.size()}</div>
                                <div class="cardName">Category</div>
                            </div>
                        </a>
                        <div class="iconBx">
                            <ion-icon name="eye-outline"></ion-icon>
                        </div>
                    </div>

                    <div class="card">
                        <a href="admincontroller?action=product" style="text-decoration: none;">
                            <div>
                                <div class="numbers">${requestScope.proList.size()}</div>
                                <div class="cardName">Product</div>
                            </div>
                        </a>

                        <div class="iconBx">
                            <ion-icon name="shirt"></ion-icon>
                        </div>
                    </div>

                    <div class="card">
                        <a href="admincontroller?action=order" style="text-decoration: none;"> 
                            <div>
                                <div class="numbers">${requestScope.orList.size()}</div>
                                <div class="cardName">Number of order</div>
                            </div>


                        </a>
                        <div class="iconBx">
                            <ion-icon name="cart-outline"></ion-icon>
                        </div>
                    </div>

                    <div class="card">
                        <a href="admincontroller?action=orderdetail" style="text-decoration: none;">
                            <div>
                                <c:set var="total" value="0"></c:set>
                                <c:forEach items="${requestScope.orDetailList}" var="p">
                                    <c:set var="total" value="${total + p.price}"></c:set>
                                </c:forEach>

                                <div class="numbers">$${Float.valueOf(total)}</div>
                                <div class="cardName">Earning</div>
                            </div>
                        </a>
                        <div class="iconBx">
                            <ion-icon name="cash-outline"></ion-icon>
                        </div>
                    </div>
                </div>

                <!-- ================ Order Details List ================= -->
                <div class="details">
                    <div class="recentOrders">
                        <div class="cardHeader">
                            <h2>Recent Orders</h2>
                            <a href="#" class="btn">View All</a>
                        </div>

                        <table>
                            <thead>
                                <tr>
                                    <td>Order ID</td>
                                    <td>Customer Name</td>
                                    <td>Price</td>
                                    <td>Order Date</td>
                                    <td>View</td>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach items="${requestScope.orList}" var="o" begin="1" end="5">
                                    <tr>

                                        <td>${o.id}</td>
                                        <td>
                                            <c:forEach items="${requestScope.cusList}" var="cu">
                                                <c:if test="${cu.id==o.cusid}">
                                                    ${cu.fullName}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>${o.totalMoney}</td>
                                        <td>${o.date}</td>
                                        <td ><a href="adminorderdetail?id=${o.id}" style="color:black">View Detail</a></td>
                                    </tr>
                                </c:forEach>





                            </tbody>
                        </table>
                    </div>

                    <!-- ================= New Customers ================ -->
                    <div class="recentCustomers">
                        <div class="cardHeader">
                            <h2>Recent Customers</h2>
                        </div>

                        <table>

                            <c:forEach items="${requestScope.cusList}" var="ce">
                                <tr>

                                    <td>
                                        <h4>${ce.fullName} <br> <span>${ce.address}</span></h4>
                                    </td>
                                </tr> 
                            </c:forEach>


                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- =========== Scripts =========  -->
        <script src="assets/js/main.js"></script>

        <!-- ====== ionicons ======= -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>

</html>