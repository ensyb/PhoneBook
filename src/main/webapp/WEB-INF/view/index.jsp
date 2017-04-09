<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="../fragments/header/head.jsp"></c:import>
<body class="indexBody">

	<%@ include file="../fragments/header/navigationHeader.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="inner">
					<h1>WEB IMENIK</h1>
					<h2>telefonski imenik na webu, kome to još treba ?</h2>
					<hr>
					<a href="about.html" class="btn btn-default btn-lg">istraži</a>
				</div>
			</div>
		</div>
		<%@ include file="../fragments/header/modalsBody.jsp" %>
	</div>
	<%@ include file="../fragments/footer/scripts.jsp" %>
</body>
</html>