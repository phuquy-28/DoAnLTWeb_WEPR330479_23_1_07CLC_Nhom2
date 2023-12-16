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
                <a class="navbar-brand fs-1 fw-bold" href="#" style="color: #006340">STOCK&STOCK</a>
                <a></a>
            </div>
        </nav>
        <div>
            <div class="container-fluid p-3" style="width: 28rem; margin-top: 4rem">
                <div class="text-center fs-2 mb-3"><span>Reset Password</span></div>
                <div class="container-fluid border p-4 bg-white">
                    <div class="text-center mb-4">
                        <span>Please enter the email address that is 
                            <br>associated with your StockX account.</span>
                    </div>

                    <form action="resetPass" method="post">
                        <div class="form-floating mb-2">
                            <input type="email" class="form-control rounded-0" id="email" placeholder="First Name" required name="email">
                            <label for="email" class="form-label required-field">Email Address</label>
                        </div>

                        <input type="hidden" name="action" value="submitEmail">
                        <c:if test="${not empty message}">
                            <p class="alert alert-warning form-text fw-semibold"><i>${message}</i></p>
                                </c:if>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-dark rounded-0 mt-2" style="height: 48px" name="action" value="forgetPassword">Reset Password</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>