<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="row">
	<div class="col-sm-5 text-center">
		<img class="detail-img" src="/static/images/products/${prod.image}">
	</div>
	<div class="col-sm-7">
		<ul class="detail-info">
			<li>Name: ${prod.name}</li>
			<li>Unit Price: ${prod.unitPrice}</li>
			<li>Production Date: ${prod.productDate}</li>
			<li>Category: ${prod.category.nameVN}</li>
			<li>Quantity: ${prod.quantity}</li>
			<li>Discount: ${prod.discount}%</li>
			<li>View Count: ${prod.viewCount}</li>
			<li>Available: ${prod.available?"Yes" : "No"}</li>
			<li>Special: ${prod.special?"Yes" : "No"}</li>
		</ul>
	</div>
</div>

<div class="text-justify">${prod.description}</div> 
<hr><hr>

<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#tab1">RELEVANT ITEMS</a></li>
  <li><a data-toggle="tab" href="#tab2">FAVORITE ITEMS</a></li>
  <li><a data-toggle="tab" href="#tab3">VIEWED ITEMS</a></li>
</ul>

<div class="tab-content">
  <div id="tab1" class="tab-pane fade in active">
    <c:forEach var="p" items="${list}">
		<a href="/product/detail/${p.id}"> 
			<img class="thumb-img" src="/static/images/products/${p.image}">
		</a>
	</c:forEach>
  </div>
  <div id="tab2" class="tab-pane fade">
    <c:forEach var="p" items="${favo}">
		<a href="/product/detail/${p.id}"> 
			<img class="thumb-img" src="/static/images/products/${p.image}">
		</a>
	</c:forEach>
  </div>
  <div id="tab3" class="tab-pane fade">
 	<c:forEach var="p" items="${viewed}">
		<a href="/product/detail/${p.id}"> 
			<img class="thumb-img" src="/static/images/products/${p.image}">
		</a>
	</c:forEach>
  </div>
</div>

