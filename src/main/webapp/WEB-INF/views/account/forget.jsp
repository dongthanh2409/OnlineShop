<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>Forget Password</h2>
<h4>${message}</h4>   
<form action="/account/forget" method="post">
	<div class="form-group">
		<label>User Name</label>
		<input name="id" class="form-control">
	</div>
	<div class="form-group">
		<label>Email</label>
		<input  name="email" class="form-control" >
	</div>
	<div class="form-group">
		<button class="btn btn-default">Retrieve Password</button>
	</div>
</form>