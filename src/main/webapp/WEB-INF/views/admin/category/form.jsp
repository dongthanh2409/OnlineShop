<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>   

<div class="panel panel-default">
	<div class="panel-body">
		<form:form action="${base}/index" modelAttribute="entity">
			<div class="form-group">
				<label>Name</label>
				<form:input path="name" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Name VN</label>
				<form:input path="nameVN" class="form-control"/>
			</div>
			<div class="form-group">
				<button class="btn btn-primary" formaction="${base}/create" ${entity.name==null?'':'disabled'}>Create</button>
				<button class="btn btn-warning" formaction="${base}/update" ${entity.name==null?'disabled':''}>Update</button>
				<a class="btn btn-default" href="${base}/index">Reset</a>
			</div>
		</form:form>
	</div>
</div>
