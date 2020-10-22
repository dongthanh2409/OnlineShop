<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>Login</h2>
<h4>${message}</h4>   
<form action="/account/login" method="post">
	<div class="form-group">
		<label>User Name</label>
		<input type="text" name="id" class="form-control" value="${uid}">
	</div>
	<div class="form-group">
		<label>Password</label>
		<input type="password" name="password" class="form-control" value="${pwd}">
	</div>
	<div class="form-group">
		<input name="rm" type="checkbox">
		<label>Remember me</label>
	</div>
	<div class="form-group">
		<button class="btn btn-default">Login</button>
	</div>
</form>