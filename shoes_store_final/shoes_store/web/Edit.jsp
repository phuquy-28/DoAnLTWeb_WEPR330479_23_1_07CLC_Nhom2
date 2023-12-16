<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Edit <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="editProduct" method="post">
                            <!--<input type="hidden" name="action" value="editProduct"/>-->
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Product</h4>
                                <button type="submit" class="close" data-dismiss="modal" aria-hidden="true" name="action" value="closeProduct">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>ID</label>
                                    <input value="${product.productID}" name="id" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input value="${product.productName}" name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Color</label>
                                    <input value="${product.productColor}" name="color" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Size</label><br>
                                    <div class="checkbox-group">
                                        <div class="row">
                                            <c:forEach var="index" begin="0" end="3" step="1">
                                                <c:set var="size" value="${7.0 + index * 0.5}" />
                                                <div class="col-md-3">
                                                    <div class="form-check">
                                                        <input name="size" type="checkbox" class="form-check-input" value="${size}" 
                                                               <c:if test="${fn:contains(productSizeValues, size)}">checked</c:if>>
                                                        <label class="form-check-label">${size}</label>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="row">
                                            <c:forEach var="index" begin="4" end="7" step="1">
                                                <c:set var="size" value="${7.0 + index * 0.5}" />
                                                <div class="col-md-3">
                                                    <div class="form-check">
                                                        <input name="size" type="checkbox" class="form-check-input" value="${size}" 
                                                               <c:if test="${fn:contains(productSizeValues, size)}">checked</c:if>>
                                                        <label class="form-check-label">${size}</label>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="row">
                                            <c:forEach var="index" begin="8" end="11" step="1">
                                                <c:set var="size" value="${7.0 + index * 0.5}" />
                                                <div class="col-md-3">
                                                    <div class="form-check">
                                                        <input name="size" type="checkbox" class="form-check-input" value="${size}" 
                                                               <c:if test="${fn:contains(productSizeValues, size)}">checked</c:if>>
                                                        <label class="form-check-label">${size}</label>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Image1</label>
                                    <input value="${product.images[0].imageURL}" name="image1" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Image2</label>
                                    <input value="${product.images[1].imageURL}" name="image2" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Image3</label>
                                    <input value="${product.images[2].imageURL}" name="image3" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input value="${product.productPrice}" name="price" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="description" class="form-control" required>${product.productDecription}</textarea>
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="category" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listCategories}" var="o">
                                            <c:choose>
                                                <c:when test="${o.categoryID eq product.category.categoryID}">
                                                    <option value="${o.categoryID}" selected>${o.categoryName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${o.categoryID}">${o.categoryName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>


                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" name="action" value="editProduct">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>


        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>