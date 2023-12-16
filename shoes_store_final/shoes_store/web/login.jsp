<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="styles/login.css" rel="stylesheet" type="text/css"/>
        <title>Login Form</title>
    </head>
    <body>
        <nav class="navbar bg-white" style="height: 6rem">
            <div class="container-fluid text-center">
                <a></a>
                <a class="navbar-brand fs-1 fw-bold" href="<c:url value='/index.jsp'/>" style="color: #006340">STOCK&STOCK</a>
                <a></a>
            </div>
        </nav>
        <div class="container-fluid border my-4 p-3 bg-white" style="width: 28rem">
            <!-- Pills navs -->
            <ul class="nav nav-pills nav-justified mb-3" id="pills-tab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active rounded-0" id="pills-signup-tab" data-bs-toggle="pill" data-bs-target="#pills-signup" type="button" role="tab" aria-controls="pills-signup" aria-selected="true">Sign Up</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link rounded-0" id="pills-login-tab" data-bs-toggle="pill" data-bs-target="#pills-login" type="button" role="tab" aria-controls="pills-login" aria-selected="false">Log In</button>
                </li>
            </ul>
            <!-- Pills navs -->

            <!-- Pills content -->
            <div class="tab-content" id="pills-tabContent">
                <div class="tab-pane fade show active" id="pills-signup" role="tabpanel" aria-labelledby="pills-signup-tab" tabindex="0">
                    <form action="login" method="post">
                        <input type="hidden" name="action" value="register">
                        <div class="form-floating mb-2">
                            <input type="text" class="form-control rounded-0" id="fname" placeholder="First Name" name="firstName" required value="${user.firstName}"/>
                            <label for="fname" class="form-label required-field">First Name</label>
                        </div>
                        <div class="form-floating mb-2">
                            <input type="text" class="form-control rounded-0" id="lname" placeholder="Last Name" name="lastName" required value="${user.lastName}"/>
                            <label for="lname" class="form-label required-field">Last Name</label>
                        </div>
                        <div class="form-floating mb-2">
                            <input type="email" class="form-control rounded-0" id="email" placeholder="Email Address" name="email" required value="${user.email}"/>
                            <label for="email" class="form-label required-field">Email Address</label>
                        </div>
                        <div class="form-floating mb-2">
                            <input type="text" class="form-control rounded-0" id="phone" placeholder="Phone Number" name="phone" required value="${user.phoneNumber}"/>
                            <label for="phone" class="form-label required-field">Phone Number</label>
                        </div>
                        <div class="form-floating mb-2">
                            <input type="password" class="form-control rounded-0" id="password" placeholder="Password" aria-describedby="requirePass" name="password" required>
                            <label for="password" class="form-label required-field">Password</label>
                            <div id="requirePass" class="form-text fw-semibold">At least 8 characters, 1 uppercase letter, 1 number & 1 symbol</div>
                            <c:if test="${not empty message}">
                                <p class="alert alert-warning form-text fw-semibold"><i>${message}</i></p>
                                    </c:if>
                        </div>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary rounded-1" style="height: 48px">Sign Up</button>
                        </div>
                        <div class="text-center my-2">
                            <span>Already have an account? </span>
                            <a href="#" id="login-link">Log In</a>
                        </div>
                    </form>
                </div>
                <div class="tab-pane fade" id="pills-login" role="tabpanel" aria-labelledby="pills-login-tab" tabindex="0">
                    <form action="home" method="POST">
                        <input type="hidden" name="action" value="verify">
                        <div class="form-floating mb-2">
                            <input type="email" class="form-control rounded-0" id="emailLogin" placeholder="user@example.com" aria-describedby="emailHelp" name="email" required value="${cookie.emailCookie.value}">
                            <label for="emailLogin" class="form-label required-field">Email address</label>
                            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                        </div>
                        <div class="form-floating mb-2">
                            <input type="password" class="form-control rounded-0" id="passwordLogin" placeholder="Password" name="password" required value="${cookie.passCookie.value}">
                            <label for="passwordLogin" class="form-label required-field">Password</label>
                        </div>
                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" value="" id="rememberMe" name="rememberPassword">
                            <label class="form-check-label" for="rememberMe">Remember me</label>
                        </div>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary rounded-1" style="height: 48px">Log In</button>
                        </div>
                    </form>    
                    <div class="text-end">
                        <a href="resetPass?action=forgetPassword" style="text-decoration: none">Forget Password?</a>
                    </div>
                    <hr class="my-2">
                    <div class="d-grid gap-2">
                        <button type="button" class="btn btn-outline-dark rounded-0" style="height: 48px">Continue with Google</button>
                        <button type="button" class="btn btn-outline-dark rounded-0" style="height: 48px">Continue with Facebook</button>
                    </div>
                    <div class="text-center my-2">
                        <span>Need an account? </span>
                        <a href="#" id="signup-link">Sign Up</a>
                    </div>
                </div>
            </div>
            <!-- Pills content -->
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
        <script>
            document.getElementById('signup-link').addEventListener('click', function () {
                var myTab = new bootstrap.Tab(document.getElementById('pills-signup-tab'));
                myTab.show();
            });
            document.getElementById('login-link').addEventListener('click', function () {
                var myTab = new bootstrap.Tab(document.getElementById('pills-login-tab'));
                myTab.show();
            });
        </script>
    </body>
</html>