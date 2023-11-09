<%-- 
    Document   : createproduct.jsp
    Created on : Oct 26, 2023, 1:59:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
          <link href="css/adminstyle.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row centered-form"> 

                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4>
                                    Add Product
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

    </body>
</html>
