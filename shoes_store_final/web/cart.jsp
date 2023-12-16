<%@ include file="/header.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- start content -->
<div class="container p-3 my-5 clearfix ">
    <!-- Shopping cart table -->
    <div class="card">
        <div class="card-header">
            <h2>Shopping Cart</h2>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="checkout">
                    <table class="table table-bordered m-0">
                        <thead>
                            <tr>
                                <!-- Set columns width -->
                                <th class="text-center" style="width: 20px"></th>
                                <th class="text-center py-3 px-4" style="min-width: 400px;">Product Name &amp; Details</th>
                                <th class="text-right py-3 px-4" style="width: 100px;">Price</th>
                                <th class="text-center py-3 px-4" style="width: 120px;">Quantity</th>
                                <th class="text-right py-3 px-4" style="width: 100px;">Total</th>
                                <th class="text-center align-middle py-3 px-0" style="width: 40px;"><a href="#" class="shop-tooltip float-none text-light" title="" data-original-title="Clear cart"><i class="ino ion-md-trash"></i></a></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                            <c:forEach var="item" items="${cart.productList}">
                                <tr>
                                    <td class="py-5">
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox1" name="selectedItem" class="itemAmount" data-amount="${item.getAmount()}" value="${item.lineItemID}" checked>
                                            <label for="checkbox1"></label>
                                        </span>
                                    </td>
                                    <td class="p-4">
                                        <div class="media align-items-center">
                                            <img src="${item.product.images[0].imageURL}" class="d-block ui-w-40 ui-bordered mr-4" alt="">
                                            <div class="media-body">
                                                <a href="#" class="d-block text-dark">${item.product.productName}</a>
                                                <small>
                                                    <span class="text-muted">Color: ${item.product.productColor}</span>
                                                    <span class="text-muted">Size: </span>${item.shoeSize} &nbsp;
                                                </small>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="text-right font-weight-semibold align-middle p-4">$${item.product.productPrice}</td>
                                    <td class="align-middle p-3">
                                        <a href="cart?productID=${item.product.productID}&amp;quantity=${item.quantity}&amp;action=update&amp;change=des&amp;size=${item.shoeSize}" class="shop-tooltip close float-none text-primary fw-semibold">-</a>
                                        <input type="text" class="form-control text-center" value="${item.quantity}" readonly style="width:60px; display: inline">
                                        <a href="cart?productID=${item.product.productID}&amp;quantity=${item.quantity}&amp;action=update&amp;change=inc&amp;size=${item.shoeSize}" class="shop-tooltip close float-none text-primary fw-semibold">+</a>
                                    </td>
                                    <td class="text-right font-weight-semibold align-middle p-4" value="${item.getAmount()}">$${item.getAmount()}</td>
                                    <td class="text-center align-middle px-0"><a href="cart?productID=${item.product.productID}&amp;quantity=0&amp;action=cart&amp;size=${item.shoeSize}" class="shop-tooltip close float-none text-danger" title="" data-original-title="Remove">×</a></td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                    <!-- / Shopping cart table -->

                    <div class="d-flex flex-wrap justify-content-between align-items-center pb-4">
                        <div class="mt-4">
                            <label class="text-muted font-weight-normal">Promocode</label>
                            <input type="text" placeholder="ABC" class="form-control mb-3" name="promotion">
                            <c:if test="${not empty message}">
                                <div class="alert alert-warning d-flex align-items-center" role="alert">
                                    <svg class="bi flex-shrink-0 me-2" width="16" height="16" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                    <div>
                                        ${message}
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <div class="d-flex">
                            <div class="text-right mt-4 me-5">
                            </div>
                            <div class="text-right mt-4">
                                <label class="text-muted font-weight-normal m-0">Total price</label>
                                <div class="text-large"><strong id="totalCart">$${cart.totalCart()}</strong></div>
                            </div>
                        </div>
                    </div>

                    <div class="float-right">
                        <a href="loadProductShop?action=loadAll" style="text-decoration: none">
                            <button type="button" class="btn btn-lg btn-outline-dark md-btn-flat mt-2 mr-3">
                                Back to shopping
                            </button>
                        </a>
                        <button type="submit" class="btn btn-lg btn-primary mt-2">Checkout</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- end content -->
<script src="js/cart.js"></script>
<%@ include file="/footer.jsp" %>