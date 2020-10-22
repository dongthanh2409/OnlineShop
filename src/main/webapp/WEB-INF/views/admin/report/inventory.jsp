<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<h2 class="alert alert-success">INVENTORY</h2>
<table class="table table-hover">
<thead>
	<tr>
		<th>Category</th>
		<th>Quantity</th>
		<th>Value</th>
		<th>Min</th>
		<th>Max</th>
		<th>Avg</th>
	</tr>
</thead>
<tbody>
	<c:forEach var="d" items="${data}">
		<tr>
			<td>${d[0]}</td>
			<td>${d[1]}</td>
			<td>$<f:formatNumber value="${d[2]}" pattern="#,###.00"/></td>
			<td>$<f:formatNumber value="${d[3]}" pattern="#,###.00"/></td>
			<td>$<f:formatNumber value="${d[4]}" pattern="#,###.00"/></td>
			<td>$<f:formatNumber value="${d[5]}" pattern="#,###.00"/></td>			
		</tr>
	</c:forEach>
</tbody>
</table>

<jsp:include page="_inventory_chart.jsp"></jsp:include>