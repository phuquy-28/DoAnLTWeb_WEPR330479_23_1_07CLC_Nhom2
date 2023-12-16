<%@ include file="/header.jsp" %>

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet">

<link href="css/detailProduct.css" rel="stylesheet" type="text/css"/>
<!-- start content -->

<div class="container">
    <div class="card">
        <div class="container-fliud">
            <form action="cart" method="post">
                <input type="hidden" name="action" value="cart">
                <input type="hidden" name="productID" value="${detailShoes.productID}">
                <input type="hidden" name="quantity" value="1">
                <div class="wrapper row">
                    <div class="preview col-md-6">
                        <div class="preview-pic tab-content">
                            <div class="tab-pane active" id="pic-1"><img src="${detailShoes.images[0].imageURL}" alt=""/></div>
                            <div class="tab-pane" id="pic-2"><img src="${detailShoes.images[1].imageURL}" alt=""/></div>
                            <div class="tab-pane" id="pic-3"><img src="${detailShoes.images[2].imageURL}" alt=""/></div>
                        </div>
                    </div>
                    <div class="details col-md-6">
                        <h3 class="product-title">${detailShoes.productName}</h3>
                        <!--						<div class="rating">
                                                    <div class="stars">
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star checked"></span>
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                    </div>
                                                    <span class="review-no">41 reviews</span>
                                            </div>-->
                        <p class="product-description">${detailShoes.productDecription}</p>
                        <h4 class="price">current price: <span>$${detailShoes.productPrice}</span></h4>
                        <!--<p class="vote"><strong>91%</strong> of buyers enjoyed this product! <strong>(87 votes)</strong></p>-->
                        <h5 class="sizes">category: ${detailShoes.category.categoryName}</h5>
                        <h5 class="sizes">colors: ${detailShoes.productColor}</h5>
                        <select name="selectedSize" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
                            <c:forEach var="sizeList" items="${detailShoes.sizes}">
                                <option value="${sizeList.sizeValue}">SIZE: ${sizeList.sizeValue}</option>
                            </c:forEach> 
                        </select>
                        <div class="action">
                            <button class="add-to-cart btn btn-default" type="submit">add to cart</button>
                        </div>

                        <ul class="preview-thumbnail nav nav-tabs">
                            <li class="active"><a data-target="#pic-1" data-toggle="tab"><img src="${detailShoes.images[0].imageURL}" alt=""/></a></li>
                            <li><a data-target="#pic-2" data-toggle="tab"><img src="${detailShoes.images[1].imageURL}" alt=""/></a></li>
                            <li><a data-target="#pic-3" data-toggle="tab"><img src="${detailShoes.images[2].imageURL}" alt=""/></a></li>
                        </ul>
                    </div>
                </div>   
            </form>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col p-2 h2">Recommended For You</div>
    </div>
    <div class="row">
        <c:forEach items="${listShoes}" var="o">
            <div class="col-md-3 p-2">
                <div class="border border-dark">
                    <div class="col px-2 py-1">
                        <a href="loadProductDetail?productID=${o.productID}">
                            <img src="${o.images[0].imageURL}" alt="alt" class="rounded mx-auto d-block img-fluid p-2">
                        </a>
                    </div>

                    <div class="col px-2 py-1">${o.productName}</div>
                    <div class="col px-2 py-1 h5">$${o.productPrice}</div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<!-- end content -->

<%@ include file="/footer.jsp" %>