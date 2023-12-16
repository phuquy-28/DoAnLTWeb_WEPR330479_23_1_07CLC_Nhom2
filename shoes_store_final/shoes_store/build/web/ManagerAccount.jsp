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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="table-wrapper">
                <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
                    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                </symbol>
                <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
                    <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
                </symbol>
                <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </symbol>
                </svg>
                <c:if test="${not empty message}">
                    <div class="alert alert-warning d-flex align-items-center" role="alert">
                        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                        <div>
                            ${message}
                        </div>
                    </div>
                </c:if>
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <a href="loadUserAdmin?action=loadUser" style="text-decoration: none; color: inherit;"><h2>Manage <b>Account</b></h2></a>
                        </div>

                        <div class="col-sm-6">
                            <div class="row">
                                <div class="col-sm-6">
                                    <form action="search" method="post">
                                        <input type="hidden" name="action" value="searchUser">
                                        <div class="search-box" style="display: flex; justify-content: center; align-items: center;">
                                            <input type="text" class="form-control" placeholder="Search..." style="margin-right: 5px;" name="keyword" value="${keyword}">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="material-icons">&#xE8B6;</i>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Account</span></a>
                                <a href="#sendMailModal" class="btn btn-primary" data-toggle="modal"><i class="fas fa-envelope"></i> <span>Mail</span></a>
                            </div>
                        </div>
                    </div>
                </div>
                <form action="SendEmailController" method="post">
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
                                <th>Email</th>
                                <th>Password</th>
                                <th>FirstName</th>
                                <th>LastName</th>
                                <th>PhoneNumber</th>
                                <th>Role</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listUser}" var="o">
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox1" name="sendMail" value="${o.email}">
                                            <label for="checkbox1"></label>
                                        </span>
                                    </td>
                                    <td>${o.userId}</td>
                                    <td>${o.email}</td>
                                    <td>${o.password}</td>
                                    <td>${o.firstName}</td>
                                    <td>${o.lastName}</td>
                                    <td>${o.phoneNumber}</td>
                                    <c:choose>
                                        <c:when test="${o.isAdmin == 1}">
                                            <td>Admin</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>User</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>
                                        <a href="loadEditUser?action=loadEditUser&uid=${o.userId}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="deleteUser?action=deleteUser&uid=${o.userId}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div id="sendMailModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <!--<form action="SendEmailController" method="post">-->
                                    <!--<input type="hidden" name="action" value="addUser"/>-->
                                    <div class="modal-header">						
                                        <h4 class="modal-title">Send Email</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label>Title</label>
                                            <textarea name="title" class="form-control" required></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Message</label>
                                            <textarea name="message" class="form-control" required></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                        <input type="submit" class="btn btn-success" value="Send">
                                    </div>
                                <!--</form>-->

                            </div>
                        </div>
                    </div>
                </form>
                <div class="clearfix">
                    <div class="hint-text">Showing <b>${index*10 < userCount ? index*10 : userCount}</b> out of <b>${userCount}</b> entries</div>
                    <ul class="pagination">
                        <%--<c:if test="${index > 1}">
                            <li class="page-item"><a href="loadUserAdmin?action=loadUser&pageNumber=${index-1}">Previous</a></li>
                            </c:if>
                            <c:forEach begin="1" end="${endPage}" var="i">
                            <li class="page-item ${index==i?"active":""}"><a href="loadUserAdmin?action=loadUser&pageNumber=${i}" class="page-link">${i}</a></li>
                            </c:forEach>
                            <c:if test="${index < endPage}">
                            <li class="page-item"><a href="loadUserAdmin?action=loadUser&pageNumber=${index+1}" class="page-link">Next</a></li>
                            </c:if>--%>
                        <c:choose>
                            <c:when test="${not empty search}">
                                <c:if test="${index > 1}">
                                    <li class="page-item"><a href="search?action=searchUser&pageNumber=${index-1}&keyword=${keyword}">Previous</a></li>
                                    </c:if>
                                    <c:forEach begin="1" end="${endPage}" var="i">
                                    <li class="page-item ${index==i?'active':''}">
                                        <a href="search?action=searchUser&pageNumber=${i}&keyword=${keyword}" class="page-link">${i}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${index < endPage}">
                                    <li class="page-item"><a href="search?action=searchUser&pageNumber=${index+1}&keyword=${keyword}" class="page-link">Next</a></li>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${index > 1}">
                                    <li class="page-item"><a href="loadUserAdmin?action=loadUser&pageNumber=${index-1}">Previous</a></li>
                                    </c:if>
                                    <c:forEach begin="1" end="${endPage}" var="i">
                                    <li class="page-item ${index==i?'active':''}">
                                        <a href="loadUserAdmin?action=loadUser&pageNumber=${i}" class="page-link">${i}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${index < endPage}">
                                    <li class="page-item"><a href="loadUserAdmin?action=loadUser&pageNumber=${index+1}" class="page-link">Next</a></li>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                    </ul>
                </div>
            </div>
            <a href="home?action=home"><button type="button" class="btn btn-primary">Back to home</button></a>

        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="addUser" method="post">
                        <input type="hidden" name="action" value="addUser"/>
                        <div class="modal-header">						
                            <h4 class="modal-title">New Account</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Email</label>
                                <input name="email" type="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input name="password" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>FirstName</label>
                                <input name="firstName" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>LastName</label>
                                <input name="lastName" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>PhoneNumber</label>
                                <input name="phone" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Role</label>
                                <select name="role" class="form-select" aria-label="Default select example">
                                    <option value="0">User</option>
                                    <option value="1">Admin</option>
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