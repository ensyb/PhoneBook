<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/header/head.jsp"></c:import>
<body class="indexBody">
	<c:import url="fragments/header/navigationHeader.jsp"></c:import>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="inner">
					<h1>WEB IMENIK</h1>
					<h2>telefonski imenik na webu, kome to još treba ?</h2>
					<hr>
					<button class="btn btn-default btn-lg">istraži</button>
				</div>
			</div>
		</div>
		<c:import url="fragments/header/modalsBody.jsp"></c:import>
	</div>
	<c:import url="fragments/footer/scripts.jsp"></c:import>
</body>
</html>