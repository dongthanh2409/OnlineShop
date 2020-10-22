<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>   

<table class="table table-hover">
<thead>
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Unit Price</th>
		<th>Discount</th>
		<th>Quantity</th>
		<th>Amount</th>
		<th></th>
	</tr>
</thead>
<tbody>
	<c:forEach var="d" items="${details}">
		<tr>
			<td>${d.product.id}</td>
			<td>${d.product.name}</td>
			<td>${d.unitPrice}$</td>
			<td>${d.discount}%</td>
			<td>${d.quantity}</td>
			<td>${d.unitPrice*d.quantity*(1-d.discount/100)}$</td>
		</tr>
	</c:forEach>
</tbody>
</table>