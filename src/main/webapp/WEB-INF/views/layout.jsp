<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
	
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Tô Tô Shopping Mall</title>
	<tiles:insertAttribute name = "head"/>	
	<style>
		body, html {
		  height: 150%;
		  margin: 0;
		}
		
		.bg {
		  /* The image used */
		  background-image: url("/static/images/bg3.jpeg");
		
		  /* Full height */
		  height: 100%; 
		
		  /* Center and scale the image nicely */
		  background-position: center;
		  background-repeat: no-repeat;
		  background-size: cover;
		}
	</style>
</head>

<body class="bg">
	<div class="container">
		<header class="row">
			<h1 class="alert alert-success">Tô Tô Shopping Mall</h1>  
		</header>
		
		<nav class="row">
			<tiles:insertAttribute name = "menu"/>
		</nav> 
			
		<div class="row">
			<article class="col-sm-9">
				<tiles:insertAttribute name = "body"/>
			</article>
			<aside class="col-sm-3">
				<tiles:insertAttribute name = "aside"/>
			</aside>
		</div>
		
		<footer class="row">
			<p class="text-center">&copy;2020. All rights reserved</p>
		</footer>
	</div>
</body>
</html>