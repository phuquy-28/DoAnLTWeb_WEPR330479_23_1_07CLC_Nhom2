<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="styles/resetpw.css" rel="stylesheet" type="text/css"/>
        <title>Forget Password</title>
    </head>
    <body>
        <nav class="navbar bg-white" style="height: 6rem">
            <div class="container-fluid text-center">
                <a></a>
                <a class="navbar-brand fs-1 fw-bold" href="home?action=home" style="color: #006340">STOCK&STOCK</a>
                <a></a>
            </div>
        </nav>
        <div>
            <div class="container-fluid p-3" style="width: 28rem; margin-top: 1rem">
                <div class="text-center fs-2 mb-3"><span>Edit Profile</span></div>
                <div class="container-fluid border p-4 bg-white">
                    <div class="text-center mb-4">
                        <span>To change information you need to enter your current password</span>
                    </div>

                    <form action="editUsr" method="post">
                        <input type="hidden" name="action" value="editUsr">
                        <div class="form-floating mb-2">
                            <input type="text" class="form-control rounded-0" id="email" placeholder="First Name" readonly required name="id" value="${id}">
                            <label for="email" class="form-label required-field">User ID</label>
                        </div>
                        
                        <div class="form-floating mb-2">
                            <input type="email" class="form-control rounded-0" id="email" placeholder="First Name" readonly required name="email" value="${email}">
                            <label for="email" class="form-label required-field">Email Address</label>
                        </div>
                        
                        <div class="form-floating mb-2">
                            <input type="text" class="form-control rounded-0" id="email" placeholder="First Name" required name="firstName" value="${firstName}">
                            <label for="email" class="form-label required-field">First Name</label>
                        </div>
                        
                        <div class="form-floating mb-2">
                            <input type="text" class="form-control rounded-0" id="email" placeholder="Last Name" required name="lastName" value="${lastName}">
                            <label for="email" class="form-label required-field">Last Name</label>
                        </div>
                        
                        <div class="form-floating mb-2">
                            <input type="text" class="form-control rounded-0" id="email" placeholder="First Name" required name="phone" value="${phone}">
                            <label for="email" class="form-label required-field">Phone Number</label>
                        </div>
                        
                        <div class="form-floating mb-2">
                            <input type="password" class="form-control rounded-0" id="email" placeholder="First Name" required name="password">
                            <label for="email" class="form-label required-field">Current Password</label>
                        </div>
                        
                        <div class="form-floating mb-2">
                            <input type="password" class="form-control rounded-0" id="email" placeholder="First Name" name="newpass">
                            <label for="email" class="form-label required-field">New Password (Optional)</label>
                        </div>
                        
                        <c:if test="${not empty message}">
                            <p class="alert alert-warning form-text fw-semibold"><i>${message}</i></p>
                                </c:if>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-dark rounded-0 mt-2" style="height: 48px" name="action" value="forgetPassword">Save Edit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>