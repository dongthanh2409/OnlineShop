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
		<th>Date</th>
		<th></th>
	</tr>
</thead>
<tbody>
	<c:forEach var="p" items="${list}">
		<tr>
			<th>${p.id}</th>
			<th>${p.name}</th>
			<th>${p.unitPrice}</th>
			<th>${p.discount}</th>
			<th>${p.quantity}</th>
			<th>${p.productDate}</th>
			<th>
				<a class="btn btn-sm btn-warning" href="${base}/edit/${p.id}">Edit</a>
				<a class="btn btn-sm btn-danger" href="${base}/delete/${p.id}">Delete</a>
			</th>
		</tr>
	</c:forEach>
</tbody>
</table>