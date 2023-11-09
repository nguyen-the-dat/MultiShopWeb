<%-- 
    Document   : detailproductadmin.jsp
    Created on : Oct 25, 2023, 2:00:13 AM
    Author     : Admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/style.css" rel="stylesheet">
<!--        <link href="css/adminstyle.css" rel="stylesheet">-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    </head>
    <body>
        <%@include file="HeaderAdmin.jsp" %>
        <!-- Shop Product Start -->
        <div class="container">
               <div class="container-fluid">
            <div class="row px-xl-5">
                <!-- Shop Sidebar Start -->
                <div class="col-lg-3 col-md-4">
                    <!-- Price Start -->

                    <!-- Price End -->

                    <!-- Color Start -->


                    <!-- Size Start -->

                    <!-- Size End -->
                </div>
                <!-- Shop Sidebar End -->


                <!-- Shop Product Start -->
                <div class="col-lg-9 col-md-8">
                    <div class="row pb-3">
                        <div class="col-12 pb-1">
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <div>
                                      <button type="button" class="btn btn-outline-primary">Add a new Product</button>
                                </div>

                                <div class="ml-2">

                                    <div class="btn-group ml-2">

                                        <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown" id="selectedCategory">${requestScope.searchName}</button>
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a href="productmanage?cateName=All" class="dropdown-item">All</a>
                                            <c:forEach items="${requestScope.allCateList}" var="cate">
                                                <a href="productmanage?cateName=${cate.name}" class="dropdown-item">${cate.name}</a>
                                            </c:forEach>
                                        </div>
                                    </div>


                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">Sorting</button>
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="#">Latest</a>
                                            <a class="dropdown-item" href="#">Popularity</a>
                                            <a class="dropdown-item" href="#">Best Rating</a>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <!--bat dau dua ra san pham tai day-->



                        <c:set var="p" value="${requestScope.proBC}"></c:set>



                        <c:forEach items="${p}" var="pr" begin='${page.begin}' end="${page.end-1}">
                            <div class="col-lg-4 col-md-6 col-sm-6 pb-1">
                                <div class="product-item bg-light mb-4">
                                    <div class="product-img position-relative overflow-hidden">
                                        <img class="img-fluid w-100" src="${pr.image}" alt="">

                                    </div>
                                    <div class="text-center py-4">
                                        <a class="h6 text-decoration-none text-truncate" href="">${pr.name}</a>
                                        <div class="d-flex align-items-center justify-content-center mt-2">
                                            <h5>$${pr.price}</h5><h6 class="text-muted ml-2"><del>$(${pr.price})</del></h6>
                                        </div>
                                        <div class="d-flex align-items-center justify-content-center mb-1">
                                            <a href="home" style="text-decoration: none"> <button type="button" class="btn btn-warning">Update</button></a>
                                            <a href="home" style="text-decoration: none">  <button type="button" class="btn btn-success">Delete</button></a>




                                        </div>
                                    </div>
                                </div>
                            </div>


                        </c:forEach>
                        <div class="col-12">
                            <nav>
                                <div class="form-paging">
                                    <form action="productmanage" method="post">

                                        <input type="text" name="index" value="${page.index}" hidden/>
                                        <input type="text" name="totalPage" value="${page.totalPage}" hidden/>
                                        <input type="text" name="cateName" value="${requestScope.searchName}" hidden/>
                                        <select name="nrpp_f" onchange="this.form.submit()">
                                            <c:forEach var="i" items="${nrppArr}" begin="0" end="${fn:length(nrppArr)-1}" step="1">
                                                <option value="${i}" ${nrpp_t==(i)?"selected":""}>${i}</option>
                                            </c:forEach>
                                        </select>            

                                        <c:if test="${page.index != 0}">
                                            <input type="submit" name="btnHome" value="Home">
                                            <input type="submit" name="btnPre" value="Pre">
                                        </c:if> 
                                        <c:forEach var="i" begin ='${page.pageStart}' end="${page.pageEnd}">
                                            <input type="submit" name="btn${i}" value="${i+1}" style="${index==(i)?"background: black; color:white;":""}">
                                        </c:forEach>

                                        <c:if test="${page.index != page.totalPage-1 }">
                                            <input type="submit" name="btnNext" value="Next">
                                            <input type="submit" name="btnEnd" value="End">
                                        </c:if>

                                    </form>
                                </div>

                            </nav>
                        </div>
                    </div>
                </div>
                <!-- Shop Product End -->
            </div>
        </div>

        </div>     
        <!-- Shop Product End -->


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>
</html>
