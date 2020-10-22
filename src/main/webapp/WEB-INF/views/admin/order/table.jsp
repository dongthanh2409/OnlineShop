<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>   

<table class="table table-hover">
<thead>
	<tr>
		<th>Id</th>
		<th>Customer</th>
		<th>Order Date</th>
		<th>Shipping Address</th>
		<th>Total Amount</th>
		<th>Status</th>
		<th></th>
	</tr>
</thead>
<tbody>
	<c:forEach var="o" items="${list}">
		<tr>
			<td>${o.id}</td>
			<td>${o.customer.id}</td>
			<td>${o.orderDate}</td>
			<td>${o.address}</td>
			<td>${o.amount}$</td>
			<td>${o.completed?"Completed":"Not Completed"}</td>
			<td>
				<a class="btn btn-sm btn-warning" href="${base}/edit/${o.id}">${o.completed?"Detail":"Edit"}</a>
				<a class="btn btn-sm btn-danger" href="${base}/delete/${o.id}">Delete</a>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>