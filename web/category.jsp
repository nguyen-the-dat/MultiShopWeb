<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
    <head>
        <title>Table 06</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="css/adminstyle.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style3.css">

        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Do you want to delete id = " + id)) {
                    window.location = "delete?id=" + id;
                    window.location = "categorymanage?action=delete&cId=" + id;
                }
            }
        </script>


    </head>
    <div>
        <jsp:include page="HeaderAdmin.jsp" />
    </div>


    <body class="large-screen">

        <div class="wrap">
            <div >
                <a style="text-decoration: none; font-size:20px" href="categorymanage?action=add">Add new Category</a>
            </div>
            <br/>  <br/>  
            <div class="table-wrapper">

                <table class="table-responsive card-list-table">
                    <thead>
                        <tr>
                            <th>Category ID</th>
                            <th>Category Name</th>
                            <th>Image</th>
                            <th>Update</th>
                            <th>Delete</th>
                            <th>Product</th>
                        </tr>
                    </thead>
                    <tbody>



                        <c:forEach items="${requestScope.cateList}" var="ca">
                            <tr>
                                <td >${ca.id}</td>
                                <td>${ca.name}</td>
                                <td>
                                    <img src="${ca.image}" width="100px" height="100px"/>
                                </td>
                                <td >
                                    <a href="categorymanage?action=update&cId=${ca.id}">Update</a>
                                </td>
                                <td ><a href="#" onclick="doDelete(${ca.id})">Delete</a></td>
                                <td><a href="productmanage?cateName=${ca.name}">View product</a></td>
                            </tr>
                        </c:forEach>





                    </tbody>
                </table>

            </div>
            <br/><br/><!-- comment -->
            <c:if test="${requestScope.cateUpdate!=null}">
                <h2>Update a category</h2>
                <form action="categorymanage?action=update" method="post">

                    <label for="id">Enter ID</label>
                    <input type="number" id="id" name="id" value="${cateUpdate.id}" readonly >
                    <br/><br/>
                    <label for="name">Enter Name</label>
                    <input type="text" id="name" name="name" value="${cateUpdate.name}">
                    <br/><br/>
                    <label for="image">Enter Image</label>


                    <input type="text" id="image" name="image">


                    <input type="hidden" name="demo" class="demo" value="">

                    <input type="submit" value="Update"/> 

                </form>



            </c:if>

            <div class="container">
                <c:if test="${requestScope.add!=null}">
                    <!--                <h2>Create a category</h2> <br/> <br/> 
                                    <form action="categorymanage?action=add" method="post">
                    
                                        <label for="name">Enter Name</label>
                                        <input type="text" id="name" name="name">
                                        <br/><br/>
                                        <label for="image">Enter Image</label>  
                                        <input type="text" id="image" name="image"><br/><br/>
                                        <input type="submit" value="Add"/> 
                    
                                    </form>-->

                    <div class="container">
                        <div class="row centered-form"> 

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4>
                                                Add Category
                                            </h4>
                                        </div>
                                        <div class="card-body">
                                            <div class="overplay-form">
                                                <form action="" method="post" enctype="multipart/form-data">
                                                <div class="row">

                                                    <div class="col-md-6">
                                                        <label for="">Name</label>
                                                        <input type="text" class="form-control" name="name" placeholder="Enter category name">
                                                    </div>

                                                    <div class="col-md-6">
                                                        <label for="">Slug</label>
                                                        <input type="text" class="form-control" name="slug" placeholder="Enter slug">
                                                    </div>

                                                    <div class="col-md-12">
                                                        <label for="">Description</label>
                                                        <textarea row="3" name="description" placeholder="Enter description"
                                                                  class="form-control"></textarea>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <label for="">Upload Image</label>
                                                        <input type="file" name="image" class="form-control">
                                                    </div>
                                                    <div class="col-md-12">
                                                        <label for="">Meta Title</label>
                                                        <input type="text" name="meta_title" placeholder="Enter meta title"
                                                               class="form-control">
                                                    </div>
                                                    <div class="col-md-12">
                                                        <label for="">Meta Description</label>
                                                        <textarea row="3" name="meta_description" placeholder="Enter meta description"></textarea>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <label for="">Meta Keywords</label>
                                                        <textarea row="3" name="meta_keywords" placeholder="Enter meta keywords"></textarea>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label for="">Status</label>
                                                        <input type="checkbox" name="status">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label for="">Popular</label>
                                                        <input type="checkbox" name="popular">
                                                    </div>
                                                    <div class="col-md-12">
                                                        <button type="submit" class="btn btn-primary" name="add_category_btn">
                                                            Save
                                                        </button>

                                                    </div>

                                                </div>
                                            </form>

                                            </div>

                                        </div>

                                    </div>
                                </div>
                            </div></div>
                    </div>




                </c:if> 
            </div>


        </div>

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <br/>  <br/>  <br/>  <br/>  <br/>   
    </body>

</html>

