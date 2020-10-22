<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/home/index">Home</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Manager <span class="caret"></span></a>				
				<ul class="dropdown-menu">
					<li><a href="/admin/category/index">Product Categories</a></li>
					<li><a href="/admin/product/index">Products</a></li>
					<li><a href="/admin/customer/index">Customers</a></li>
					<li><a href="/admin/order/index">Orders</a></li>
				</ul>
			</li>
			<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Statistics <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/admin/inventory/index">Inventory</a></li>
					<li><a href="/admin/revenue/category">Revenue by Category</a></li>
					<li><a href="/admin/revenue/customer">Revenue by Customer</a></li>
					<li><a href="/admin/revenue/year">Revenue by years</a></li>
					<li><a href="/admin/revenue/month">Revenue by months</a></li>
					<li><a href="/admin/revenue/quarter">Revenue by quarters</a></li>
				</ul>
			</li>
			
		</ul>
	</div>
</nav>