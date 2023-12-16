<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Stock&Stock</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" rel="stylesheet">
        <link rel="stylesheet" href="styles/main.css" type="text/css">
    </head>

    <body>
        <nav class="navbar navbar-expand-xl border-bottom border-dark"> 
            <div class="container-fluid">
                <a class="navbar-brand fs-3 fw-bold mx-3" href="/StockAndStock" style="color: #006340">STOCK&STOCK</a>
                <div style="width:46%">
                    <div class="d-flex form-inputs border border-dark" style="width: 100%;">
                        <input class="form-control rounded-0" type="text" placeholder="Search any product..." style="background-color: #fafafa">
                        <i class="bx bx-search"></i>
                    </div>
                </div>
                <button class="navbar-toggler"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbar"> 
                    <span class="navbar-toggler-icon"> 
                    </span> 
                </button>
                <div class="collapse navbar-collapse ps-3" 
                     id="navbar"> 
                    <ul class="navbar-nav"> 
                        <li class="nav-item"> 
                            <a class="nav-link text-dark fs-5 fw-semibold mx-1" href="loadUserAdmin?action=loadUser"> 
                                Account
                            </a> 
                        </li> 
                        <li class="nav-item"> 
                            <a class="nav-link text-dark fs-5 fw-semibold mx-1" href="loadProductAdmin?action=loadProduct"> 
                                Product
                            </a> 
                        </li> 
                        <li class="nav-item"> 
                            <a class="nav-link text-dark fs-5 fw-semibold mx-1" href="/StockAndStock"> 
                                Home
                            </a> 
                        </li> 
                        <li class="nav-item"> 
                            <a class="nav-link text-dark fs-5 fw-semibold mx-1" href="#"> 
                                Brand
                            </a> 
                        </li>
                        <li class="nav-item"> 
                            <a class="nav-link text-dark fs-5 fw-semibold mx-1" href="#"> 
                                Cart
                            </a> 
                        </li>
                        <li class="nav-item"> 
                            <a class="nav-link text-dark fs-5 fw-semibold mx-1" href="#"> 
                                About
                            </a> 
                        </li>
                        <li class="nav-item"> 
                            <a class="nav-link text-dark fs-5 fw-semibold mx-1" href="#"> 
                                Help
                            </a> 
                        </li>
                        <!--                        <li class="nav-item"> 
                                                    <div class="d-flex flex-row align-items-center nav-link">
                                                        <form action="login" method="post">
                                                            <input type="hidden" name="action" value="login">
                                                            <button type="submit" name="action" value="login" class="btn btn-outline-dark rounded-0 fw-semibold me-2">Login</button>
                                                        </form>
                        
                                                        <form action="login" method="post">
                                                            <input type="hidden" name="action" value="signup">
                                                            <button type="submit" name="action" value="signup" class="btn btn-outline-dark rounded-0 fw-semibold me-2">Sign Up</button>
                                                        </form>
                                                    </div>
                                                </li>
                                            </ul> -->
                        <li class="nav-item">
                            <c:if test="${not empty sessionScope.user}">
                                <!-- If the user is logged in, show the edit and logout buttons -->
                                <div class="d-flex flex-row align-items-center nav-link">
                                <form action="edit_profile" method="post">
                                    <input type="hidden" name="action" value="edit">
                                    <button type="submit" name="action" value="edit" class="btn btn-outline-dark rounded-0 fw-semibold me-2 text-nowrap">Edit</button>
                                </form>

                                <form action="logout?action=logout" method="post">
                                    <input type="hidden" name="action" value="logout">
                                    <button type="submit" name="action" value="logout" class="btn btn-outline-dark rounded-0 fw-semibold me-2 text-nowrap">Log Out</button>
                                </form>
                                </div>
                            </c:if>

                            <c:if test="${empty sessionScope.user}">
                                <!-- If the user is not logged in, show the login and signup buttons -->
                                <div class="d-flex flex-row align-items-center nav-link">
                                <form action="login" method="post">
                                    <input type="hidden" name="action" value="login">
                                    <button type="submit" name="action" value="login" class="btn btn-outline-dark rounded-0 fw-semibold me-2 text-nowrap">Login</button>
                                </form>

                                <form action="register" method="post">
                                    <input type="hidden" name="action" value="signup">
                                    <button type="submit" name="action" value="signup" class="btn btn-outline-dark rounded-0 fw-semibold me-2 text-nowrap">Sign Up</button>
                                </form>
                                </div>
                            </c:if>
                        </li>
                    </ul>
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

                    <c:if test="${not empty sessionScope.user}">
                        <c:set var="loggedInUser" value="${sessionScope.user}" />
                        <c:out value="Welcome, ${loggedInUser.firstName}" />
                    </c:if>
                </div> 
            </div> 
        </nav>