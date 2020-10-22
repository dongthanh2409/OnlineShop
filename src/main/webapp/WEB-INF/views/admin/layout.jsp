<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
	
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Administrator Tool</title>
	<tiles:insertAttribute name = "head"/>
</head>

<body>
	<div class="container">
		<header class="row">
			<h1 class="alert alert-success">Online Shopping Mall</h1>  
		</header>
		
		<nav class="row">
			<tiles:insertAttribute name = "menu"/>
		</nav> 
			
		<div class="row">
				<tiles:insertAttribute name = "body"/>
		</div>
		
		<footer class="row">
			<p class="text-center">&copy;2020. All rights reserved</p>
		</footer>
	</div>

</body>
</html>