<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Stock&Stock</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
                object-fit: cover;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="table-wrapper">
                <!--                <div class="table-title">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <h2>Manage <b>Product</b></h2>
                                        </div>
                                        <div class="col-sm-6">
                                            <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                                            <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						
                                        </div>
                                    </div>
                                </div>-->
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <a href="loadProductAdmin?action=loadProduct" style="text-decoration: none; color: inherit;"><h2>Manage <b>Product</b></h2></a>
                        </div>

                        <div class="col-sm-6">
                            <div class="row">
                                <div class="col-sm-6">
                                    <form action="search" method="post">
                                        <input type="hidden" name="action" value="searchProduct">
                                        <div class="search-box" style="display: flex; justify-content: center; align-items: center;">
                                            <input type="text" class="form-control" placeholder="Search..." style="margin-right: 5px;" name="keyword" value="${keyword}">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="material-icons">&#xE8B6;</i>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                                <!--<a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>-->
                                <div class="form-group">
                                    <!--<label>Category</label>-->
                                    <select name="category" class="form-select" aria-label="Default select example" style="color: #000; height: 33px;">
                                        <c:forEach items="${listCategories}" var="o">
                                            <option value="${o.categoryID}">${o.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>
                                <span class="custom-checkbox">
                                    <input type="checkbox" id="selectAll">
                                    <label for="selectAll"></label>
                                </span>
                            </th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Brand</th>
                            <th>Color</th>
                            <th>Size</th>
                            <th>Price</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listShoes}" var="o">
                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                        <label for="checkbox1"></label>
                                    </span>
                                </td>
                                <td>${o.productID}</td>
                                <td>${o.productName}</td>
                                <td>
                                    <img src="${o.images[0].imageURL}">
                                </td>
                                <td>${o.category.categoryName}</td>
                                <td>${o.productColor}</td>
                                <td>${o.productSizeString}</td>
                                <td>$${o.productPrice}</td>
                                <td>
                                    <a href="loadEditProduct?action=loadEditProduct&pid=${o.productID}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    <a href="deleteProduct?action=deleteProduct&pid=${o.productID}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix">
                    <div class="hint-text">Showing <b>${index*5 < productCount ? index*5 : productCount}</b> out of <b>${productCount}</b> entries</div>
                    <ul class="pagination">
                        <%--<c:if test="${not empty search}">
                            <c:if test="${index > 1}">
                                <li class="page-item"><a href="search?action=searchProduct&pageNumber=${index-1}">Previous</a></li>
                                </c:if>
                                <c:forEach begin="1" end="${endPage}" var="i">
                                <li class="page-item ${index==i?"active":""}"><a href="search?action=searchProduct&pageNumber=${i}" class="page-link">${i}</a></li>
                                </c:forEach>
                                <c:if test="${index < endPage}">
                                <li class="page-item"><a href="search?action=searchProduct&pageNumber=${index+1}" class="page-link">Next</a></li>
                                </c:if>
                            </c:if>

                        <c:if test="${index > 1}">
                            <li class="page-item"><a href="loadProductAdmin?action=loadProduct&pageNumber=${index-1}">Previous</a></li>
                            </c:if>
                            <c:forEach begin="1" end="${endPage}" var="i">
                            <li class="page-item ${index==i?"active":""}"><a href="loadProductAdmin?action=loadProduct&pageNumber=${i}" class="page-link">${i}</a></li>
                            </c:forEach>
                            <c:if test="${index < endPage}">
                            <li class="page-item"><a href="loadProductAdmin?action=loadProduct&pageNumber=${index+1}" class="page-link">Next</a></li>
                            </c:if>--%>

                        <c:choose>
                            <c:when test="${not empty search}">
                                <c:if test="${index > 1}">
                                    <li class="page-item"><a href="search?action=searchProduct&pageNumber=${index-1}&keyword=${keyword}">Previous</a></li>
                                    </c:if>
                                    <c:forEach begin="1" end="${endPage}" var="i">
                                    <li class="page-item ${index==i?'active':''}">
                                        <a href="search?action=searchProduct&pageNumber=${i}&keyword=${keyword}" class="page-link">${i}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${index < endPage}">
                                    <li class="page-item"><a href="search?action=searchProduct&pageNumber=${index+1}&keyword=${keyword}" class="page-link">Next</a></li>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${index > 1}">
                                    <li class="page-item"><a href="loadProductAdmin?action=loadProduct&pageNumber=${index-1}">Previous</a></li>
                                    </c:if>
                                    <c:forEach begin="1" end="${endPage}" var="i">
                                    <li class="page-item ${index==i?'active':''}">
                                        <a href="loadProductAdmin?action=loadProduct&pageNumber=${i}" class="page-link">${i}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${index < endPage}">
                                    <li class="page-item"><a href="loadProductAdmin?action=loadProduct&pageNumber=${index+1}" class="page-link">Next</a></li>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                    </ul>
                </div>
            </div>
            <a href="home?action=home"><button type="button" class="btn btn-primary">Back to home</button></a>

            <!-- Edit Modal HTML -->
            <div id="addEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="addProduct" method="post">
                            <input type="hidden" name="action" value="addProduct"/>
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Name</label>
                                    <input name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Image1</label>
                                    <input name="image1" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Image2</label>
                                    <input name="image2" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Image3</label>
                                    <input name="image3" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Color</label>
                                    <input name="color" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Size</label>
                                    <!--<input name="size" type="text" class="form-control" required>-->
                                    <div class="checkbox-group">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="7" checked>
                                                    <label class="form-check-label">7</label>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="7.5" checked>
                                                    <label class="form-check-label">7.5</label>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="8" checked>
                                                    <label class="form-check-label">8</label>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="8.5" checked>
                                                    <label class="form-check-label">8.5</label>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="9" checked>
                                                    <label class="form-check-label">9</label>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="9.5" checked>
                                                    <label class="form-check-label">9.5</label>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="10" checked>
                                                    <label class="form-check-label">10</label>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="10.5" checked>
                                                    <label class="form-check-label">10.5</label>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="11" checked>
                                                    <label class="form-check-label">11</label>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="11.5" checked>
                                                    <label class="form-check-label">11.5</label>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="12" checked>
                                                    <label class="form-check-label">12</label>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-check">
                                                    <input name="size" type="checkbox" class="form-check-input" value="12.5" checked>
                                                    <label class="form-check-label">12.5</label>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input name="price" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="description" class="form-control" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="category" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listCategories}" var="o">
                                            <option value="${o.categoryID}">${o.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>


            <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>