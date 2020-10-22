<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>   

<table class="table table-hover">
<thead>
	<tr>
		<th>Id</th>
		<th>Full Name</th>
		<th>Email</th>
		<th>Activated</th>
		<th>Role</th>
		<th></th>
	</tr>
</thead>
<tbody>
	<c:forEach var="c" items="${list}">
		<tr>
			<td>${c.id}</td>
			<td>${c.fullname}</td>
			<td>${c.email}</td>
			<td>${c.activated?"Yes":"No"}</td>
			<td>${c.admin?"Administrator":"User"}</td>
			<td>
				<button class="btn btn-sm btn-warning"  onclick="location.href='${base}/edit/${c.id}';">Edit</button>
				<button class="btn btn-sm btn-danger"  onclick="location.href='${base}/delete/${c.id}';" ${c.activated?"disabled":""}>Delete</button>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
