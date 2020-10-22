<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/home/index">Home</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/home/about">About Us</a></li>
			<li><a href="/home/contact">Contact Us</a></li>
			<li><a href="/home/feedback">FeedBack</a></li>
			<li><a href="/home/faq">FAQs</a></li>
			<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Account <span class="caret"></span></a>
				<c:choose>
					<c:when test="${empty sessionScope.user}">
						<ul class="dropdown-menu">
							<li><a href="/account/login">Login</a></li>
							<li><a href="/account/register">Register</a></li>
							<li><a href="/account/forget">Forget Password</a></li>					
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="dropdown-menu">
							<li><a href="/account/change">Change Password</a></li>
							<li><a href="/account/edit">Update Account</a></li>
							<li><a href="/order/list">Shopping Cart</a></li>
							<li><a href="/account/logout">Logout</a></li>
						</ul>
					</c:otherwise>
				</c:choose>				
			</li>
		</ul>
		<c:if test="${sessionScope.user.admin}">
			<ul class="nav navbar-nav navbar-right">
				<li><a class="btn" href="/admin/revenue/month" >Shop Manager</a></li>
			</ul>
		</c:if>		
	</div>
</nav>