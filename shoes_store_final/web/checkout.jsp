<%@ include file="/header.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- start content -->
<div class="container p-3">
    <div class="row">
        <div class="col-md-4 order-md-2 mb-4">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Your cart</span>
                <span class="badge badge-secondary badge-pill"></span>
            </h4>
            <ul class="list-group mb-3">
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <c:forEach var="item" items="${items}">
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0">${item.product.productName}</h6>
                            <small class="text-muted">Size: ${item.shoeSize},</small>
                            <small class="text-muted">Quantity: ${item.quantity}</small>
                        </div>
                        <span class="text-muted">$${item.getAmount()}</span>
                    </li>
                </c:forEach>
                <li class="list-group-item d-flex justify-content-between bg-light">
                    <div class="text-success">
                        <h6 class="my-0">Promo code</h6>
                        <small>${promotion.promotionCode}</small>
                    </div>
                    <span class="text-success">-$${promotion.promotionAmount}</span>
                </li>
                <li class="list-group-item d-flex justify-content-between">
                    <span>Total (USD)</span>
                    <strong>$${totalPrice-promotion.promotionAmount}</strong>
                </li>
            </ul>
        </div>
        <div class="col-md-8 order-md-1">
            <h4 class="mb-3">Billing address</h4>
            <form action="payment" method="post">
                <input type="hidden" name="discount" value="${promotion.promotionAmount}">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstName">First name</label>
                        <input type="text" class="form-control" name="firstName" placeholder="" value="${user.firstName}" required>
                        <div class="invalid-feedback">
                            Valid first name is required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName">Last name</label>
                        <input type="text" class="form-control" name="lastName" placeholder="" value="${user.lastName}" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" name="email" value="${user.email}" disabled>
                </div>

                <div class="mb-3">
                    <label for="address">Phone Number</label>
                    <input type="text" class="form-control" name="phone" placeholder="1234567890" required>
                    <div class="invalid-feedback">
                        Please enter your phone number.
                    </div>
                </div>
                
                <div class="mb-3">
                    <label for="address">Address</label>
                    <input type="text" class="form-control" name="address" placeholder="1 Vo Van Ngan, Thanh pho Thu Duc" required>
                    <div class="invalid-feedback">
                        Please enter your shipping address.
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-5 mb-3">
                        <label for="country">Country</label>
                        <input type="text" class="form-control" name="country" placeholder="Viet Nam" required>
                        <div class="invalid-feedback">
                            Please select a valid country.
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="zip">Zip</label>
                        <input type="text" class="form-control" name="zip" placeholder="" required>
                        <div class="invalid-feedback">
                            Zip code required.
                        </div>
                    </div>
                </div>
                <hr class="mb-4">

                <h4 class="mb-3">Payment</h4>

                <div class="d-block my-3">
                    <div class="custom-control custom-radio">
                        <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" value="Cash in hand" checked required>
                        <label class="custom-control-label" for="credit">Cash in hand</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" value="Bank transfer" required>
                        <label class="custom-control-label" for="debit">Bank transfer</label>
                    </div>
<!--                    <div class="custom-control custom-radio">
                        <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required>
                        <label class="custom-control-label" for="paypal">PayPal</label>
                    </div>-->
                </div>
<!--                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="cc-name">Name on card</label>
                        <input type="text" class="form-control" id="cc-name" placeholder="" required>
                        <small class="text-muted">Full name as displayed on card</small>
                        <div class="invalid-feedback">
                            Name on card is required
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="cc-number">Credit card number</label>
                        <input type="text" class="form-control" id="cc-number" placeholder="" required>
                        <div class="invalid-feedback">
                            Credit card number is required
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 mb-3">
                        <label for="cc-expiration">Expiration</label>
                        <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                        <div class="invalid-feedback">
                            Expiration date required
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="cc-cvv">CVV</label>
                        <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                        <div class="invalid-feedback">
                            Security code required
                        </div>
                    </div>
                </div>-->
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
            </form>
        </div>
    </div>
</div>
<!-- end content -->

<%@ include file="/footer.jsp" %>