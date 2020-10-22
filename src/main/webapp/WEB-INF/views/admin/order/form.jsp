<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>   

<div class="panel panel-default">
	<div class="panel-body">
		<form:form action="${base}/index" modelAttribute="entity">
		<div class = "row">
			<div class="form-group col-sm-6">
				<label>Order Id</label>
				<form:input path="id" class="form-control" readonly="true" placeholder="Auto Generated"/>
			</div>
			<div class="form-group col-sm-6">
				<label>Customer</label>
				<form:input path="customer.id" class="form-control" readonly="${entity.completed}"/>
			</div>
		</div>
		<div class = "row">	
			<div class="form-group col-sm-6">
				<label>Order Date</label>
				<form:input path="orderDate" class="form-control" readonly="${entity.completed}"/>
			</div>
			<div class="form-group col-sm-6">
				<label>Shipping Address</label>
				<form:input path="address" class="form-control" readonly="${entity.completed}"/>
			</div>
		</div>
		<div class = "row">
			<div class="form-group col-sm-6">
				<label>Total Amount</label>
				<form:input path="amount" class="form-control" readonly="${entity.completed}"/>
			</div>
			<div class="form-group col-sm-6">
				<label>Status</label>
				<div>
					<form:radiobutton path="completed" value="true" label="Completed" />
					<form:radiobutton path="completed" value="false" label="Not Completed" disabled="${entity.completed}" />
				</div>
			</div>
		</div>
		<div class = "row">
			<div class="form-group col-sm-12" >
				<label>Description</label>
				<form:textarea path="description" class="form-control" readonly="${entity.completed}"/>
			</div>
		</div>
			<div class="form-group">
				<c:if test="${entity.completed != null}">
					<button class="btn btn-warning" formaction="${base}/update" ${entity.completed?'style="display:none"':''} >Edit</button>
				</c:if>				
			</div>
		</form:form>
		
		<c:if test="${!empty details}">
		<jsp:include page="details.jsp"></jsp:include>
		</c:if>
	</div>
</div>
