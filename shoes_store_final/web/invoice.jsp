<%@ include file="/header.jsp" %>

<div class="card">
    <div class="card-body">
        <div class="container">
            <p class="my-5 mx-5 fw-semibold" style="font-size: 40px; text-align: center; color: #006340">Thank for your purchase</p>
            <div class="row">
                <ul class="list-unstyled">
                    <li class="text-black">Name: ${invoice.address.lastName} ${invoice.address.firstName}</li>
                    <li class="text-muted mt-1"><span class="text-black">Invoice</span> #${invoice.invoiceID}</li>
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
                    <li class="text-black mt-1">Date: <fmt:formatDate value="${invoice.invoiceDate}" pattern="dd/MM/yyyy"/></li>
                    <li class="text-black">Address: ${invoice.address.toString()}</li>
                </ul>
                <hr>
            </div>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <c:forEach var="item" items="${invoice.productList}">
                <div class="row">
                    <div class="col-xl-8">
                        <p class="my-0">${item.product.productName}</p>
                        <small class="text-muted">Size: ${item.shoeSize}</small>
                    </div>
                    <div class="col-xl-2">
                        <p>Quantity: ${item.quantity}</p>
                    </div>
                    <div class="col-xl-2">
                        <p class="float-end">$${item.getAmount()}
                        </p>
                    </div>
                    <hr>
                </div>
            </c:forEach>
            <div class="row text-black">
                <div class="col-xl-12">
                    <p class="float-end">Sub total: $${invoice.subTotal}
                    </p>
                </div>
                <div class="col-xl-12">
                    <p class="float-end">Discount: -$${invoice.discount}
                    </p>
                </div>
                <div class="col-xl-12">
                    <p class="float-end fw-bold">Total: $${invoice.total}
                    </p>
                </div>
                <hr style="border: 2px solid black;">
            </div>

        </div>
    </div>
</div>