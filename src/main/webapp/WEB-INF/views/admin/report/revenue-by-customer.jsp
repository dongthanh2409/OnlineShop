<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<h2 class="alert alert-success">REVENUE BY CUSTOMER</h2>
<table class="table table-hover">
<thead>
	<tr>
		<th>Customer</th>
		<th>Quantity</th>
		<th>Revenue</th>
	</tr>
</thead>
<tbody>
	<c:forEach var="d" items="${data}">
		<tr>
			<td>${d[0]}</td>
			<td>${d[1]}</td>
			<td>$<f:formatNumber value="${d[2]}" pattern="#,###.00"/></td>
		</tr>
	</c:forEach>
</tbody>
</table>
