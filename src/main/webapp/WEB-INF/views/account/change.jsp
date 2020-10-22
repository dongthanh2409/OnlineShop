<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>CHANGE PASSWORD</h2>
<h4>${message}</h4>   
<form action="/account/change" method="post">
	<div class="form-group">
		<label>Current Password</label>
		<input type="password" name="pw" class="form-control" >
	</div>
	<div class="form-group">
		<label>New Password</label>
		<input type="password" name="pw1" class="form-control" >
	</div>
	<div class="form-group">
		<label>Confirm New Password</label>
		<input type="password" name="pw2" class="form-control" >
	</div>
	<div class="form-group">
		<button class="btn btn-default">Change Password</button>
	</div>
</form>