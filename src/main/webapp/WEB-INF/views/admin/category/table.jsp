<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>   

<table class="table table-hover">
<thead>
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>NameVN</th>
		<th></th>
	</tr>
</thead>
<tbody>
	<c:forEach var="e" items="${list}">
		<tr>
			<td>${e.id}</td>
			<td>${e.name}</td>
			<td>${e.nameVN}</td>
			<td>
				<a class="btn btn-sm btn-warning" href="${base}/edit/${e.id}">Edit</a>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>