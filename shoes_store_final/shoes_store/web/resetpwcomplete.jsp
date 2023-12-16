<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="styles/resetpw.css" rel="stylesheet" type="text/css"/>
        <title>Stock & Stock</title>
    </head>
    <body>
        <nav class="navbar bg-white" style="height: 6rem">
            <div class="container-fluid text-center">
                <a></a>
                <a class="navbar-brand fs-1 fw-bold" href="#" style="color: #006340">STOCK&STOCK</a>
                <a></a>
            </div>
        </nav>
        <hr class="my-2">
        <div class="container-fluid" style="margin-top: 6rem">
            <img src="images/img_resetEmail_2x.webp" alt="alt" class="img-fluid rounded mx-auto d-block" style="width: 360px; height: 180px">
            <div class="text-center my-3">
                <h2 class="fs-1 my-3">Reset Password Email Sent!</h2>
                <p class="fs-5 my-3">If there is an account associated with the provided email address, then you will
                    <br>receive an email with a link to reset your password.</p>
            </div>
            <form action="home" method="post">
                <input type="hidden" name="action" value="home">
                <div class="grid text-center mt-4">
                    <div class="g-col-4"></div>
                    <button type="submit" class="btn btn-dark rounded-0 w-25 g-col-4" style="height: 48px">Finish</button>
                    <div class="g-col-4"></div>
                </div>
            </form>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>