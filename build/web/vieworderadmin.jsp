<%-- 
    Document   : vieworderadmin
    Created on : Nov 6, 2023, 12:24:09 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/css/style.css">
        <link href="https://code.ionicframework.com/1.3.5/css/ionicons.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <%@include file="HeaderAdmin.jsp" %>

            <div class="container">
                <div class="main">

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

                                <c:forEach items="${requestScope.orList}" var="o">
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
                    </div>
                </div>

            </div>

        </div>

        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>
</html>
